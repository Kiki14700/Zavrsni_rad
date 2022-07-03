package org.eclipse.scout.apps.xlsxreader.files.klijenti;

import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.server.jdbc.SQL;

public class ClientsFileDetailsService implements IClientsFileDetailsService {
	@Override
	public ClientsFileDetailsFormData prepareCreate(ClientsFileDetailsFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}

	@Override
	public ClientsFileDetailsFormData create(ClientsFileDetailsFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}

	@Override
	public ClientsFileDetailsFormData load(ClientsFileDetailsFormData formData) {

		StringBuffer varname1 = new StringBuffer();
		varname1.append("SELECT f.company_id, ");
		varname1.append("       f.import_type, ");
		varname1.append("       f.import_time, ");
		varname1.append("       To_char(f.import_time, 'HH24:MI:SS'), ");
		varname1.append("       (SELECT Count(0) ");
		varname1.append("        FROM   file_details fd ");
		varname1.append("        WHERE  f.id = fd.file_id ");
		varname1.append("               AND ( fd.message = 'Klijent dodan' ");
		varname1.append("                      OR fd.message = 'Uspješno ažurirano' )) ");
		varname1.append("FROM   file f ");
		varname1.append("WHERE  id = :fileID ");
		varname1.append("INTO   :Company, ");
		varname1.append("       :ImportType, ");
		varname1.append("       :Date, ");
		varname1.append("       :Time, ");
		varname1.append("       :SuccessImport");

		SQL.selectInto(varname1.toString(), formData);

		String stmt = "SELECT code, message FROM file_details WHERE file_id = :fileID AND (message != 'Klijent dodan' AND message != 'Uspješno ažurirano') INTO :{table.Code}, :{table.Info}";

		SQL.selectInto(stmt, formData, new NVPair("table", formData.getImportInfoTable()));
		return formData;
	}

	@Override
	public ClientsFileDetailsFormData store(ClientsFileDetailsFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}
}
