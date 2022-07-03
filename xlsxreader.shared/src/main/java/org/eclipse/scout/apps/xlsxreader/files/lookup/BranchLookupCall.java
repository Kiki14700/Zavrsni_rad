package org.eclipse.scout.apps.xlsxreader.files.lookup;

import org.eclipse.scout.rt.shared.services.lookup.ILookupService;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;

public class BranchLookupCall extends LookupCall<Long> {
	private static final long serialVersionUID = 1L;
	private Long companyID;

	public Long getCompanyID() {
		return companyID;
	}

	public void setCompanyID(Long companyID) {
		this.companyID = companyID;
	}

	@Override
	protected Class<? extends ILookupService<Long>> getConfiguredService() {
		return IBranchLookupService.class;
	}
}
