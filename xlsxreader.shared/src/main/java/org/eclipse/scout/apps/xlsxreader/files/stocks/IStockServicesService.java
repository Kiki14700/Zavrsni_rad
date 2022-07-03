package org.eclipse.scout.apps.xlsxreader.files.stocks;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface IStockServicesService extends IService {
	StockServicesTablePageData getStockServicesTableData(StockServicesSearchFormData filter);
}
