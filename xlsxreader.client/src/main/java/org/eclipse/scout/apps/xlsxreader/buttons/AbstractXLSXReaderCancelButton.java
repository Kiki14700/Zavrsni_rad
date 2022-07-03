package org.eclipse.scout.apps.xlsxreader.buttons;

import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.platform.text.TEXTS;

public abstract class AbstractXLSXReaderCancelButton extends AbstractCancelButton{
	
	
	@Override
	protected String getConfiguredLabel() {
		
		return TEXTS.get("AbstractXLSXReaderOKButton.Cancel");
	}
	
	
	@Override
	protected int getConfiguredHorizontalAlignment() {
		return 1;
	}

}
