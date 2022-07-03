package org.eclipse.scout.apps.xlsxreader.aboutclients;

import java.util.List;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface IClientsService extends IService {
	ClientsTablePageData getClientsTableData(SearchFilter filter);

	
}
