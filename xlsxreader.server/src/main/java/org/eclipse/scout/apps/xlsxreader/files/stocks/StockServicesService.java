package org.eclipse.scout.apps.xlsxreader.files.stocks;

import org.eclipse.scout.apps.xlsxreader.helpers.SQLHelper;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class StockServicesService implements IStockServicesService {
	@Override
	public StockServicesTablePageData getStockServicesTableData(StockServicesSearchFormData filter) {
		StockServicesTablePageData pageData = new StockServicesTablePageData();

		StringBuffer varname = new StringBuffer();
		varname.append("SELECT stock_id ");
		varname.append("FROM   branch ");
		varname.append("WHERE  id = :branchID");
		
		final int stockId = SQLHelper.getInt(SQL.select(varname.toString(),
			new NVPair("branchID", filter.getStock().getValue())));
		
		if(stockId < 1) {
		    return pageData;
		}
		
		StringBuffer  varname1 = new StringBuffer();
		varname1.append("SELECT s.id, ");
		varname1.append("       s.code, ");
		varname1.append("       s.name, ");
		varname1.append("       s.workplace_id, ");
		varname1.append("       ss.quantity ");
		varname1.append("FROM   service s ");
		varname1.append("       INNER JOIN service_stock ss ");
		varname1.append("               ON ss.service_id = s.id ");
		varname1.append("                  AND ss.stock_id = :stockID ");
		varname1.append("INTO   :ID, ");
		varname1.append("       :Code,  ");
		varname1.append("       :Name, ");
		varname1.append("       :Workplace, ");
		varname1.append("       :NumOfServices ");
		varname1.append("       ORDER BY s.code");
		
		SQL.selectInto(varname1.toString(), pageData, filter, new NVPair("stockID", stockId));
		
		
		return pageData;
	}
}
