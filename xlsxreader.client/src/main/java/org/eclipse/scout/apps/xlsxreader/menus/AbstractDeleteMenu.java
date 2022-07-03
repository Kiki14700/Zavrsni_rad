package org.eclipse.scout.apps.xlsxreader.menus;

import java.util.Set;

import org.eclipse.scout.apps.xlsxreader.shared.Icons;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;

public abstract class AbstractDeleteMenu extends AbstractMenu{
	
	@Override
	protected String getConfiguredText() {
		return TEXTS.get("AbstractDeleteMenu.Delete");
	}

	@Override
	protected String getConfiguredIconId() {
		return Icons.Remove;
	}

	@Override
	protected Set<? extends IMenuType> getConfiguredMenuTypes() {
		return CollectionUtility.hashSet(TableMenuType.SingleSelection, TableMenuType.MultiSelection);
	}
	

}
