package org.eclipse.scout.apps.xlsxreader.files.prices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.scout.apps.xlsxreader.beans.ClientBean;
import org.eclipse.scout.apps.xlsxreader.beans.PriceBean;
import org.eclipse.scout.apps.xlsxreader.enums.DataTypeOfImportedFiles;
import org.eclipse.scout.apps.xlsxreader.files.klijenti.ClientsFileDetailsFormData;
import org.eclipse.scout.rt.platform.holders.ByteArrayHolder;
import org.eclipse.scout.rt.platform.holders.IntegerHolder;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.holders.StringArrayHolder;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.IOUtility;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class ImportPricesService implements IImportPricesService {
	@Override
	public ImportPricesTablePageData getImportPricesTableData(PricesFileSearchFormData filter) {
		ImportPricesTablePageData pageData = new ImportPricesTablePageData();

		int dataType = DataTypeOfImportedFiles.PRICE.code;

		StringBuffer varname1 = new StringBuffer();
		varname1.append("SELECT id, ");
		varname1.append("       company_id, ");
		varname1.append("       name, ");
		varname1.append("       import_type, ");
		varname1.append("       imported_items, ");
		varname1.append("       import_time ");
		varname1.append("FROM   file ");
		varname1.append("WHERE  data_type = :dataType ");

		if (filter.getDateFrom().getValue() != null)
			varname1.append("	AND import_time >= :DateFrom ");
		if (filter.getDateTo().getValue() != null)
			varname1.append("	AND CAST(import_time AS date) <= :DateTo ");
		if (filter.getPriceImportType().getValue() != null)
			varname1.append("	AND import_type = :PriceImportType ");
		if (filter.getPricesCompany().getValue() != null)
			varname1.append("	AND company_id = :PricesCompany ");
		if (filter.getPricesName().getValue() != null)
			varname1.append("	AND UPPER(name) LIKE UPPER('%" + filter.getPricesName().getValue() + "%') ");

		varname1.append("INTO   :ID, ");
		varname1.append("       :Company, ");
		varname1.append("       :Name, ");
		varname1.append("       :ImportType, ");
		varname1.append("       :NumberOfPrices, ");
		varname1.append("       :Date");

		SQL.selectInto(varname1.toString(), pageData, new NVPair("dataType", dataType), filter);

		return pageData;
	}

	@Override
	public ImportPricesFormData prepareCreate(ImportPricesFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}

	@Override
	public ImportPricesFormData create(ImportPricesFormData formData) {

		String fileName = IOUtility.getFileName(formData.getChooseFile().getValue().getFilename());
		ArrayList<PriceBean> beans = formData.getImportDetailsData();
		int dataType = DataTypeOfImportedFiles.PRICE.code;

		StringBuffer varname1 = new StringBuffer();
		varname1.append("INSERT INTO file ");
		varname1.append("            (company_id, ");
		varname1.append("             name, ");
		varname1.append("             import_type, ");
		varname1.append("             imported_items, ");
		varname1.append("             file_content, ");
		varname1.append("             import_time, ");
		varname1.append("             pricelist_id, ");
		varname1.append("             data_type) ");
		varname1.append("VALUES      (:Company, ");
		varname1.append("             :fileName, ");
		varname1.append("             :ImportType, ");
		varname1.append("             :quantity, ");
		varname1.append("             :content, ");
		varname1.append("             now(), ");
		varname1.append("             :Pricelist, ");
		varname1.append("             :dataType) ");
		varname1.append("RETURNING id INTO :fileID");

		SQL.selectInto(varname1.toString(), formData, new NVPair("fileName", fileName),
				new NVPair("content", formData.getChooseFile().getValue().getContent()),
				new NVPair("dataType", dataType), new NVPair("quantity", beans.size()));

		int idFile = formData.getFileID().intValue();
		int companyID = formData.getCompany().getValue().intValue();
		int pricelistID = formData.getPricelist().getValue().intValue();
		List<String> codes = getCodes(companyID);
		
		String priceHRK;
		String tax;
		String date;
		String serviceCode;

		for (int i = 0; i < beans.size(); i++) {
			priceHRK = beans.get(i).getPriceHRK();
			tax = beans.get(i).getTax();
			date = beans.get(i).getDate();
			serviceCode = beans.get(i).getServiceCode();

			Integer serviceID = getServiceIDByCode(serviceCode, companyID);
			Integer alreadyExists = getNumOfSamePrice(date, tax, priceHRK, serviceID, pricelistID);

			if (alreadyExists == 0) {
				if (codes.contains(serviceCode)) {
					insertPrices(priceHRK, tax, date, pricelistID, serviceID);
					insertFileDetails(idFile, serviceCode, TEXTS.get("ImportPricesService.SuccessImport"));
				} else {
					insertFileDetails(idFile, serviceCode, TEXTS.get("ImportPricesService.CodeFail"));
				}
			} else {
				insertFileDetails(idFile, serviceCode, TEXTS.get("ImportPricesService.PriceExists"));
			}

		}

		return formData;
	}

	@Override
	public ImportPricesFormData load(ImportPricesFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}

	@Override
	public ImportPricesFormData store(ImportPricesFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
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

	private void insertPrices(String priceHRK, String tax, String date, int pricelistID, int serviceID) {

		StringBuffer varname1 = new StringBuffer();
		varname1.append("INSERT INTO price ");
		varname1.append("            (price_hrk, ");
		varname1.append("             tax, ");
		varname1.append("             date, ");
		varname1.append("             pricelist_id, ");
		varname1.append("             service_id) ");
		varname1.append("VALUES      (Cast (:priceHRK AS NUMERIC(9, 2)), ");
		varname1.append("             Cast(:tax AS NUMERIC), ");
		varname1.append("             Cast(:date AS DATE), ");
		varname1.append("             :pricelistID,");
		varname1.append("             :serviceID)");

		SQL.insert(varname1.toString(), new NVPair("priceHRK", priceHRK), new NVPair("tax", tax),
				new NVPair("date", date), new NVPair("pricelistID", pricelistID), new NVPair("serviceID", serviceID));

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

	private Integer getServiceIDByCode(String serviceCode, int companyID) {

		IntegerHolder serviceID = new IntegerHolder();

		StringBuffer varname1 = new StringBuffer();
		varname1.append("SELECT id ");
		varname1.append("FROM   service ");
		varname1.append("WHERE  code = :serviceCode ");
		varname1.append("       AND company_id = :companyID ");
		varname1.append("INTO   :serviceID");

		SQL.selectInto(varname1.toString(), new NVPair("serviceCode", serviceCode), new NVPair("companyID", companyID),
				new NVPair("serviceID", serviceID));

		return serviceID.getValue();
	}

	private void insertFileDetails(int idFile, String serviceCode, String string) {

		StringBuffer varname1 = new StringBuffer();
		varname1.append("INSERT INTO file_details ");
		varname1.append("            (file_id, ");
		varname1.append("             code, ");
		varname1.append("             message) ");
		varname1.append("VALUES      (:idFile, ");
		varname1.append("             :code, ");
		varname1.append("             :string)");

		SQL.insert(varname1.toString(), new NVPair("idFile", idFile), new NVPair("code", serviceCode),
				new NVPair("string", string));

	}

	private Integer getNumOfSamePrice(String date, String tax, String priceHRK, Integer serviceID,
			Integer pricelistID) {

		IntegerHolder holder = new IntegerHolder();

		StringBuffer  varname1 = new StringBuffer();
		varname1.append("SELECT Count(0) ");
		varname1.append("FROM   price ");
		varname1.append("WHERE  date = Cast (:date AS DATE) ");
		varname1.append("AND    tax = Cast(:tax AS NUMERIC) ");
		varname1.append("AND    service_id = :serviceID ");
		varname1.append("AND    price_hrk = Cast(:priceHRK AS NUMERIC(9, 2)) ");
		varname1.append("AND    pricelist_id = :pricelistID ");
		varname1.append("INTO   :holder");
		
		SQL.selectInto(varname1.toString(), new NVPair("date", date), new NVPair("priceHRK", priceHRK), new NVPair("tax", tax),
				new NVPair("serviceID", serviceID), new NVPair("holder", holder),
				new NVPair("pricelistID", pricelistID));

		return holder.getValue().intValue();
	}

	@Override
	public void deleteFile(List<Integer> fileID) {

		StringBuffer varname1 = new StringBuffer();
		varname1.append("DELETE FROM FILE ");
		varname1.append("WHERE  id = :fileID");

		SQL.delete(varname1.toString(), new NVPair("fileID", fileID));

	}
}
