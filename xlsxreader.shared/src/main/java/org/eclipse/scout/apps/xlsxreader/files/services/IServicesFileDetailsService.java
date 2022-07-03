package org.eclipse.scout.apps.xlsxreader.files.services;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IServicesFileDetailsService extends IService {
	ServicesFileDetailsFormData prepareCreate(ServicesFileDetailsFormData formData);

	ServicesFileDetailsFormData create(ServicesFileDetailsFormData formData);

	ServicesFileDetailsFormData load(ServicesFileDetailsFormData formData);

	ServicesFileDetailsFormData store(ServicesFileDetailsFormData formData);
}
