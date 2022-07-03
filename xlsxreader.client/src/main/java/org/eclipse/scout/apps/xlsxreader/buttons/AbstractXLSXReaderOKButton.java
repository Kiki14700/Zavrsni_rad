package org.eclipse.scout.apps.xlsxreader.buttons;

import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.platform.text.TEXTS;

public abstract class AbstractXLSXReaderOKButton extends AbstractOkButton{
	
	@Override
	protected String getConfiguredLabel() {
		
		return TEXTS.get("AbstractXLSXReaderOKButton.Save");
	}
	
	
	@Override
	protected int getConfiguredHorizontalAlignment() {
		return 1;
	}

}
