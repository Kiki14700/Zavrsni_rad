package org.eclipse.scout.apps.xlsxreader.files.lookup;

import org.eclipse.scout.rt.shared.services.lookup.ILookupService;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;

public class StockLookupCall extends LookupCall<Long> {
	private static final long serialVersionUID = 1L;
	
	private Long companyID;

	@Override
	protected Class<? extends ILookupService<Long>> getConfiguredService() {
		return IStockLookupService.class;
	}

	public Long getCompanyID() {
		return companyID;
	}

	public void setCompanyID(Long companyID) {
		this.companyID = companyID;
	}
	
	
	
}
