package org.eclipse.scout.apps.xlsxreader.files.klijenti;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.eclipse.scout.apps.xlsxreader.beans.ClientBean;
import org.eclipse.scout.apps.xlsxreader.buttons.AbstractXLSXReaderCancelButton;
import org.eclipse.scout.apps.xlsxreader.buttons.AbstractXLSXReaderOKButton;
import org.eclipse.scout.apps.xlsxreader.constants.Constants;
import org.eclipse.scout.apps.xlsxreader.files.klijenti.ImportClientsForm.MainBox.CancelButton;
import org.eclipse.scout.apps.xlsxreader.files.klijenti.ImportClientsForm.MainBox.GroupBox;
import org.eclipse.scout.apps.xlsxreader.files.klijenti.ImportClientsForm.MainBox.GroupBox.ChooseFileField;
import org.eclipse.scout.apps.xlsxreader.files.klijenti.ImportClientsForm.MainBox.GroupBox.CompanyField;
import org.eclipse.scout.apps.xlsxreader.files.klijenti.ImportClientsForm.MainBox.GroupBox.ImportTypeField;
import org.eclipse.scout.apps.xlsxreader.files.klijenti.ImportClientsForm.MainBox.OkButton;
import org.eclipse.scout.apps.xlsxreader.files.lookup.CompanyLookupCall;
import org.eclipse.scout.apps.xlsxreader.lookup.ImportTypeLookupCall;
import org.eclipse.scout.apps.xlsxreader.xlsxhelper.ScoutXlsxSpreadsheetAdapter;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.filechooserfield.AbstractFileChooserField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.resource.BinaryResource;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.IOUtility;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@FormData(value = ImportClientsFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class ImportClientsForm extends AbstractForm {

	private ArrayList importDetailsData;
	private Integer IDFile;
	private String clientsCode;

	@FormData
	public ArrayList getImportDetailsData() {
		return importDetailsData;
	}

	@FormData
	public void setImportDetailsData(ArrayList importDetailsData) {
		this.importDetailsData = importDetailsData;
	}

	@FormData
	public Integer getIDFile() {
		return IDFile;
	}

	@FormData
	public void setIDFile(Integer iDFile) {
		IDFile = iDFile;
	}

	@FormData
	public String getClientsCode() {
		return clientsCode;
	}

	@FormData
	public void setClientsCode(String clientsCode) {
		this.clientsCode = clientsCode;
	}

	@Override
	protected String getConfiguredTitle() {
		// TODO [Kristian Knezevic] verify translation
		return TEXTS.get("ImportClientsForm.ImportClients");
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

	public ChooseFileField getChooseFileField() {
		return getFieldByClass(ChooseFileField.class);
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
					return TEXTS.get("ImportClientsForm.MainBox.GroupBox.Company");
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
					return TEXTS.get("ImportClientsForm.MainBox.GroupBox.ImportType");
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
			public class ChooseFileField extends AbstractFileChooserField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("ImportClientsForm.MainBox.GroupBox.File");
				}

				@Override
				protected BinaryResource execValidateValue(BinaryResource rawValue) {

					return super.execValidateValue(rawValue);
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
			ImportClientsFormData formData = new ImportClientsFormData();
			exportFormData(formData);
			formData = BEANS.get(IImportClientsService.class).prepareCreate(formData);
			importFormData(formData);
		}

		@Override
		protected void execStore() {

			String extension = IOUtility.getFileExtension(getChooseFileField().getValue().getFilename());

			if (!extension.equals("xlsx")) {
				throw new VetoException(TEXTS.get("ImportClientsForm.WrongFileFormat"));
			}

			if (getChooseFileField().getValue() != null) {
				byte[] importFile = getChooseFileField().getValue().getContent();
				InputStream is = new ByteArrayInputStream(importFile);

				if (getChooseFileField().getFileSize() > 5000000) {
					throw new VetoException(TEXTS.get("ImportClientsForm.FileLargerThan5MB"));

				} else {
					if(getImportTypeField().getValue().intValue() == Constants.Dodavanje_novih_stavki) {
						ArrayList<ClientBean> beans = (ArrayList<ClientBean>) ScoutXlsxSpreadsheetAdapter
								.newClientBeanFromXLSXFile(is);
						if (beans.size() == 0) {
							throw new VetoException(TEXTS.get("ImportClientsForm.WrongDataFormat"));
						}
						setImportDetailsData(beans);
						
					}else if(getImportTypeField().getValue().intValue() == Constants.Azuriranje){
						ArrayList<ClientBean> beans = (ArrayList<ClientBean>) ScoutXlsxSpreadsheetAdapter
								.updateClientBeanFromXLSXFile(is);
						if (beans.size() == 0) {
							throw new VetoException(TEXTS.get("ImportClientsForm.WrongDataFormat"));
						}
						setImportDetailsData(beans);
						
					}
					

					

				}

			}

			ImportClientsFormData formData = new ImportClientsFormData();
			exportFormData(formData);
			formData = BEANS.get(IImportClientsService.class).create(formData);
			importFormData(formData);

		}
	}

	public class ModifyHandler extends AbstractFormHandler {
		@Override
		protected void execLoad() {
			ImportClientsFormData formData = new ImportClientsFormData();
			exportFormData(formData);
			formData = BEANS.get(IImportClientsService.class).load(formData);
			importFormData(formData);
		}

		@Override
		protected void execStore() {
			ImportClientsFormData formData = new ImportClientsFormData();
			exportFormData(formData);
			formData = BEANS.get(IImportClientsService.class).store(formData);
			importFormData(formData);
		}
	}
}
