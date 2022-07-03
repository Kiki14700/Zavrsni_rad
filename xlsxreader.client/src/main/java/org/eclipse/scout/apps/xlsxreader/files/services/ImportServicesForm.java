package org.eclipse.scout.apps.xlsxreader.files.services;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.eclipse.scout.apps.xlsxreader.beans.ClientBean;
import org.eclipse.scout.apps.xlsxreader.beans.ServiceBean;
import org.eclipse.scout.apps.xlsxreader.buttons.AbstractXLSXReaderCancelButton;
import org.eclipse.scout.apps.xlsxreader.buttons.AbstractXLSXReaderOKButton;
import org.eclipse.scout.apps.xlsxreader.constants.Constants;
import org.eclipse.scout.apps.xlsxreader.files.lookup.CompanyLookupCall;
import org.eclipse.scout.apps.xlsxreader.files.services.ImportServicesForm.MainBox.CancelButton;
import org.eclipse.scout.apps.xlsxreader.files.services.ImportServicesForm.MainBox.GroupBox;
import org.eclipse.scout.apps.xlsxreader.files.services.ImportServicesForm.MainBox.OkButton;
import org.eclipse.scout.apps.xlsxreader.lookup.ImportTypeLookupCall;
import org.eclipse.scout.apps.xlsxreader.xlsxhelper.ScoutXlsxSpreadsheetAdapter;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractSearchForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.filechooserfield.AbstractFileChooserField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.IOUtility;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipse.scout.apps.xlsxreader.files.services.ImportServicesForm.MainBox.GroupBox.CompanyField;
import org.eclipse.scout.apps.xlsxreader.files.services.ImportServicesForm.MainBox.GroupBox.ImportTypeField;
import org.eclipse.scout.apps.xlsxreader.files.services.ImportServicesForm.MainBox.GroupBox.ImportFileField;

@FormData(value = ImportServicesFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class ImportServicesForm extends AbstractSearchForm {

	private Integer IDFile;
	private ArrayList importDetailsData;

	@FormData
	public Integer getIDFile() {
		return IDFile;
	}

	@FormData
	public void setIDFile(Integer iDFile) {
		IDFile = iDFile;
	}

	@FormData
	public ArrayList getImportDetailsData() {
		return importDetailsData;
	}

	@FormData
	public void setImportDetailsData(ArrayList importDetailsData) {
		this.importDetailsData = importDetailsData;
	}

	@Override
	protected String getConfiguredTitle() {
		// TODO [Kristian Knezevic] verify translation
		return TEXTS.get("ImportServices.ImportServices");
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

	public ImportTypeField getImportTypeField() {
		return getFieldByClass(ImportTypeField.class);
	}

	public ImportFileField getImportFileField() {
		return getFieldByClass(ImportFileField.class);
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
					return TEXTS.get("ImportServices.MainBox.GroupBox.Company");
				}

				@Override
				protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
					return CompanyLookupCall.class;
				}

				@Override
				protected boolean getConfiguredMandatory() {
					return true;
				}
			}

			@Order(2000)
			public class ImportTypeField extends AbstractSmartField<Long> {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("ImportServices.MainBox.GroupBox.ImportType");
				}

				@Override
				protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
					return ImportTypeLookupCall.class;
				}

				@Override
				protected boolean getConfiguredMandatory() {
					return true;
				}
			}

			@Order(3000)
			public class ImportFileField extends AbstractFileChooserField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("ImportServices.MainBox.GroupBox.File");
				}

				@Override
				protected boolean getConfiguredMandatory() {
					return true;
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
			ImportServicesFormData formData = new ImportServicesFormData();
			exportFormData(formData);
			formData = BEANS.get(IImportServicesService.class).prepareCreate(formData);
			importFormData(formData);
		}

		@Override
		protected void execStore() {

			String extension = IOUtility.getFileExtension(getImportFileField().getValue().getFilename());

			if (!extension.equals("xlsx")) {
				throw new VetoException(TEXTS.get("ImportServiceForm.WrongFileFormat"));
			}

			if (getImportFileField().getValue() != null) {
				byte[] importFile = getImportFileField().getValue().getContent();
				InputStream is = new ByteArrayInputStream(importFile);

				if (getImportFileField().getFileSize() > 5000000) {
					throw new VetoException(TEXTS.get("ImportServiceForm.FileLargerThan5MB"));

				} else {
					if (getImportTypeField().getValue().intValue() == Constants.Dodavanje_novih_stavki) {
						ArrayList<ServiceBean> beans = (ArrayList<ServiceBean>) ScoutXlsxSpreadsheetAdapter
								.newServiceBeanFromXLSXFile(is);
						if (beans.size() == 0) {
							throw new VetoException(TEXTS.get("ImportServiceForm.WrongDataFormat"));
						}
						setImportDetailsData(beans);

					} else if (getImportTypeField().getValue().intValue() == Constants.Azuriranje) {
						ArrayList<ServiceBean> beans = (ArrayList<ServiceBean>) ScoutXlsxSpreadsheetAdapter
								.updateServiceBeanFromXLSXFile(is);
						if (beans.size() == 0) {
							throw new VetoException(TEXTS.get("ImportServiceForm.WrongDataFormat"));
						}
						setImportDetailsData(beans);

					}

				}

			}

			ImportServicesFormData formData = new ImportServicesFormData();
			exportFormData(formData);
			formData = BEANS.get(IImportServicesService.class).create(formData);
			importFormData(formData);
		}
	}

	public class ModifyHandler extends AbstractFormHandler {
		@Override
		protected void execLoad() {
			ImportServicesFormData formData = new ImportServicesFormData();
			exportFormData(formData);
			formData = BEANS.get(IImportServicesService.class).load(formData);
			importFormData(formData);
		}

		@Override
		protected void execStore() {
			ImportServicesFormData formData = new ImportServicesFormData();
			exportFormData(formData);
			formData = BEANS.get(IImportServicesService.class).store(formData);
			importFormData(formData);
		}
	}
}
