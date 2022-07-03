package org.eclipse.scout.apps.xlsxreader.files.prices;

import java.util.List;

import org.eclipse.scout.apps.xlsxreader.files.klijenti.ClientsFileDetailsFormData;
import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface IImportPricesService extends IService {
	ImportPricesTablePageData getImportPricesTableData(PricesFileSearchFormData pricesFileSearchFormData);

	ImportPricesFormData prepareCreate(ImportPricesFormData formData);

	ImportPricesFormData create(ImportPricesFormData formData);

	ImportPricesFormData load(ImportPricesFormData formData);

	ImportPricesFormData store(ImportPricesFormData formData);

	byte[] getFileContent(Integer fileId);

	void deleteFile(List<Integer> fileID);
}
