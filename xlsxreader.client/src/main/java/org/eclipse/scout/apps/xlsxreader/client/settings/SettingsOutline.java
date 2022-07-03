package org.eclipse.scout.apps.xlsxreader.client.settings;

import java.util.List;

import org.eclipse.scout.apps.xlsxreader.files.ImportFilesNodePage;
import org.eclipse.scout.apps.xlsxreader.files.services.ImportServicesNodePage;
import org.eclipse.scout.apps.xlsxreader.shared.Icons;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;

/**
 * @author Kristian Knezevic
 */
@Order(3000)
public class SettingsOutline extends AbstractOutline {
	
	
	@Override
	protected void execCreateChildPages(List<IPage<?>> pageList) {
		pageList.add(new ImportFilesNodePage());
		super.execCreateChildPages(pageList);
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("SettingsOutline.Settings");
	}

	@Override
	protected String getConfiguredIconId() {
		return Icons.Gear;
	}
}
