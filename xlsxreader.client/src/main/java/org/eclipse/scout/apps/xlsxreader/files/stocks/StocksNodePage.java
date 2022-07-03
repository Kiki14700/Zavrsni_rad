package org.eclipse.scout.apps.xlsxreader.files.stocks;

import java.util.List;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.AbstractIcons;

public class StocksNodePage extends AbstractPageWithNodes{
	
	@Override
	protected void execCreateChildPages(List<IPage<?>> pageList) {
		
		pageList.add(new StockServicesTablePage());
		pageList.add(new ImportStockDataTablePage());

		super.execCreateChildPages(pageList);
	}
	@Override
	protected String getConfiguredIconId() {
		return AbstractIcons.Group;
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("StocksNodePage.Stocks");
	}

}
