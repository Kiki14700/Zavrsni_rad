package org.eclipse.scout.apps.xlsxreader.files.lookup;

import org.eclipse.scout.apps.xlsxreader.files.lookup.ICompanyLookupService;
import org.eclipse.scout.rt.server.jdbc.lookup.AbstractSqlLookupService;

public class CompanyLookupService extends AbstractSqlLookupService<Long> implements ICompanyLookupService {
	
	@Override
	protected String getConfiguredSqlSelect() {
		
		StringBuffer  varname1 = new StringBuffer();
		varname1.append("SELECT id, ");
		varname1.append("       name ");
		varname1.append("FROM   company ");
		varname1.append("WHERE  1=1 ");
		varname1.append(" <key>AND id = :key </key><all></all>");
		
		return varname1.toString();
	}

}
