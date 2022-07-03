package org.eclipse.scout.apps.xlsxreader.files.services;

import java.util.List;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.AbstractIcons;

public class ImportServicesNodePage extends AbstractPageWithNodes{
	
	@Override
	protected void execCreateChildPages(List<IPage<?>> pageList) {
		
		pageList.add(new ImportServicesTablePage());

		super.execCreateChildPages(pageList);
	}
	@Override
	protected String getConfiguredIconId() {
		return AbstractIcons.GroupPlus;
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("ImportServicesNodePage.Services");
	}

}
