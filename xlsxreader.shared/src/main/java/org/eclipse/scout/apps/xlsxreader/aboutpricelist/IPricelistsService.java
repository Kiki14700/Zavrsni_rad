package org.eclipse.scout.apps.xlsxreader.aboutpricelist;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface IPricelistsService extends IService {
	PricelistsTablePageData getPricelistTableData(PricelistSearchFormData filter);
}
