package org.eclipse.scout.apps.xlsxreader.aboutclients;

import java.util.List;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IClientService extends IService {
	ClientFormData prepareCreate(ClientFormData formData);

	ClientFormData create(ClientFormData formData);

	ClientFormData load(ClientFormData formData);

	ClientFormData store(ClientFormData formData);


}
