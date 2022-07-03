package org.eclipse.scout.apps.xlsxreader.files;

import java.util.List;

import org.eclipse.scout.apps.xlsxreader.files.klijenti.ImportClientsNodePage;
import org.eclipse.scout.apps.xlsxreader.files.prices.ImportPricesNodePage;
import org.eclipse.scout.apps.xlsxreader.files.services.ImportServicesNodePage;
import org.eclipse.scout.apps.xlsxreader.files.stocks.StocksNodePage;
import org.eclipse.scout.apps.xlsxreader.shared.Icons;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.text.TEXTS;

public class ImportFilesNodePage extends AbstractPageWithNodes{
	
	@Override
	protected void execCreateChildPages(List<IPage<?>> pageList) {
		
		
		pageList.add(new ImportClientsNodePage());
		pageList.add(new ImportServicesNodePage());
		pageList.add(new ImportPricesNodePage());
		pageList.add(new StocksNodePage());
		super.execCreateChildPages(pageList);
	}

	@Override
	protected String getConfiguredOverviewIconId() {
		return Icons.File;
		}

	@Override
	protected String getConfiguredIconId() {
		return Icons.File;
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("ImportFilesNodePage.DataImport");
	}


}
