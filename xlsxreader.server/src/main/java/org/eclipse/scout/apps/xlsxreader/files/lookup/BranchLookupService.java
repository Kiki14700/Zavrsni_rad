package org.eclipse.scout.apps.xlsxreader.files.lookup;

import java.util.List;

import org.eclipse.scout.rt.server.jdbc.lookup.AbstractSqlLookupService;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;

public class BranchLookupService extends AbstractSqlLookupService<Long> implements IBranchLookupService {
	
	private String getSql(ILookupCall<Long> call) {

		BranchLookupCall c = (BranchLookupCall) call;

		StringBuffer varname1 = new StringBuffer();
		varname1.append("SELECT b.id, ");
		varname1.append("       b.name ");
		varname1.append("FROM   branch b ");
		varname1.append("WHERE  b.company_id = " + c.getCompanyID());
		varname1.append(" <key>AND b.id = :key </key><all></all>");

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
