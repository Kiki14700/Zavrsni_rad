package org.eclipse.scout.apps.xlsxreader.aboutpricelist;

import org.eclipse.scout.apps.xlsxreader.aboutpricelist.PricelistForm.MainBox.CancelButton;
import org.eclipse.scout.apps.xlsxreader.aboutpricelist.PricelistForm.MainBox.GroupBox;
import org.eclipse.scout.apps.xlsxreader.aboutpricelist.PricelistForm.MainBox.OkButton;
import org.eclipse.scout.apps.xlsxreader.buttons.AbstractXLSXReaderCancelButton;
import org.eclipse.scout.apps.xlsxreader.buttons.AbstractXLSXReaderOKButton;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractSearchForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipse.scout.apps.xlsxreader.aboutpricelist.PricelistForm.MainBox.GroupBox.NameField;
import org.eclipse.scout.apps.xlsxreader.files.lookup.CompanyLookupCall;
import org.eclipse.scout.apps.xlsxreader.aboutpricelist.PricelistForm.MainBox.GroupBox.CompanyField;
import org.eclipse.scout.apps.xlsxreader.aboutpricelist.PricelistForm.MainBox.GroupBox.NoteField;

@FormData(value = PricelistFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class PricelistForm extends AbstractSearchForm {

	private Integer pricelistID;

	@FormData
	public Integer getPricelistID() {
		return pricelistID;
	}

	@FormData
	public void setPricelistID(Integer pricelistID) {
		this.pricelistID = pricelistID;
	}

	@Override
	protected String getConfiguredTitle() {
		// TODO [Kristian Knezevic] verify translation
		return TEXTS.get("PricelistForm.Pricelist");
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public GroupBox getGroupBox() {
		return getFieldByClass(GroupBox.class);
	}

	public CompanyField getCompanyField() {
		return getFieldByClass(CompanyField.class);
	}

	public NoteField getNoteField() {
		return getFieldByClass(NoteField.class);
	}

	public NameField getNameField() {
		return getFieldByClass(NameField.class);
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
				return 1;
			}

			@Order(1000)
			public class NameField extends AbstractStringField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("PricelistForm.MainBox.GroupBox.Name");
				}

				@Override
				protected int getConfiguredMaxLength() {
					return 128;
				}

				@Override
				protected byte getConfiguredLabelPosition() {
					return LABEL_POSITION_TOP;
				}
			}

			@Order(2000)
			public class CompanyField extends AbstractSmartField<Long> {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("PricelistForm.MainBox.GroupBox.Company");
				}

				@Override
				protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
					return CompanyLookupCall.class;
				}

				@Override
				protected byte getConfiguredLabelPosition() {
					return LABEL_POSITION_TOP;
				}
			}

			@Order(3000)
			public class NoteField extends AbstractStringField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("PricelistForm.MainBox.GroupBox.Note");
				}

				@Override
				protected int getConfiguredMaxLength() {
					return 128;
				}

				@Override
				protected byte getConfiguredLabelPosition() {
					return LABEL_POSITION_TOP;
				}

			}

		}

		@Order(2000)
		public class OkButton extends AbstractXLSXReaderOKButton {

		}

		@Order(3000)
		public class CancelButton extends AbstractXLSXReaderCancelButton {

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
			PricelistFormData formData = new PricelistFormData();
			exportFormData(formData);
			formData = BEANS.get(IPricelistService.class).prepareCreate(formData);
			importFormData(formData);
		}

		@Override
		protected void execStore() {
			PricelistFormData formData = new PricelistFormData();
			exportFormData(formData);
			formData = BEANS.get(IPricelistService.class).create(formData);
			importFormData(formData);
		}
	}

	public class ModifyHandler extends AbstractFormHandler {
		@Override
		protected void execLoad() {
			PricelistFormData formData = new PricelistFormData();
			exportFormData(formData);
			formData = BEANS.get(IPricelistService.class).load(formData);
			importFormData(formData);
		}

		@Override
		protected void execStore() {
			PricelistFormData formData = new PricelistFormData();
			exportFormData(formData);
			formData = BEANS.get(IPricelistService.class).store(formData);
			importFormData(formData);
		}
	}
}
