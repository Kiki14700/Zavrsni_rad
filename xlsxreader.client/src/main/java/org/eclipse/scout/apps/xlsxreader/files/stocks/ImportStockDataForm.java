package org.eclipse.scout.apps.xlsxreader.files.stocks;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.eclipse.scout.apps.xlsxreader.beans.PriceBean;
import org.eclipse.scout.apps.xlsxreader.beans.StockBean;
import org.eclipse.scout.apps.xlsxreader.buttons.AbstractXLSXReaderCancelButton;
import org.eclipse.scout.apps.xlsxreader.buttons.AbstractXLSXReaderOKButton;
import org.eclipse.scout.apps.xlsxreader.common.helpers.MessageBoxHelpers;
import org.eclipse.scout.apps.xlsxreader.files.lookup.CompanyLookupCall;
import org.eclipse.scout.apps.xlsxreader.files.lookup.StockLookupCall;
import org.eclipse.scout.apps.xlsxreader.files.stocks.ImportStockDataForm.MainBox.CancelButton;
import org.eclipse.scout.apps.xlsxreader.files.stocks.ImportStockDataForm.MainBox.GroupBox;
import org.eclipse.scout.apps.xlsxreader.files.stocks.ImportStockDataForm.MainBox.OkButton;
import org.eclipse.scout.apps.xlsxreader.lookup.StockImportTypeLookupCall;
import org.eclipse.scout.apps.xlsxreader.xlsxhelper.ScoutXlsxSpreadsheetAdapter;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
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
import org.eclipse.scout.apps.xlsxreader.files.stocks.ImportStockDataForm.MainBox.GroupBox.CompanyField;
import org.eclipse.scout.apps.xlsxreader.files.stocks.ImportStockDataForm.MainBox.GroupBox.StockField;
import org.eclipse.scout.apps.xlsxreader.files.stocks.ImportStockDataForm.MainBox.GroupBox.ImportTypeField;
import org.eclipse.scout.apps.xlsxreader.files.stocks.ImportStockDataForm.MainBox.GroupBox.ChooseFileField;

@FormData(value = ImportStockDataFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class ImportStockDataForm extends AbstractForm {

	private ArrayList importDetailsData;
	private Integer fileID;
	private Long companyID;

	@FormData
	public ArrayList getImportDetailsData() {
		return importDetailsData;
	}
	@FormData
	public void setImportDetailsData(ArrayList importDetailsData) {
		this.importDetailsData = importDetailsData;
	}
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
		return TEXTS.get("ImportStockData.Import");
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

	public StockField getStockField() {
		return getFieldByClass(StockField.class);
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
					return TEXTS.get("ImportStockDataForm.MainBox.GroupBox.Company");
				}

				@Override
				protected boolean getConfiguredMandatory() {
					return true;
				}
				
				@Override
				protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
					return CompanyLookupCall.class;
				}
				
				@Override
				protected void execChangedValue() {
					getStockField().setValue(null);
				}
			}

			@Order(2000)
			public class StockField extends AbstractSmartField<Long> {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("ImportStockDataForm.MainBox.GroupBox.Stock");
				}

				@Override
				protected boolean getConfiguredMandatory() {
					return true;
				}
				
				@Override
				protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
					return StockLookupCall.class;
				}
				
				@Override
				protected void execPrepareLookup(ILookupCall<Long> call) {
					StockLookupCall slc = (StockLookupCall) call;
					companyID = getCompanyField().getValue();
					slc.setCompanyID(companyID);
				}
			}

			@Order(3000)
			public class ImportTypeField extends AbstractSmartField<Long> {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("ImportStockDataForm.MainBox.GroupBox.ImportType");
				}

				@Override
				protected boolean getConfiguredMandatory() {
					return true;
				}
				
				@Override
				protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
					return StockImportTypeLookupCall.class;
				}
			}

			@Order(4000)
			public class ChooseFileField extends AbstractFileChooserField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("ImportStockDataForm.MainBox.GroupBox.File");
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
			ImportStockDataFormData formData = new ImportStockDataFormData();
			exportFormData(formData);
			formData = BEANS.get(IImportStockDataService.class).prepareCreate(formData);
			importFormData(formData);
		}

		@Override
		protected void execStore() {
			
			String extension = IOUtility.getFileExtension(getChooseFileField().getValue().getFilename());

			if (!extension.equals("xlsx")) {
				throw new VetoException(TEXTS.get("ImportStockDataForm.WrongFileFormat"));
			}
			
			if (getChooseFileField().getValue() != null) {
				byte[] importFile = getChooseFileField().getValue().getContent();
				InputStream is = new ByteArrayInputStream(importFile);

				if (getChooseFileField().getFileSize() > 5000000) {
					throw new VetoException(TEXTS.get("ImportStockForm.FileLargerThan5MB"));

				} else {
					ArrayList<StockBean> beans = (ArrayList<StockBean>) ScoutXlsxSpreadsheetAdapter
							.fillStockImportBeanFromXLSXFile(is);
					if (beans.size() == 0) {
						throw new VetoException(TEXTS.get("ImportStockDataForm.WrongDataFormat"));
					}
					setImportDetailsData(beans);

				}

			}
			
			ImportStockDataFormData formData = new ImportStockDataFormData();
			exportFormData(formData);
			formData = BEANS.get(IImportStockDataService.class).create(formData);
			importFormData(formData);
			
			
		}
	}

	public class ModifyHandler extends AbstractFormHandler {
		@Override
		protected void execLoad() {
			ImportStockDataFormData formData = new ImportStockDataFormData();
			exportFormData(formData);
			formData = BEANS.get(IImportStockDataService.class).load(formData);
			importFormData(formData);
		}

		@Override
		protected void execStore() {
			ImportStockDataFormData formData = new ImportStockDataFormData();
			exportFormData(formData);
			formData = BEANS.get(IImportStockDataService.class).store(formData);
			importFormData(formData);
		}
	}
}
