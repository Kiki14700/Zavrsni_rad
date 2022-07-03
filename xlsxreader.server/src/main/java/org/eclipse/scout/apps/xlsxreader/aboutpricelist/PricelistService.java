package org.eclipse.scout.apps.xlsxreader.aboutpricelist;

import org.eclipse.scout.rt.platform.holders.IntegerHolder;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.server.jdbc.SQL;

public class PricelistService implements IPricelistService {
	@Override
	public PricelistFormData prepareCreate(PricelistFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}

	@Override
	public PricelistFormData create(PricelistFormData formData) {

		int companyID = formData.getCompany().getValue().intValue();
		int newCode = getLastCode(companyID) + 1;

		StringBuffer varname1 = new StringBuffer();
		varname1.append("INSERT INTO pricelist ");
		varname1.append("            (NAME, ");
		varname1.append("             code, ");
		varname1.append("             note, ");
		varname1.append("             company_id) ");
		varname1.append("VALUES      (:Name, ");
		varname1.append("             :newCode, ");
		varname1.append("             :Note, ");
		varname1.append("             :Company)");

		SQL.insert(varname1.toString(), new NVPair("newCode", newCode), formData);

		return formData;
	}

	@Override
	public PricelistFormData load(PricelistFormData formData) {

		StringBuffer varname1 = new StringBuffer();
		varname1.append("SELECT name, ");
		varname1.append("       note, ");
		varname1.append("       company_id ");
		varname1.append("FROM   pricelist ");
		varname1.append("WHERE  id = :pricelistID ");
		varname1.append("INTO   :Name, ");
		varname1.append("       :Note, ");
		varname1.append("       :Company");

		SQL.selectInto(varname1.toString(), formData);

		return formData;
	}

	@Override
	public PricelistFormData store(PricelistFormData formData) {

		StringBuffer varname1 = new StringBuffer();
		varname1.append("UPDATE pricelist ");
		varname1.append("SET    NAME = :Name, ");
		varname1.append("       note = :Note, ");
		varname1.append("       company_id = :Company ");
		varname1.append("WHERE  id = :pricelistID");

		SQL.update(varname1.toString(), formData);

		return formData;
	}

	private Integer getLastCode(int companyID) {

		IntegerHolder lastCode = new IntegerHolder();

		StringBuffer varname1 = new StringBuffer();
		varname1.append("SELECT   Cast (code AS INTEGER) ");
		varname1.append("FROM     pricelist ");
		varname1.append("INTO     :lastCode ");
		varname1.append("WHERE    company_id = :companyID ");
		varname1.append("ORDER BY code DESC limit 1");

		SQL.selectInto(varname1.toString(), new NVPair("lastCode", lastCode), new NVPair("companyID", companyID));

		if (lastCode.getValue() == null) {
			lastCode.setValue(100);
		}

		return lastCode.getValue().intValue();
	}
}
