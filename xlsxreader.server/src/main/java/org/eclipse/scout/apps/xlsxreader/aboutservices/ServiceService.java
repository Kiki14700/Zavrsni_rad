package org.eclipse.scout.apps.xlsxreader.aboutservices;

import org.eclipse.scout.apps.xlsxreader.aboutservices.IServiceService;
import org.eclipse.scout.apps.xlsxreader.aboutservices.ServiceFormData;
import org.eclipse.scout.rt.server.jdbc.SQL;

public class ServiceService implements IServiceService {
	@Override
	public ServiceFormData prepareCreate(ServiceFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}

	@Override
	public ServiceFormData create(ServiceFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}

	@Override
	public ServiceFormData load(ServiceFormData formData) {
		
		StringBuffer  varname1 = new StringBuffer();
		varname1.append("SELECT name, ");
		varname1.append("       company_id, ");
		varname1.append("       service_category, ");
		varname1.append("       workplace_id ");
		varname1.append("FROM   service ");
		varname1.append("WHERE  id = :serviceID ");
		varname1.append("INTO   :Name, ");
		varname1.append("       :Company, ");
		varname1.append("       :Category, ");
		varname1.append("       :Workplace");
		SQL.selectInto(varname1.toString(), formData);
		
		return formData;
	}

	@Override
	public ServiceFormData store(ServiceFormData formData) {
		
		
		StringBuffer  varname1 = new StringBuffer();
		varname1.append("UPDATE service ");
		varname1.append("SET    name = :Name, ");
		varname1.append("       company_id = :Company, ");
		varname1.append("       service_category = :Category, ");
		varname1.append("       workplace_id = :Workplace ");
		varname1.append("WHERE  id = :serviceID");
		SQL.update(varname1.toString(), formData);
		
		return formData;
	}
}
