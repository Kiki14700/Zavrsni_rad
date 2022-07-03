package org.eclipse.scout.apps.xlsxreader.files.services;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.eclipse.scout.apps.xlsxreader.beans.ClientBean;
import org.eclipse.scout.apps.xlsxreader.beans.ServiceBean;
import org.eclipse.scout.apps.xlsxreader.constants.Constants;
import org.eclipse.scout.apps.xlsxreader.enums.DataTypeOfImportedFiles;
import org.eclipse.scout.apps.xlsxreader.enums.ServiceCategoryEnum;
import org.eclipse.scout.apps.xlsxreader.enums.WorkplaceEnum;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.holders.ByteArrayHolder;
import org.eclipse.scout.rt.platform.holders.IntegerHolder;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.holders.StringArrayHolder;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.IOUtility;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class ImportServicesService implements IImportServicesService {
	@Override
	public ImportServicesTablePageData getImportServicesTableData(ServicesFilesSearchFormData filter) {
		ImportServicesTablePageData pageData = new ImportServicesTablePageData();

		int dataType1 = DataTypeOfImportedFiles.SERVICE.code;

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
		varname1.append("       :NumberOfItems");

		SQL.selectInto(varname1.toString(), pageData, filter, new NVPair("dataType", dataType1));

		return pageData;
	}

	@Override
	public ImportServicesFormData prepareCreate(ImportServicesFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}

	@Override
	public ImportServicesFormData create(ImportServicesFormData formData) {

		String fileName = IOUtility.getFileName(formData.getImportFile().getValue().getFilename());
		ArrayList<ServiceBean> beans = formData.getImportDetailsData();
		int dataType1 = DataTypeOfImportedFiles.SERVICE.code;

		StringBuffer varname1 = new StringBuffer();
		varname1.append("INSERT INTO FILE ");
		varname1.append("            (company_id, ");
		varname1.append("             name, ");
		varname1.append("             import_type, ");
		varname1.append("             file_content, ");
		varname1.append("             import_time, ");
		varname1.append("             imported_items, ");
		varname1.append("             data_type) ");
		varname1.append("VALUES      (:Company, ");
		varname1.append("             :FileName, ");
		varname1.append("             :ImportType, ");
		varname1.append("             :content, ");
		varname1.append("             Now(), ");
		varname1.append("             :quantity, ");
		varname1.append("             :dataType) ");
		varname1.append("RETURNING    id INTO :IDFile");

		SQL.selectInto(varname1.toString(), formData,
				new NVPair("content", formData.getImportFile().getValue().getContent()),
				new NVPair("FileName", fileName), new NVPair("dataType", dataType1),
				new NVPair("quantity", beans.size()));

		int idFile = formData.getIDFile().intValue();
		int companyID = formData.getCompany().getValue().intValue();
		int importType = formData.getImportType().getValue().intValue();

		List<String> codes = getCodes(companyID);

		Integer lastCode = getLastCode(companyID);
		Integer newCode = lastCode + 1;
		
		String code;
		String name;
		String category;
		String workplace;

		for (int i = 0; i < beans.size(); i++) {
			code = beans.get(i).getCode();
			name = beans.get(i).getName();
			category = beans.get(i).getCategory();
			workplace = beans.get(i).getWorkplace();

			String mistake = name;

			Integer sce = ServiceCategoryEnum.getValueByName(category);
			if (sce == null) {
				mistake += " | Nije pronađena kategorija (" + category + ") ";
			}

			Integer we = WorkplaceEnum.getValueByName(workplace);
			if (we == null) {
				mistake += " | Nije pronađena djelatnost (" + workplace + ") ";
			}

			if (importType == Constants.Dodavanje_novih_stavki) {

				if (mistake.equals(name)) {

					int cat = ServiceCategoryEnum.getValueByName(category);
					int work = WorkplaceEnum.getValueByName(workplace);

					inputServiceData(newCode, name, cat, work, companyID);
					insertFileDetails(idFile, newCode.toString(), TEXTS.get("ImportServicesService.Added"));
					newCode++;

				} else {
					insertFileDetails(idFile, code, mistake);
				}

			} else if (importType == Constants.Azuriranje) {
				if (codes.contains(code.toString())) {
					if (mistake.equals(name)) {

						int cat = ServiceCategoryEnum.getValueByName(category);
						int work = WorkplaceEnum.getValueByName(workplace);

						updateServices(companyID, code, name, cat, work);
						insertFileDetails(idFile, code.toLowerCase(), TEXTS.get("ImportServicesService.SuccessUpdate"));
					} else {
						insertFileDetails(idFile, code, mistake);
					}

				} else {
					insertFileDetails(idFile, code.toLowerCase(), TEXTS.get("ImportServicesService.UnsuccessUpdate"));

				}
			}

		}

		return formData;
	}

	@Override
	public ImportServicesFormData load(ImportServicesFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}

	@Override
	public ImportServicesFormData store(ImportServicesFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}

	private Integer getLastCode(int companyID) {

		IntegerHolder lastCode = new IntegerHolder();

		StringBuffer varname1 = new StringBuffer();
		varname1.append("SELECT   Cast (code AS INTEGER) ");
		varname1.append("FROM     service ");
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
		varname1.append("FROM   service ");
		varname1.append("INTO   :codes ");
		varname1.append("WHERE  company_id = :companyID");

		SQL.selectInto(varname1.toString(), new NVPair("codes", codes), new NVPair("companyID", companyID));

		return Arrays.asList(codes.getValue());
	}

	private void inputServiceData(int code, String name, int categoryEnum, int workplaceEnum, int companyID) {

		StringBuffer varname1 = new StringBuffer();
		varname1.append("INSERT INTO service ");
		varname1.append("            (code, ");
		varname1.append("             NAME, ");
		varname1.append("             service_category, ");
		varname1.append("             workplace_id, ");
		varname1.append("             company_id) ");
		varname1.append("VALUES      (:code, ");
		varname1.append("             :name, ");
		varname1.append("             :categoryEnum, ");
		varname1.append("             :workplaceEnum, ");
		varname1.append("             :companyID)");

		SQL.insert(varname1.toString(), new NVPair("code", code), new NVPair("name", name),
				new NVPair("categoryEnum", categoryEnum), new NVPair("workplaceEnum", workplaceEnum),
				new NVPair("companyID", companyID));

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

	private void updateServices(int companyID, String code, String name, int categoryEnum, int workplaceEnum) {

		StringBuffer varname1 = new StringBuffer();
		varname1.append("UPDATE service ");
		varname1.append("SET    NAME = :name, ");
		varname1.append("       service_category = :categoryEnum, ");
		varname1.append("       workplace_id = :workplaceEnum ");
		varname1.append("WHERE  company_id = :companyID ");
		varname1.append("       AND code = :code");

		SQL.update(varname1.toString(), new NVPair("companyID", companyID), new NVPair("code", code),
				new NVPair("name", name), new NVPair("categoryEnum", categoryEnum),
				new NVPair("workplaceEnum", workplaceEnum));

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
