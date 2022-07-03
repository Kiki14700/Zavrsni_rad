package org.eclipse.scout.apps.xlsxreader.files.stocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.scout.apps.xlsxreader.beans.ServiceBean;
import org.eclipse.scout.apps.xlsxreader.beans.StockBean;
import org.eclipse.scout.apps.xlsxreader.constants.Constants;
import org.eclipse.scout.apps.xlsxreader.enums.DataTypeOfImportedFiles;
import org.eclipse.scout.apps.xlsxreader.helpers.SQLHelper;
import org.eclipse.scout.rt.platform.holders.ByteArrayHolder;
import org.eclipse.scout.rt.platform.holders.IntegerHolder;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.holders.StringArrayHolder;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.IOUtility;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class ImportStockDataService implements IImportStockDataService {
	@Override
	public ImportStockDataTablePageData getImportStockDataTableData(ImportStockDataSearchFormData filter) {
		ImportStockDataTablePageData pageData = new ImportStockDataTablePageData();

		int dataType1 = DataTypeOfImportedFiles.STOCK.code;

		StringBuffer varname1 = new StringBuffer();
		varname1.append("SELECT id, ");
		varname1.append("       company_id, ");
		varname1.append("       name, ");
		varname1.append("       stock_import_type, ");
		varname1.append("       import_time, ");
		varname1.append("       imported_items ");
		varname1.append("FROM   file ");
		varname1.append("WHERE  data_type = :dataType ");

		if (filter.getDateFrom().getValue() != null)
			varname1.append("	AND import_time >= :DateFrom ");
		if (filter.getDateTo().getValue() != null)
			varname1.append("	AND CAST(import_time AS date) <= :DateTo ");
		if (filter.getFilesImportType().getValue() != null)
			varname1.append("	AND stock_import_type = :FilesImportType ");
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

		SQL.selectInto(varname1.toString(), pageData, filter, new NVPair("dataType", dataType1), filter);

		return pageData;
	}

	@Override
	public ImportStockDataFormData prepareCreate(ImportStockDataFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}

	@Override
	public ImportStockDataFormData create(ImportStockDataFormData formData) {

		StringBuffer varname = new StringBuffer();
		varname.append("SELECT stock_id ");
		varname.append("FROM   branch ");
		varname.append("WHERE  id = :branchId");

		final int stockID = SQLHelper.getInt(
				SQL.select(varname.toString(), new NVPair("branchId", formData.getStock().getValue().intValue())));

		if (stockID < 1) {
			return formData;
		}

		String fileName = IOUtility.getFileName(formData.getChooseFile().getValue().getFilename());
		ArrayList<StockBean> beans = formData.getImportDetailsData();
		int dataType1 = DataTypeOfImportedFiles.STOCK.code;

		StringBuffer varname1 = new StringBuffer();
		varname1.append("INSERT INTO FILE ");
		varname1.append("            (company_id, ");
		varname1.append("             name, ");
		varname1.append("             stock_import_type, ");
		varname1.append("             file_content, ");
		varname1.append("             import_time, ");
		varname1.append("             imported_items, ");
		varname1.append("             stock_id, ");
		varname1.append("             data_type) ");
		varname1.append("VALUES      (:Company, ");
		varname1.append("             :FileName, ");
		varname1.append("             :ImportType, ");
		varname1.append("             :content, ");
		varname1.append("             Now(), ");
		varname1.append("             :quantity, ");
		varname1.append("             :Stock, ");
		varname1.append("             :dataType) ");
		varname1.append("RETURNING    id INTO :fileID");

		SQL.selectInto(varname1.toString(), formData,
				new NVPair("content", formData.getChooseFile().getValue().getContent()),
				new NVPair("FileName", fileName), new NVPair("dataType", dataType1),
				new NVPair("quantity", beans.size()));

		int idFile = formData.getFileID().intValue();
		int companyID = formData.getCompany().getValue().intValue();
		int importType = formData.getImportType().getValue().intValue();

		List<String> codes = getCodes(companyID);

		String code;
		Integer quantity;
		
		for (int i = 0; i < beans.size(); i++) {
			code = beans.get(i).getCode();
			quantity = beans.get(i).getQuantity();

			if (importType == Constants.Dodavanje_usluga_na_skladište) {
				if (codes.contains(code)) {
					int serviceID = getServiceID(code, companyID);
					boolean exists = serviceAlreadyAtStock(serviceID, stockID);
					if (exists == true) {
						addServicesToStock(serviceID, quantity, stockID);
						insertFileDetails(idFile, code, TEXTS.get("ImportStockDataService.SuccessImport"));
					} else {
						insertFileDetails(idFile, code, TEXTS.get("ImportStockDataService.AlreadyAdded"));
					}

				} else {
					insertFileDetails(idFile, code, TEXTS.get("ImportStockDataService.CodeFail"));
				}
			} else if (importType == Constants.Nabava_robe) {
				if (codes.contains(code)) {
					int serviceID = getServiceID(code, companyID);
					boolean exists = serviceAlreadyAtStock(serviceID, stockID);
					if (exists == false) {
						int existingQuantity = getQuantity(serviceID, stockID);
						int newQuantity = existingQuantity + quantity;
						insertNewQuantity(stockID, serviceID, newQuantity);
						insertFileDetails(idFile, code, TEXTS.get("ImportStockDataService.SuccessImport"));
					} else {
						insertFileDetails(idFile, code, TEXTS.get("ImportStockDataService.DoesNotExist"));
					}

				} else {
					insertFileDetails(idFile, code, TEXTS.get("ImportStockDataService.CodeFail"));
				}

			} else if (importType == Constants.Inventura) {
				if (codes.contains(code)) {
					int serviceID = getServiceID(code, companyID);
					boolean exists = serviceAlreadyAtStock(serviceID, stockID);
					if (exists == false) {
						insertNewQuantity(stockID, serviceID, quantity);
						insertFileDetails(idFile, code, TEXTS.get("ImportStockDataService.SuccessImport"));
					} else {
						insertFileDetails(idFile, code, TEXTS.get("ImportStockDataService.DoesNotExist"));
					}
				} else {
					insertFileDetails(idFile, code, TEXTS.get("ImportStockDataService.CodeFail"));
				}

			}

		}

		return formData;
	}

	@Override
	public ImportStockDataFormData load(ImportStockDataFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}

	@Override
	public ImportStockDataFormData store(ImportStockDataFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}

	private int getServiceID(String code, int companyID) {

		IntegerHolder holder = new IntegerHolder();

		String stmt = "SELECT id FROM service WHERE code = :code AND company_id = :companyID INTO :holder";
		SQL.selectInto(stmt, new NVPair("code", code), new NVPair("companyID", companyID),
				new NVPair("holder", holder));

		return holder.getValue();
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

	private void addServicesToStock(int serviceID, int quantity, int stockID) {

		StringBuffer  varname1 = new StringBuffer();
		varname1.append("INSERT INTO service_stock ");
		varname1.append("            (stock_id, ");
		varname1.append("             service_id, ");
		varname1.append("             quantity) ");
		varname1.append("VALUES      (:stockID, ");
		varname1.append("             :serviceID, ");
		varname1.append("             :quantity)");
		SQL.insert(varname1.toString(), new NVPair("stockID", stockID), new NVPair("serviceID", serviceID),
				new NVPair("quantity", quantity));

	}

	private boolean serviceAlreadyAtStock(int serviceID, int stockID) {

		IntegerHolder holder = new IntegerHolder();

		StringBuffer  varname1 = new StringBuffer();
		varname1.append("SELECT Count(0) ");
		varname1.append("FROM   service_stock ");
		varname1.append("WHERE  stock_id = :stockID ");
		varname1.append("       AND service_id = :serviceID ");
		varname1.append("INTO   :holder");

		SQL.selectInto(varname1.toString(), new NVPair("holder", holder), new NVPair("serviceID", serviceID),
				new NVPair("stockID", stockID));

		return holder.getValue() < 1;
	}

	private int getQuantity(int serviceID, int stockID) {

		IntegerHolder holder = new IntegerHolder();

		StringBuffer  varname1 = new StringBuffer();
		varname1.append("SELECT quantity ");
		varname1.append("FROM   service_stock ");
		varname1.append("WHERE  service_id = :serviceID ");
		varname1.append("       AND stock_id = :stockID ");
		varname1.append("INTO   :holder");

		SQL.selectInto(varname1.toString(), new NVPair("stockID", stockID), new NVPair("serviceID", serviceID),
				new NVPair("holder", holder));

		return holder.getValue();
	}

	private void insertNewQuantity(int stockID, int serviceID, int newQuantity) {

		StringBuffer  varname1 = new StringBuffer();
		varname1.append("UPDATE service_stock ");
		varname1.append("SET    quantity = :newQuantity ");
		varname1.append("WHERE  stock_id = :stockID ");
		varname1.append("       AND service_id = :serviceID");
		
		SQL.update(varname1.toString(), new NVPair("newQuantity", newQuantity), new NVPair("stockID", stockID),
				new NVPair("serviceID", serviceID));

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
