package org.eclipse.scout.apps.xlsxreader.files.prices;

import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.server.jdbc.SQL;

public class PricesFileDetailsService implements IPricesFileDetailsService {
	@Override
	public PricesFileDetailsFormData prepareCreate(PricesFileDetailsFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}

	@Override
	public PricesFileDetailsFormData create(PricesFileDetailsFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}

	@Override
	public PricesFileDetailsFormData load(PricesFileDetailsFormData formData) {

		StringBuffer varname1 = new StringBuffer();
		varname1.append("SELECT f.company_id, ");
		varname1.append("       f.import_type, ");
		varname1.append("       f.pricelist_id, ");
		varname1.append("       f.import_time, ");
		varname1.append("       To_char(f.import_time, 'HH24:MI:SS'), ");
		varname1.append("       (SELECT Count(0) ");
		varname1.append("        FROM   file_details fd ");
		varname1.append("        WHERE  f.id = fd.file_id ");
		varname1.append("               AND ( fd.message = 'Cijena dodana' ");
		varname1.append("                      OR fd.message = 'Uspješno ažurirano' )) ");
		varname1.append("FROM   file f ");
		varname1.append("WHERE  id = :fileID ");
		varname1.append("INTO   :Company, ");
		varname1.append("       :ImportType, ");
		varname1.append("       :Pricelist, ");
		varname1.append("       :Date, ");
		varname1.append("       :Time, ");
		varname1.append("       :SuccessImport");

		SQL.selectInto(varname1.toString(), formData);

		String stmt = "SELECT code, message FROM file_details WHERE file_id = :fileID"
				+ " AND (message = 'Ne postoji usluga s navedenom šifrom'"
				+ " OR message = 'Cijena i porez već postoje za odabrani datum i uslugu')"
				+ " INTO :{table.Code}, :{table.Info}";

		SQL.selectInto(stmt, formData, new NVPair("table", formData.getImportInfoTable()));
		return formData;
	}

	@Override
	public PricesFileDetailsFormData store(PricesFileDetailsFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}
}
