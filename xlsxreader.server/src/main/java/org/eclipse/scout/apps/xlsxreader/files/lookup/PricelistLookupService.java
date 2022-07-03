package org.eclipse.scout.apps.xlsxreader.files.lookup;

import java.util.List;

import org.eclipse.scout.rt.server.jdbc.lookup.AbstractSqlLookupService;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;

public class PricelistLookupService extends AbstractSqlLookupService<Long> implements IPricelistLookupService {

	private String getSql(ILookupCall<Long> call) {

		PricelistLookupCall c = (PricelistLookupCall) call;

		StringBuffer varname1 = new StringBuffer();
		varname1.append("SELECT p.id, ");
		varname1.append("       p.name ");
		varname1.append("FROM   pricelist p ");
		varname1.append("WHERE p.company_id = " + c.getCompanyID());
		varname1.append(" <key>AND p.id = :key </key><all></all>");

		return varname1.toString();

	}

	@Override
	public List<ILookupRow<Long>> getDataByAll(ILookupCall<Long> call) {
		return execLoadLookupRows(getSql(call), filterSqlByAll(getSql(call)), call);
	}

	@Override
	public List<ILookupRow<Long>> getDataByText(ILookupCall<Long> call) {
		return execLoadLookupRows(getSql(call), filterSqlByText(getSql(call)), call);
	}

	@Override
	public List<ILookupRow<Long>> getDataByKey(ILookupCall<Long> call) {
		return execLoadLookupRows(getSql(call), filterSqlByKey(getSql(call)), call);
	}

}
