package org.eclipse.scout.apps.xlsxreader.files.services;

import java.util.List;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface IImportServicesService extends IService {
	ImportServicesTablePageData getImportServicesTableData(ServicesFilesSearchFormData filter);

	ImportServicesFormData prepareCreate(ImportServicesFormData formData);

	ImportServicesFormData create(ImportServicesFormData formData);

	ImportServicesFormData load(ImportServicesFormData formData);

	ImportServicesFormData store(ImportServicesFormData formData);

	byte[] getFileContent(Integer fileId);

	void deleteFile(List<Integer> fileID);
}
