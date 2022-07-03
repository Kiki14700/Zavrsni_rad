package org.eclipse.scout.apps.xlsxreader.client.work;

import java.util.List;

import org.eclipse.scout.apps.xlsxreader.aboutclients.ClientsTablePage;
import org.eclipse.scout.apps.xlsxreader.aboutpricelist.PricelistsTablePage;
import org.eclipse.scout.apps.xlsxreader.aboutservices.ServicesTablePage;
import org.eclipse.scout.apps.xlsxreader.shared.Icons;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;

/**
 * @author Kristian Knezevic
 */
@Order(1000)
public class WorkOutline extends AbstractOutline {

	@Override
	protected void execCreateChildPages(List<IPage<?>> pageList) {
		pageList.add(new ClientsTablePage());
		pageList.add(new ServicesTablePage());
		pageList.add(new PricelistsTablePage());
		super.execCreateChildPages(pageList);
	
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("WorkOutline.Activities");
	}

	@Override
	protected String getConfiguredIconId() {
		return Icons.Pencil;
	}
}
