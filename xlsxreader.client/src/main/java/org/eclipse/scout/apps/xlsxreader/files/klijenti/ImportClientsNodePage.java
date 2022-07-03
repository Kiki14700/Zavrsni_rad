package org.eclipse.scout.apps.xlsxreader.files.klijenti;

import java.util.List;
import org.eclipse.scout.apps.xlsxreader.shared.Icons;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.text.TEXTS;

public class ImportClientsNodePage extends AbstractPageWithNodes{
	

	@Override
	protected void execCreateChildPages(List<IPage<?>> pageList) {
		
		pageList.add(new ImportClientsTablePage());

		super.execCreateChildPages(pageList);
	}
	@Override
	protected String getConfiguredIconId() {
		return Icons.PersonSolid;
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("ImportClientsNodePage.Clients");
	}

}
