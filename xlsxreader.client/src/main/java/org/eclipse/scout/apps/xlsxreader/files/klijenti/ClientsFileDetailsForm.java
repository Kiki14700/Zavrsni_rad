package org.eclipse.scout.apps.xlsxreader.files.klijenti;

import org.eclipse.scout.apps.xlsxreader.buttons.AbstractXLSXReaderCancelButton;
import org.eclipse.scout.apps.xlsxreader.files.klijenti.ClientsFileDetailsForm.MainBox.CancelButton;
import org.eclipse.scout.apps.xlsxreader.files.klijenti.ClientsFileDetailsForm.MainBox.GroupBox;
import org.eclipse.scout.apps.xlsxreader.files.klijenti.ClientsFileDetailsForm.MainBox.GroupBox.CompanyField;
import org.eclipse.scout.apps.xlsxreader.files.klijenti.ClientsFileDetailsForm.MainBox.GroupBox.DateImportBox;
import org.eclipse.scout.apps.xlsxreader.files.klijenti.ClientsFileDetailsForm.MainBox.GroupBox.DateImportBox.DateField;
import org.eclipse.scout.apps.xlsxreader.files.klijenti.ClientsFileDetailsForm.MainBox.GroupBox.DateImportBox.TimeField;
import org.eclipse.scout.apps.xlsxreader.files.klijenti.ClientsFileDetailsForm.MainBox.GroupBox.ImportInfoTableField;
import org.eclipse.scout.apps.xlsxreader.files.klijenti.ClientsFileDetailsForm.MainBox.GroupBox.ImportTypeField;
import org.eclipse.scout.apps.xlsxreader.files.klijenti.ClientsFileDetailsForm.MainBox.GroupBox.SuccessImportField;
import org.eclipse.scout.apps.xlsxreader.files.klijenti.ClientsFileDetailsForm.MainBox.OkButton;
import org.eclipse.scout.apps.xlsxreader.files.lookup.CompanyLookupCall;
import org.eclipse.scout.apps.xlsxreader.lookup.ImportTypeLookupCall;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.action.keystroke.IKeyStroke;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tablefield.AbstractTableField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.TriState;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipse.scout.apps.xlsxreader.files.klijenti.ClientsFileDetailsForm.MainBox.GroupBox.EmptyField;

@FormData(value = ClientsFileDetailsFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class ClientsFileDetailsForm extends AbstractForm {

	private Integer fileID;

	@FormData
	public Integer getFileID() {
		return fileID;
	}

	@FormData
	public void setFileID(Integer fileID) {
		this.fileID = fileID;
	}

	@Override
	protected String getConfiguredTitle() {
		// TODO [Kristian Knezevic] verify translation
		return TEXTS.get("ClientsFileDetails.FileInspect");
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public GroupBox getGroupBox() {
		return getFieldByClass(GroupBox.class);
	}

	public DateImportBox getDateImportBox() {
		return getFieldByClass(DateImportBox.class);
	}

	public DateField getDateField() {
		return getFieldByClass(DateField.class);
	}

	public TimeField getTimeField() {
		return getFieldByClass(TimeField.class);
	}

	public ImportTypeField getImportTypeField() {
		return getFieldByClass(ImportTypeField.class);
	}

	public SuccessImportField getSuccessImportField() {
		return getFieldByClass(SuccessImportField.class);
	}

	public ImportInfoTableField getImportInfoTableField() {
		return getFieldByClass(ImportInfoTableField.class);
	}

	public EmptyField getEmptyField() {
		return getFieldByClass(EmptyField.class);
	}

	public CompanyField getMySmartField() {
		return getFieldByClass(CompanyField.class);
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
			public class CompanyField extends AbstractSmartField<Long> {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("ClientsFileDetails.MainBox.GroupBox.Company");
				}

				@Override
				protected byte getConfiguredLabelPosition() {
					return LABEL_POSITION_TOP;
				}

				@Override
				protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
					return CompanyLookupCall.class;
				}

				@Override
				protected boolean getConfiguredEnabled() {
					return false;
				}
			}

			@Order(2000)
			public class ImportTypeField extends AbstractSmartField<Long> {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("ClientsFileDetails.MainBox.GroupBox.ImportType");
				}

				@Override
				protected byte getConfiguredLabelPosition() {
					return LABEL_POSITION_TOP;
				}

				@Override
				protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
					return ImportTypeLookupCall.class;
				}

				@Override
				protected boolean getConfiguredEnabled() {
					return false;
				}

			}

			@Order(3000)
			public class SuccessImportField extends AbstractStringField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("ClientsFileDetails.MainBox.GroupBox.SuccessImport");
				}

				@Override
				protected int getConfiguredMaxLength() {
					return 128;
				}

				@Override
				protected byte getConfiguredLabelPosition() {
					return LABEL_POSITION_TOP;
				}

				@Override
				protected boolean getConfiguredEnabled() {
					return false;
				}

			}

			@Order(4000)
			public class DateImportBox extends AbstractSequenceBox {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("ClientsFileDetails.MainBox.GroupBox.DateImportBox");
				}

				@Override
				protected boolean getConfiguredAutoCheckFromTo() {
					return false;
				}

				@Override
				protected boolean getConfiguredLabelVisible() {
					return false;
				}

				@Order(1000)
				public class DateField extends AbstractDateField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("ClientsFileDetails.MainBox.GroupBox.DateImportBox.Date");
					}

					@Override
					protected String getConfiguredFormat() {
						return "dd.MM.yyyy";
					}

					@Override
					protected boolean getConfiguredEnabled() {
						return false;
					}

					@Override
					protected byte getConfiguredLabelPosition() {
						return LABEL_POSITION_TOP;
					}

				}

				@Order(2000)
				public class TimeField extends AbstractStringField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("ClientsFileDetails.MainBox.GroupBox.DateImportBox.Time");
					}

					@Override
					protected int getConfiguredMaxLength() {
						return 128;
					}

					@Override
					protected byte getConfiguredLabelPosition() {
						return LABEL_POSITION_TOP;
					}

					@Override
					protected boolean getConfiguredEnabled() {
						return false;
					}

				}

			}

			@Order(4500)
			@FormData(sdkCommand = FormData.SdkCommand.IGNORE)
			public class EmptyField extends AbstractLabelField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("ClientsFileDetails.MainBox.GroupBox.EmptyField");
				}

				@Override
				protected boolean getConfiguredLabelVisible() {
					return false;
				}
			}

			@Order(5000)
			public class ImportInfoTableField extends AbstractTableField<ImportInfoTableField.Table> {

				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("ClientsFileDetails.MainBox.GroupBox.ImportInfoTableField.UnsuccessImport");
				}

				@Override
				protected int getConfiguredGridH() {
					return 6;
				}

				@Override
				protected byte getConfiguredLabelPosition() {
					return LABEL_POSITION_TOP;
				}

				public class Table extends AbstractTable {

					@Override
					protected boolean getConfiguredAutoResizeColumns() {
						return true;
					}

					@Override
					protected TriState getConfiguredTruncatedCellTooltipEnabled() {

						return TriState.TRUE;
					}

					public InfoColumn getInfoColumn() {
						return getColumnSet().getColumnByClass(InfoColumn.class);
					}

					public CodeColumn getCodeColumn() {
						return getColumnSet().getColumnByClass(CodeColumn.class);
					}

					@Order(1000)
					public class CodeColumn extends AbstractStringColumn {
						@Override
						protected String getConfiguredHeaderText() {
							return TEXTS.get("ClientsFileDetails.MainBox.GroupBox.ImportInfoTableField.Code");
						}

						@Override
						protected int getConfiguredWidth() {
							return 100;
						}
					}

					@Order(2000)
					public class InfoColumn extends AbstractStringColumn {
						@Override
						protected String getConfiguredHeaderText() {
							return TEXTS.get("ClientsFileDetails.MainBox.GroupBox.ImportInfoTableField.Info");
						}

						@Override
						protected int getConfiguredWidth() {
							return 100;
						}
					}

				}

			}

		}

		@Order(2000)
		public class OkButton extends AbstractOkButton {

			@Override
			protected String getConfiguredLabel() {
				return TEXTS.get("ClientsFileDetailsForm.MainBox.GroupBox.Close");
			}

			@Override
			protected int getConfiguredHorizontalAlignment() {
				return 1;
			}

			@Override
			protected String getConfiguredKeyStroke() {
				return IKeyStroke.ESCAPE;
			}

		}

		@Order(3000)
		public class CancelButton extends AbstractXLSXReaderCancelButton {
			@Override
			protected boolean getConfiguredVisible() {
				return false;
			}
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
			ClientsFileDetailsFormData formData = new ClientsFileDetailsFormData();
			exportFormData(formData);
			formData = BEANS.get(IClientsFileDetailsService.class).prepareCreate(formData);
			importFormData(formData);
		}

		@Override
		protected void execStore() {
			ClientsFileDetailsFormData formData = new ClientsFileDetailsFormData();
			exportFormData(formData);
			formData = BEANS.get(IClientsFileDetailsService.class).create(formData);
			importFormData(formData);
		}
	}

	public class ModifyHandler extends AbstractFormHandler {
		@Override
		protected void execLoad() {

			ClientsFileDetailsFormData formData = new ClientsFileDetailsFormData();
			exportFormData(formData);
			formData = BEANS.get(IClientsFileDetailsService.class).load(formData);
			importFormData(formData);
		}

		@Override
		protected void execStore() {
			ClientsFileDetailsFormData formData = new ClientsFileDetailsFormData();
			exportFormData(formData);
			formData = BEANS.get(IClientsFileDetailsService.class).store(formData);
			importFormData(formData);
		}
	}
}
