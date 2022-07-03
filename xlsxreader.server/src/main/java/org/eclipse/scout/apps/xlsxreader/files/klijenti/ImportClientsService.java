package org.eclipse.scout.apps.xlsxreader.files.klijenti;

import java.math.BigInteger;

import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.scout.apps.xlsxreader.beans.ClientBean;
import org.eclipse.scout.apps.xlsxreader.constants.Constants;
import org.eclipse.scout.apps.xlsxreader.enums.DataTypeOfImportedFiles;
import org.eclipse.scout.rt.platform.holders.ByteArrayHolder;
import org.eclipse.scout.rt.platform.holders.IntegerArrayHolder;
import org.eclipse.scout.rt.platform.holders.IntegerHolder;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.holders.StringArrayHolder;
import org.eclipse.scout.rt.platform.holders.StringHolder;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.IOUtility;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.opendope.SmartArt.dataHierarchy.SmartArtDataHierarchy.Texts;
import org.pptx4j.pml.STTLBehaviorAdditiveType;

public class ImportClientsService implements IImportClientsService {
	@Override
	public ImportClientsTablePageData getImportClientsTableData(ClientsFileSearchFormData filter) {
		ImportClientsTablePageData pageData = new ImportClientsTablePageData();

		int dataType1 = DataTypeOfImportedFiles.CLIENT.code;

		StringBuffer varname1 = new StringBuffer();
		varname1.append("SELECT id, ");
		varname1.append("       company_id, ");
		varname1.append("       name, ");
		varname1.append("       import_type, ");
		varname1.append("       import_time, ");
		varname1.append("       imported_items ");
		varname1.append("FROM   file ");
		varname1.append("WHERE  data_type = :dataType ");

		if (filter.getDateFrom().getValue() != null)
			varname1.append("	AND import_time >= :DateFrom ");
		if (filter.getDateTo().getValue() != null)
			varname1.append("	AND CAST(import_time AS date) <= :DateTo ");
		if (filter.getFilesImportType().getValue() != null)
			varname1.append("	AND import_type = :FilesImportType ");
		if (filter.getFilesCompany().getValue() != null)
			varname1.append("	AND company_id = :FilesCompany ");
		if (filter.getFilesName().getValue() != null)
			varname1.append("	AND UPPER(name) LIKE UPPER('%" + filter.getFilesName().getValue() + "%') ");

		varname1.append("INTO   :ID, ");
		varname1.append("       :Company, ");
		varname1.append("       :Name, ");
		varname1.append("       :ImportType, ");
		varname1.append("       :Date, ");
		varname1.append("       :NumberOfClients");

		SQL.selectInto(varname1.toString(), pageData, filter, new NVPair("dataType", dataType1));

		return pageData;
	}

	@Override
	public ImportClientsFormData prepareCreate(ImportClientsFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}

	@Override
	public ImportClientsFormData create(ImportClientsFormData formData) {

		String fileName = IOUtility.getFileName(formData.getChooseFile().getValue().getFilename());

		ArrayList<ClientBean> beans = formData.getImportDetailsData();

		int dataType1 = DataTypeOfImportedFiles.CLIENT.code;

		StringBuffer varname1 = new StringBuffer();
		varname1.append("INSERT INTO FILE ");
		varname1.append("            (company_id, ");
		varname1.append("             name, ");
		varname1.append("             import_type, ");
		varname1.append("             file_content, ");
		varname1.append("             import_time, ");
		varname1.append("             imported_items,  ");
		varname1.append("             data_type) ");
		varname1.append("VALUES      (:Company, ");
		varname1.append("             :FileName, ");
		varname1.append("             :ImportType, ");
		varname1.append("             :content, ");
		varname1.append("             Now(), ");
		varname1.append("             :quantity,  ");
		varname1.append("             :dataType) ");
		varname1.append("RETURNING    id INTO :IDFile");

		SQL.selectInto(varname1.toString(), formData,
				new NVPair("content", formData.getChooseFile().getValue().getContent()),
				new NVPair("FileName", fileName), new NVPair("quantity", beans.size()),
				new NVPair("dataType", dataType1));

		int idFile = formData.getIDFile().intValue();
		int companyID = formData.getCompany().getValue().intValue();
		int importType = formData.getImportType().getValue().intValue();

		List<String> codes = getCodes(companyID);

		Integer lastCode = getLastCode(companyID);
		Integer newCode = lastCode + 1;
		
		String code;
		String name;
		String surname;
		String town;
		String address;
		String OIB;
		String email;
		String phone;
		String dateOfBirth;

		for (int i = 0; i < beans.size(); i++) {
			code = beans.get(i).getCode();
			name = beans.get(i).getName();
			surname = beans.get(i).getSurname();
			town = beans.get(i).getTown();
			address = beans.get(i).getAddress();
			OIB = beans.get(i).getOIB();
			email = beans.get(i).getEmail();
			phone = beans.get(i).getPhone();
			dateOfBirth = beans.get(i).getDateOfBirth();
			
			
			String mistake = name;
			
			if(OIB.length() != 11) {
				mistake += " | " + TEXTS.get("ImportClientsService.WrongOIBFormat");
			}
			
			String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
			
			if(!email.matches(regex)) {
				mistake += " | " + TEXTS.get("ImportClientsService.WrongEmailFormat");
			}

			if (importType == Constants.Dodavanje_novih_stavki) {
				if(mistake == name) {
					insertNewClient(newCode, companyID, name, surname, town, address, OIB, email, phone, dateOfBirth);
					insertFileDetails(idFile, newCode.toString(), TEXTS.get("ImportClientsService.Added"));
					newCode++;
				}else {
					insertFileDetails(idFile, newCode.toString(), mistake);
				}
				
			} else if (importType == Constants.Azuriranje) {
				if (codes.contains(code.toString())) {
					if(mistake == name) {
						updateClients(companyID, name, surname, town, address, OIB, email, phone, dateOfBirth, code);
						insertFileDetails(idFile, code, TEXTS.get("ImportClientsService.SuccessUpdate"));
					}else {
						insertFileDetails(idFile, code.toString(), mistake);
					}
					
				} else {
					insertFileDetails(idFile, code, TEXTS.get("ImportClientsService.ClientNotFound"));
				}

			}

		}

		return formData;
	}

	

	@Override
	public ImportClientsFormData load(ImportClientsFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}

	@Override
	public ImportClientsFormData store(ImportClientsFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}

	private Integer getLastCode(int companyID) {

		IntegerHolder lastCode = new IntegerHolder();

		StringBuffer varname1 = new StringBuffer();
		varname1.append("SELECT   Cast (code AS INTEGER) ");
		varname1.append("FROM     client ");
		varname1.append("INTO     :lastCode ");
		varname1.append("WHERE    company_id = :companyID ");
		varname1.append("ORDER BY code DESC limit 1");

		SQL.selectInto(varname1.toString(), new NVPair("lastCode", lastCode), new NVPair("companyID", companyID));

		if (lastCode.getValue() == null) {
			lastCode.setValue(100);
		}

		return lastCode.getValue().intValue();
	}

	private List<String> getCodes(int companyID) {
		StringArrayHolder codes = new StringArrayHolder();

		StringBuffer varname1 = new StringBuffer();
		varname1.append("SELECT code ");
		varname1.append("FROM   client ");
		varname1.append("INTO   :codes ");
		varname1.append("WHERE  company_id = :companyID");

		SQL.selectInto(varname1.toString(), new NVPair("codes", codes), new NVPair("companyID", companyID));

		return Arrays.asList(codes.getValue());
	}

	private void insertNewClient(int newCode, int companyID, String name, String surname, String town, String address,
			String OIB, String email, String phone, String dateOfBirth) {

		StringBuffer varname1 = new StringBuffer();
		varname1.append("INSERT INTO client ");
		varname1.append("            (code, ");
		varname1.append("             company_id, ");
		varname1.append("             name, ");
		varname1.append("             surname, ");
		varname1.append("             town, ");
		varname1.append("             address, ");
		varname1.append("             oib, ");
		varname1.append("             email, ");
		varname1.append("             phone, ");
		varname1.append("             date_of_birth) ");
		varname1.append("VALUES      ( CAST (:newCode AS character varying), ");
		varname1.append("             :companyID, ");
		varname1.append("             :name, ");
		varname1.append("             :surname, ");
		varname1.append("             :town, ");
		varname1.append("             :address, ");
		varname1.append("             Cast (:OIB AS BIGINT), ");
		varname1.append("             :email, ");
		varname1.append("             :phone, ");
		varname1.append("             Cast (:dateOfBirth AS DATE))");

		SQL.insert(varname1.toString(), new NVPair("companyID", companyID), new NVPair("name", name),
				new NVPair("surname", surname), new NVPair("town", town), new NVPair("address", address),
				new NVPair("OIB", OIB), new NVPair("email", email), new NVPair("phone", phone),
				new NVPair("dateOfBirth", dateOfBirth), new NVPair("newCode", newCode));

	}

	private void insertFileDetails(int idFile, String code, String string) {

		StringBuffer varname1 = new StringBuffer();
		varname1.append("INSERT INTO file_details ");
		varname1.append("            (file_id, ");
		varname1.append("             code, ");
		varname1.append("             message) ");
		varname1.append("VALUES      (:idFile, ");
		varname1.append("             :code, ");
		varname1.append("             :string)");

		SQL.insert(varname1.toString(), new NVPair("idFile", idFile), new NVPair("code", code),
				new NVPair("string", string));

	}

	private String getClientCode(String OIB) {

		StringHolder oldCode = new StringHolder();

		StringBuffer varname1 = new StringBuffer();
		varname1.append("SELECT code ");
		varname1.append("FROM   client ");
		varname1.append("WHERE  oib = Cast (:OIB AS BIGINT) ");
		varname1.append("into   :oldCode");

		SQL.selectInto(varname1.toString(), new NVPair("OIB", OIB), new NVPair("oldCode", oldCode));

		return oldCode.getValue().toString();
	}

	private void updateClients(int companyID, String name, String surname, String town, String address, String OIB,
			String email, String phone, String dateOfBirth, String code) {

		StringBuffer varname1 = new StringBuffer();
		varname1.append("UPDATE client ");
		varname1.append("SET    name = :name, ");
		varname1.append("       surname = :surname, ");
		varname1.append("       town = :town, ");
		varname1.append("       address = :address, ");
		varname1.append("       oib = Cast (:OIB AS BIGINT), ");
		varname1.append("       email = :email, ");
		varname1.append("       phone = :phone, ");
		varname1.append("       date_of_birth = Cast (:dateOfBirth AS DATE) ");
		varname1.append("WHERE  company_id = :companyID ");
		varname1.append("       AND code = :code");

		SQL.update(varname1.toString(), new NVPair("companyID", companyID), new NVPair("name", name),
				new NVPair("surname", surname), new NVPair("town", town), new NVPair("address", address),
				new NVPair("OIB", OIB), new NVPair("email", email), new NVPair("phone", phone),
				new NVPair("dateOfBirth", dateOfBirth), new NVPair("code", code));

	}

	@Override
	public byte[] getFileContent(Integer fileId) {
		ByteArrayHolder content = new ByteArrayHolder();

		StringBuffer varname1 = new StringBuffer();
		varname1.append("SELECT file_content ");
		varname1.append("FROM   file ");
		varname1.append("WHERE  id = :fileId ");
		varname1.append("INTO   :content");
		SQL.selectInto(varname1.toString(), new NVPair("fileId", fileId), new NVPair("content", content));

		return content.getValue();
	}

	@Override
	public void deleteFile(List<Integer> fileID) {
		StringBuffer  varname1 = new StringBuffer();
		varname1.append("DELETE FROM FILE ");
		varname1.append("WHERE  id = :fileID");
		
		SQL.delete(varname1.toString(), new NVPair("fileID", fileID));
	}
	
	

}
