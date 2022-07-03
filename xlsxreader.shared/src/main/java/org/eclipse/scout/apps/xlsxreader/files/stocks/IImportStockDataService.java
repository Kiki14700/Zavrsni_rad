package org.eclipse.scout.apps.xlsxreader.files.stocks;

import java.util.List;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface IImportStockDataService extends IService {
	ImportStockDataTablePageData getImportStockDataTableData(ImportStockDataSearchFormData filter);

	ImportStockDataFormData prepareCreate(ImportStockDataFormData formData);

	ImportStockDataFormData create(ImportStockDataFormData formData);

	ImportStockDataFormData load(ImportStockDataFormData formData);

	ImportStockDataFormData store(ImportStockDataFormData formData);

	byte[] getFileContent(Integer fileId);

	void deleteFile(List<Integer> fileID);
}
