package org.eclipse.scout.apps.xlsxreader.aboutpricelist;

import org.eclipse.scout.apps.xlsxreader.aboutpricelist.PricelistSearchForm.MainBox.CancelButton;
import org.eclipse.scout.apps.xlsxreader.aboutpricelist.PricelistSearchForm.MainBox.GroupBox;
import org.eclipse.scout.apps.xlsxreader.aboutpricelist.PricelistSearchForm.MainBox.OkButton;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractSearchForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractResetButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractSearchButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipse.scout.apps.xlsxreader.aboutpricelist.PricelistSearchForm.MainBox.GroupBox.PricelistsNameField;
import org.eclipse.scout.apps.xlsxreader.files.lookup.CompanyLookupCall;
import org.eclipse.scout.apps.xlsxreader.aboutpricelist.PricelistSearchForm.MainBox.GroupBox.PricelistsCompanyField;

@FormData(value = PricelistSearchFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class PricelistSearchForm extends AbstractSearchForm {
	@Override
	protected String getConfiguredTitle() {
		// TODO [Kristian Knezevic] verify translation
		return TEXTS.get("PricelistSearch.Search");
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public GroupBox getGroupBox() {
		return getFieldByClass(GroupBox.class);
	}

	public PricelistsNameField getPricelistsNameField() {
		return getFieldByClass(PricelistsNameField.class);
	}

	public PricelistsCompanyField getPricelistsCompanyField() {
		return getFieldByClass(PricelistsCompanyField.class);
	}

	public OkButton getOkButton() {
		return getFieldByClass(OkButton.class);
	}

	public CancelButton getCancelButton() {
		return getFieldByClass(CancelButton.class);
	}

	@Order(1000)
	public class MainBox extends AbstractGroupBox {
		@Order(1000)
		public class GroupBox extends AbstractGroupBox {
			
			@Override
			protected int getConfiguredGridColumnCount() {
				return 2;
			}

			@Order(1000)
			public class PricelistsNameField extends AbstractStringField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("PricelistSearch.MainBox.GroupBox.Name");
				}

				@Override
				protected int getConfiguredMaxLength() {
					return 128;
				}
			}

			@Order(2000)
			public class PricelistsCompanyField extends AbstractSmartField<Long> {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("PricelistSearch.MainBox.GroupBox.Company");
				}
				
				@Override
				protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
					return CompanyLookupCall.class;
				}
			}
			
			

		}

		@Order(2000)
		public class OkButton extends AbstractSearchButton {

		}

		@Order(3000)
		public class CancelButton extends AbstractResetButton {

		}
	}

	public void startModify() {
		startInternalExclusive(new ModifyHandler());
	}

	public void startNew() {
		startInternal(new NewHandler());
	}

	public class NewHandler extends AbstractFormHandler {
		@Override
		protected void execLoad() {
		}

		@Override
		protected void execStore() {
		}
	}

	public class ModifyHandler extends AbstractFormHandler {
		@Override
		protected void execLoad() {
		}

		@Override
		protected void execStore() {
		}
	}
}
