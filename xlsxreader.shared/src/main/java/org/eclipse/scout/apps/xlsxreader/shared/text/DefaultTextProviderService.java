package org.eclipse.scout.apps.xlsxreader.shared.text;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.AbstractDynamicNlsTextProviderService;

/**
 * @author Kristian Knezevic
 */
@Order(-2000)
public class DefaultTextProviderService extends AbstractDynamicNlsTextProviderService {
	@Override
	public String getDynamicNlsBaseName() {
		return "org.eclipse.scout.apps.xlsxreader.shared.texts.Texts";
	}
}
