package org.eclipse.scout.apps.xlsxreader.aboutservices;

import org.eclipse.scout.apps.xlsxreader.aboutservices.ServicesTablePageData;
import org.eclipse.scout.apps.xlsxreader.files.services.ServicesFilesSearchFormData;
import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface IServicesService extends IService {
	ServicesTablePageData getServicesTableData(ServicesSearchFormData filter);
}
