package org.eclipse.scout.apps.xlsxreader.aboutpricelist;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IPricelistService extends IService {
	PricelistFormData prepareCreate(PricelistFormData formData);

	PricelistFormData create(PricelistFormData formData);

	PricelistFormData load(PricelistFormData formData);

	PricelistFormData store(PricelistFormData formData);
}
