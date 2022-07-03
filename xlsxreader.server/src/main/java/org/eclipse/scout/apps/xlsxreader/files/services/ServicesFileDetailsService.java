package org.eclipse.scout.apps.xlsxreader.files.services;

import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.server.jdbc.SQL;

public class ServicesFileDetailsService implements IServicesFileDetailsService {
	@Override
	public ServicesFileDetailsFormData prepareCreate(ServicesFileDetailsFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}

	@Override
	public ServicesFileDetailsFormData create(ServicesFileDetailsFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}

	@Override
	public ServicesFileDetailsFormData load(ServicesFileDetailsFormData formData) {
		
		
		StringBuffer varname1 = new StringBuffer();
		varname1.append("SELECT f.company_id, ");
		varname1.append("       f.import_type, ");
		varname1.append("       f.import_time, ");
		varname1.append("       To_char(f.import_time, 'HH24:MI:SS'), ");
		varname1.append("       (SELECT Count(0) ");
		varname1.append("        FROM   file_details fd ");
		varname1.append("        WHERE  f.id = fd.file_id ");
		varname1.append("               AND ( fd.message = 'Usluga dodana' ");
		varname1.append("                      OR fd.message = 'Uspješno ažurirano' )) ");
		varname1.append("FROM   file f ");
		varname1.append("WHERE  id = :fileID ");
		varname1.append("INTO   :Company, ");
		varname1.append("       :ImportType, ");
		varname1.append("       :Date, ");
		varname1.append("       :Time, ");
		varname1.append("       :SuccessImport");

		SQL.selectInto(varname1.toString(), formData);

		String stmt = "SELECT code, message FROM file_details WHERE file_id = :fileID"
				+ " AND (message != 'Usluga dodana' AND message != 'Uspješno ažurirano') "
				+ " INTO :{table.Code}, :{table.Info}";

		SQL.selectInto(stmt, formData, new NVPair("table", formData.getImportInfoTable()));
		
		
		return formData;
		
		
	}

	@Override
	public ServicesFileDetailsFormData store(ServicesFileDetailsFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}
}
