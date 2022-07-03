package org.eclipse.scout.apps.xlsxreader.files.prices;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IPricesFileDetailsService extends IService {
	PricesFileDetailsFormData prepareCreate(PricesFileDetailsFormData formData);

	PricesFileDetailsFormData create(PricesFileDetailsFormData formData);

	PricesFileDetailsFormData load(PricesFileDetailsFormData formData);

	PricesFileDetailsFormData store(PricesFileDetailsFormData formData);
}
