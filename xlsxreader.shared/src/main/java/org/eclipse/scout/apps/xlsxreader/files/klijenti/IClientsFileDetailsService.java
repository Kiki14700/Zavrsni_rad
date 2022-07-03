package org.eclipse.scout.apps.xlsxreader.files.klijenti;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IClientsFileDetailsService extends IService {
	ClientsFileDetailsFormData prepareCreate(ClientsFileDetailsFormData formData);

	ClientsFileDetailsFormData create(ClientsFileDetailsFormData formData);

	ClientsFileDetailsFormData load(ClientsFileDetailsFormData formData);

	ClientsFileDetailsFormData store(ClientsFileDetailsFormData formData);
}
