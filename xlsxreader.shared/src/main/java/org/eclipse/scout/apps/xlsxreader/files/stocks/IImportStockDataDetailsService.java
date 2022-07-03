package org.eclipse.scout.apps.xlsxreader.files.stocks;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IImportStockDataDetailsService extends IService {
	ImportStockDataDetailsFormData prepareCreate(ImportStockDataDetailsFormData formData);

	ImportStockDataDetailsFormData create(ImportStockDataDetailsFormData formData);

	ImportStockDataDetailsFormData load(ImportStockDataDetailsFormData formData);

	ImportStockDataDetailsFormData store(ImportStockDataDetailsFormData formData);
}
