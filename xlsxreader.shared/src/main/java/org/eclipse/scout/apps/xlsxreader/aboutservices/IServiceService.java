package org.eclipse.scout.apps.xlsxreader.aboutservices;

import org.eclipse.scout.apps.xlsxreader.aboutservices.ServiceFormData;
import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IServiceService extends IService {
	ServiceFormData prepareCreate(ServiceFormData formData);

	ServiceFormData create(ServiceFormData formData);

	ServiceFormData load(ServiceFormData formData);

	ServiceFormData store(ServiceFormData formData);
}
