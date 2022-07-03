package org.eclipse.scout.apps.xlsxreader.common.forms;

import java.util.List;
import java.util.Set;

import org.eclipse.scout.apps.xlsxreader.aboutservices.IUndefinedServicesService;
import org.eclipse.scout.apps.xlsxreader.aboutservices.UndefinedServicesFormData.UndefinedServicesTable.UndefinedServicesTableRowData;
import org.eclipse.scout.apps.xlsxreader.buttons.AbstractXLSXReaderCancelButton;
import org.eclipse.scout.apps.xlsxreader.buttons.AbstractXLSXReaderOKButton;
import org.eclipse.scout.apps.xlsxreader.common.forms.XlsxTemplateForm.MainBox.CancelButton;
import org.eclipse.scout.apps.xlsxreader.common.forms.XlsxTemplateForm.MainBox.GroupBox;
import org.eclipse.scout.apps.xlsxreader.common.forms.XlsxTemplateForm.MainBox.OkButton;
import org.eclipse.scout.apps.xlsxreader.common.forms.XlsxTemplateFormData.TemplateTable.TemplateTableRowData;
import org.eclipse.scout.apps.xlsxreader.menus.AbstractDeleteMenu;
import org.eclipse.scout.apps.xlsxreader.menus.AbstractXLSXReaderDownloadMenu;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.action.keystroke.IKeyStroke;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractIntegerColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.IDesktop;
import org.eclipse.scout.rt.client.ui.desktop.OpenUriAction;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.filechooserfield.AbstractFileChooserField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.tablefield.AbstractTableField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.resource.BinaryResource;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.platform.util.IOUtility;
import org.eclipse.scout.apps.xlsxreader.common.forms.XlsxTemplateForm.MainBox.GroupBox.ImportBox;
import org.eclipse.scout.apps.xlsxreader.common.forms.XlsxTemplateForm.MainBox.GroupBox.ImportBox.ImportFileField;
import org.eclipse.scout.apps.xlsxreader.common.forms.XlsxTemplateForm.MainBox.GroupBox.ImportBox.AddButton;
import org.eclipse.scout.apps.xlsxreader.common.forms.XlsxTemplateForm.MainBox.GroupBox.TemplateTableField;

@FormData(value = XlsxTemplateFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class XlsxTemplateForm extends AbstractForm {

	private Long formID;

	@FormData
	public Long getFormID() {
		return formID;
	}

	@FormData
	public void setFormID(Long formID) {
		this.formID = formID;
	}

	@Override
	protected String getConfiguredTitle() {
		// TODO [Kristian Knezevic] verify translation
		return TEXTS.get("XlsxTemplate.Template");
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public GroupBox getGroupBox() {
		return getFieldByClass(GroupBox.class);
	}

	public ImportBox getImportBox() {
		return getFieldByClass(ImportBox.class);
	}

	public ImportFileField getImportFileField() {
		return getFieldByClass(ImportFileField.class);
	}

	public AddButton getAddButton() {
		return getFieldByClass(AddButton.class);
	}

	public TemplateTableField getTemplateTableField() {
		return getFieldByClass(TemplateTableField.class);
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
			public class ImportBox extends AbstractSequenceBox {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("XlsxTemplate.MainBox.GroupBox.ImportBox");
				}

				@Override
				protected boolean getConfiguredLabelVisible() {
					return false;
				}

				@Override
				protected boolean getConfiguredAutoCheckFromTo() {
					return false;
				}

				@Order(1000)
				public class ImportFileField extends AbstractFileChooserField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("XlsxTemplate.MainBox.GroupBox.ImportBox.ImportFile");
					}
					
					
				}

				@Order(2000)
				public class AddButton extends AbstractButton {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("XlsxTemplate.MainBox.GroupBox.ImportBox.Add");
					}

					@Override
					protected void execClickAction() {

						String fileName = IOUtility.getFileName(getImportFileField().getValue().getFilename());
						byte[] content = getImportFileField().getValue().getContent();

						BEANS.get(IXlsxTemplateService.class).addTemplateFile(formID, fileName, content);

						fetchTemplateFiles();

						getImportFileField().setValue(null);
					}
				}

			}

			@Order(2000)
			public class TemplateTableField extends AbstractTableField<TemplateTableField.Table> {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("XlsxTemplate.MainBox.GroupBox.Table.Template");
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

					@Order(1000)
					public class DownloadMenu extends AbstractXLSXReaderDownloadMenu {
						@Override
						protected void execAction() {

							Integer fileID = getTable().getIDColumn().getSelectedValue();

							byte[] content = BEANS.get(IXlsxTemplateService.class).getTemplateFile(fileID);

							IDesktop.CURRENT.get().openUri(
									new BinaryResource(getTable().getNameColumn().getSelectedValue(), content),
									OpenUriAction.DOWNLOAD);

						}
					}

					@Order(2000)
					public class DeleteMenu extends AbstractDeleteMenu {

						@Override
						protected void execAction() {
							
							List<Integer> fileID = getTable().getIDColumn().getSelectedValues();
							BEANS.get(IXlsxTemplateService.class).deleteFile(fileID);
							fetchTemplateFiles();
						}
					}

					@Override
					protected boolean getConfiguredAutoResizeColumns() {
						return true;
					}

					public IDColumn getIDColumn() {
						return getColumnSet().getColumnByClass(IDColumn.class);
					}

					public NameColumn getNameColumn() {
						return getColumnSet().getColumnByClass(NameColumn.class);
					}

					@Order(1000)
					public class IDColumn extends AbstractIntegerColumn {
						@Override
						protected String getConfiguredHeaderText() {
							return TEXTS.get("XlsxTemplate.MainBox.GroupBox.Table.ID");
						}

						@Override
						protected int getConfiguredWidth() {
							return 100;
						}

						@Override
						protected boolean getConfiguredVisible() {
							return false;
						}
					}

					@Order(2000)
					public class NameColumn extends AbstractStringColumn {
						@Override
						protected String getConfiguredHeaderText() {
							return TEXTS.get("XlsxTemplate.MainBox.GroupBox.Table.TemplateName");
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
		public class OkButton extends AbstractXLSXReaderOKButton {
			@Override
			protected String getConfiguredLabel() {
				return TEXTS.get("PricesFileDetailsForm.MainBox.GroupBox.Close");
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
			XlsxTemplateFormData formData = new XlsxTemplateFormData();
			exportFormData(formData);
			formData = BEANS.get(IXlsxTemplateService.class).prepareCreate(formData);
			importFormData(formData);
		}

		@Override
		protected void execStore() {
			XlsxTemplateFormData formData = new XlsxTemplateFormData();
			exportFormData(formData);
			formData = BEANS.get(IXlsxTemplateService.class).create(formData);
			importFormData(formData);
		}
	}

	public class ModifyHandler extends AbstractFormHandler {
		@Override
		protected void execLoad() {
			XlsxTemplateFormData formData = new XlsxTemplateFormData();
			exportFormData(formData);
			formData = BEANS.get(IXlsxTemplateService.class).load(formData);
			importFormData(formData);
		}

		@Override
		protected void execStore() {
			XlsxTemplateFormData formData = new XlsxTemplateFormData();
			exportFormData(formData);
			formData = BEANS.get(IXlsxTemplateService.class).store(formData);
			importFormData(formData);
		}
	}

	public void fetchTemplateFiles() {

		List<TemplateTableRowData> rowData = BEANS.get(IXlsxTemplateService.class).fetchTempalateFiles(formID);

		getTemplateTableField().getTable().importFromTableRowBeanData(rowData, TemplateTableRowData.class);
	}
}
