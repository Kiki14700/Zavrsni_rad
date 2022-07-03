package org.eclipse.scout.apps.xlsxreader.aboutservices;

import org.apache.xalan.templates.VarNameCollector;
import org.eclipse.scout.apps.xlsxreader.aboutservices.IServicesService;
import org.eclipse.scout.apps.xlsxreader.aboutservices.ServicesTablePageData;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class ServicesService implements IServicesService {
	@Override
	public ServicesTablePageData getServicesTableData(ServicesSearchFormData filter) {
		ServicesTablePageData pageData = new ServicesTablePageData();

		if (filter.getCompany().getValue() != null && filter.getPricelist().getValue() != null) {

			StringBuffer varname1 = new StringBuffer();
			varname1.append("SELECT DISTINCT ON (s.id) s.id, ");
			varname1.append("       s.name, ");
			varname1.append("       s.code, ");
			varname1.append("       s.company_id, ");
			varname1.append("       s.service_category, ");
			varname1.append("       s.workplace_id, ");
			varname1.append("       p.price_hrk, ");
			varname1.append("       p.tax ");
			varname1.append("       FROM service s ");
			varname1.append("             JOIN price p ON (s.id = p.service_id) ");
			varname1.append("       WHERE p.pricelist_id = :pricelistID ");
			varname1.append("       AND CAST(p.date AS date) <= CAST(now() AS date) ");
			varname1.append("       ORDER BY s.id, p.date DESC ");
			varname1.append("INTO   :ID, ");
			varname1.append("       :Name, ");
			varname1.append("       :Code, ");
			varname1.append("       :Company, ");
			varname1.append("       :ServiceCategory, ");
			varname1.append("       :Workplace, ");
			varname1.append("       :Price, ");
			varname1.append("       :Tax ");
		
			SQL.selectInto(varname1.toString(), pageData, filter,
					new NVPair("pricelistID", filter.getPricelist().getValue()));

		}

		return pageData;
	}
}
