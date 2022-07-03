package org.eclipse.scout.apps.xlsxreader.files.prices;

import org.eclipse.scout.apps.xlsxreader.files.lookup.CompanyLookupCall;
import org.eclipse.scout.apps.xlsxreader.files.prices.PricesFileSearchForm.MainBox.CancelButton;
import org.eclipse.scout.apps.xlsxreader.files.prices.PricesFileSearchForm.MainBox.GroupBox;
import org.eclipse.scout.apps.xlsxreader.files.prices.PricesFileSearchForm.MainBox.OkButton;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractSearchForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractResetButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractSearchButton;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipse.scout.apps.xlsxreader.files.prices.PricesFileSearchForm.MainBox.GroupBox.PricesNameField;
import org.eclipse.scout.apps.xlsxreader.files.prices.PricesFileSearchForm.MainBox.GroupBox.PriceImportTypeField;
import org.eclipse.scout.apps.xlsxreader.files.prices.PricesFileSearchForm.MainBox.GroupBox.PricesCompanyField;
import org.eclipse.scout.apps.xlsxreader.files.prices.PricesFileSearchForm.MainBox.GroupBox.PeriodBox;
import org.eclipse.scout.apps.xlsxreader.files.prices.PricesFileSearchForm.MainBox.GroupBox.PeriodBox.DateFromField;
import org.eclipse.scout.apps.xlsxreader.files.prices.PricesFileSearchForm.MainBox.GroupBox.PeriodBox.DateToField;
import org.eclipse.scout.apps.xlsxreader.lookup.ImportTypeLookupCall;

@FormData(value = PricesFileSearchFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class PricesFileSearchForm extends AbstractSearchForm {
	@Override
	protected String getConfiguredTitle() {
		// TODO [Kristian Knezevic] verify translation
		return TEXTS.get("PricesFileSearch.Search");
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public GroupBox getGroupBox() {
		return getFieldByClass(GroupBox.class);
	}

	public PricesNameField getPricesFileField() {
		return getFieldByClass(PricesNameField.class);
	}

	public PriceImportTypeField getPriceImportTypeField() {
		return getFieldByClass(PriceImportTypeField.class);
	}

	public PricesCompanyField getPricesCompanyField() {
		return getFieldByClass(PricesCompanyField.class);
	}

	public PeriodBox getPeriodBox() {
		return getFieldByClass(PeriodBox.class);
	}

	public DateFromField getDateFromField() {
		return getFieldByClass(DateFromField.class);
	}

	public DateToField getDateToField() {
		return getFieldByClass(DateToField.class);
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
			public class PricesNameField extends AbstractStringField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("PricesFileSearch.MainBox.GroupBox.Name");
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
			public class PriceImportTypeField extends AbstractSmartField<Long> {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("PricesFileSearch.MainBox.GroupBox.ImportType");
				}
				

				@Override
				protected byte getConfiguredLabelPosition() {
					return LABEL_POSITION_TOP;
				}
				
				@Override
				protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
					return ImportTypeLookupCall.class;
				}
			}

			@Order(3000)
			public class PricesCompanyField extends AbstractSmartField<Long> {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("PricesFileSearch.MainBox.GroupBox.Company");
				}
				

				@Override
				protected byte getConfiguredLabelPosition() {
					return LABEL_POSITION_TOP;
				}
				
				@Override
				protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
					return CompanyLookupCall.class;
				}
			}

			@Order(4000)
			public class PeriodBox extends AbstractSequenceBox {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("PricesFileSearch.MainBox.GroupBox.SequenceBox.PeriodBox");
				}

				@Override
				protected boolean getConfiguredAutoCheckFromTo() {
					return true;
				}
				
				@Override
				protected boolean getConfiguredLabelVisible() {
					return false;
				}

				@Order(1000)
				public class DateFromField extends AbstractDateField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("PricesFileSearch.MainBox.GroupBox.SequenceBox.PeriodBox.DateFrom");
					}
					
					@Override
					protected byte getConfiguredLabelPosition() {
						return LABEL_POSITION_TOP;
					}
					
					@Override
					protected String getConfiguredFormat() {
						return "dd.MM.yyyy";
					}
				}

				@Order(2000)
				public class DateToField extends AbstractDateField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("PricesFileSearch.MainBox.GroupBox.SequenceBox.PeriodBox.DateTo");
					}
					
					@Override
					protected byte getConfiguredLabelPosition() {
						return LABEL_POSITION_TOP;
					}
					
					@Override
					protected String getConfiguredFormat() {
						return "dd.MM.yyyy";
					}
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
