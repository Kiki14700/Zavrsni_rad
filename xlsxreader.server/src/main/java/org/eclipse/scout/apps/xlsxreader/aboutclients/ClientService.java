package org.eclipse.scout.apps.xlsxreader.aboutclients;

import org.eclipse.scout.rt.server.jdbc.SQL;

public class ClientService implements IClientService {
	@Override
	public ClientFormData prepareCreate(ClientFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}

	@Override
	public ClientFormData create(ClientFormData formData) {
		
		return formData;
	}

	@Override
	public ClientFormData load(ClientFormData formData) {
		
		StringBuffer  varname1 = new StringBuffer();
		varname1.append("SELECT name, ");
		varname1.append("       surname, ");
		varname1.append("       town, ");
		varname1.append("       address, ");
		varname1.append("       oib, ");
		varname1.append("       email, ");
		varname1.append("       date_of_birth, ");
		varname1.append("       phone ");
		varname1.append("FROM   client ");
		varname1.append("WHERE  id = :userID");
		varname1.append("INTO   :Name,");
		varname1.append("       :Surname, ");
		varname1.append("       :Town, ");
		varname1.append("       :Address, ");
		varname1.append("       :OIB, ");
		varname1.append("       :Email, ");
		varname1.append("       :DateOfBirth, ");
		varname1.append("       :Phone");
		SQL.selectInto(varname1.toString(), formData);
		
		return formData;
	}

	@Override
	public ClientFormData store(ClientFormData formData) {
		
		StringBuffer  varname1 = new StringBuffer();
		varname1.append("UPDATE client ");
		varname1.append("SET    name = :Name, ");
		varname1.append("       surname = :Surname, ");
		varname1.append("       town = :Town, ");
		varname1.append("       address = :Address, ");
		varname1.append("       oib = Cast (:OIB AS BIGINT), ");
		varname1.append("       email = :Email, ");
		varname1.append("       date_of_birth = :DateOfBirth, ");
		varname1.append("       phone = :Phone ");
		varname1.append("WHERE  id = :userID");	
		
		SQL.insert(varname1.toString(), formData);
	
		return formData;
	}
}
