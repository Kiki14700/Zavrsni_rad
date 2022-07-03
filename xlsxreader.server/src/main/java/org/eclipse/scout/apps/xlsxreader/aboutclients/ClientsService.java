package org.eclipse.scout.apps.xlsxreader.aboutclients;

import java.util.List;

import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class ClientsService implements IClientsService {
	@Override
	public ClientsTablePageData getClientsTableData(SearchFilter filter) {
		ClientsTablePageData pageData = new ClientsTablePageData();
		
		StringBuffer  varname1 = new StringBuffer();
		varname1.append("SELECT id, ");
		varname1.append("       code, ");
		varname1.append("       name, ");
		varname1.append("       surname, ");
		varname1.append("       date_of_birth, ");
		varname1.append("       oib, ");
		varname1.append("       company_id ");
		varname1.append("FROM   client ");
		varname1.append("INTO   :ID, ");
		varname1.append("       :Code, ");
		varname1.append("       :Name, ");
		varname1.append("       :Surname, ");
		varname1.append("       :DateOfBirth, ");
		varname1.append("       :OIB, ");
		varname1.append("       :Company ");
		varname1.append("ORDER BY company_id");
		
		
		SQL.selectInto(varname1.toString(), pageData);
	
		return pageData;
	}

	
}
