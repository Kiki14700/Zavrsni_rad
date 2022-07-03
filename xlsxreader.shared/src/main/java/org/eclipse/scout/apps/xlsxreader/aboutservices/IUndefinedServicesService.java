package org.eclipse.scout.apps.xlsxreader.aboutservices;

import java.util.List;

import org.eclipse.scout.apps.xlsxreader.aboutservices.ServicesTablePageData.ServicesTableRowData;
import org.eclipse.scout.apps.xlsxreader.aboutservices.UndefinedServicesFormData.UndefinedServicesTable.UndefinedServicesTableRowData;
import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IUndefinedServicesService extends IService {
	UndefinedServicesFormData prepareCreate(UndefinedServicesFormData formData);

	UndefinedServicesFormData create(UndefinedServicesFormData formData);

	UndefinedServicesFormData load(UndefinedServicesFormData formData);

	UndefinedServicesFormData store(UndefinedServicesFormData formData);

	List<UndefinedServicesTableRowData> fetchServices(int companyID);



	
}
