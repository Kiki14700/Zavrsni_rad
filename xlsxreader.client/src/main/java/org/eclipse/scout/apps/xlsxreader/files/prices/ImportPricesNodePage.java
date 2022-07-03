package org.eclipse.scout.apps.xlsxreader.files.prices;

import java.util.List;

import org.eclipse.scout.apps.xlsxreader.shared.Icons;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.AbstractIcons;

public class ImportPricesNodePage extends AbstractPageWithNodes{
	
	@Override
	protected void execCreateChildPages(List<IPage<?>> pageList) {
		
		pageList.add(new ImportPricesTablePage());

		super.execCreateChildPages(pageList);
	}
	@Override
	protected String getConfiguredIconId() {
		return AbstractIcons.DiagramLine;
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("ImportPricesNodePage.Prices");
	}

}
