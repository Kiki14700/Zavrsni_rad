package org.eclipse.scout.apps.xlsxreader.files.klijenti;

import java.util.List;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface IImportClientsService extends IService {
	ImportClientsTablePageData getImportClientsTableData(ClientsFileSearchFormData filter);

	ImportClientsFormData prepareCreate(ImportClientsFormData formData);

	ImportClientsFormData create(ImportClientsFormData formData);

	ImportClientsFormData load(ImportClientsFormData formData);

	ImportClientsFormData store(ImportClientsFormData formData);

	byte[] getFileContent(Integer fileId);

	void deleteFile(List<Integer> fileID);
}
