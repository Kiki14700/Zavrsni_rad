package org.eclipse.scout.apps.xlsxreader.aboutpricelist;

import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class PricelistsService implements IPricelistsService {
	@Override
	public PricelistsTablePageData getPricelistTableData(PricelistSearchFormData filter) {
		PricelistsTablePageData pageData = new PricelistsTablePageData();

		StringBuffer  varname1 = new StringBuffer();
		varname1.append("SELECT id, ");
		varname1.append("       name, ");
		varname1.append("       code, ");
		varname1.append("       note, ");
		varname1.append("       company_id ");
		varname1.append("FROM   pricelist ");
		varname1.append("WHERE  1=1 ");
		
		if(filter.getPricelistsName().getValue() != null) {
			varname1.append(" AND UPPER(name) LIKE UPPER('%" + filter.getPricelistsName().getValue() + "%') ");
		}
		if(filter.getPricelistsCompany().getValue() != null) {
			varname1.append(" AND company_id = :PricelistsCompany ");
		}
		
		varname1.append("INTO   :ID, ");
		varname1.append("       :Name, ");
		varname1.append("       :Code, ");
		varname1.append("       :Note, ");
		varname1.append("       :Company ");
		varname1.append("ORDER BY company_id ");
		
		SQL.selectInto(varname1.toString(), pageData, filter);
		
		return pageData;
	}
}
