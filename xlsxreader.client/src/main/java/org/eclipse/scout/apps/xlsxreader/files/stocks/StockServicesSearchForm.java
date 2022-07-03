package org.eclipse.scout.apps.xlsxreader.files.stocks;

import org.eclipse.scout.apps.xlsxreader.files.lookup.BranchLookupCall;
import org.eclipse.scout.apps.xlsxreader.files.lookup.CompanyLookupCall;
import org.eclipse.scout.apps.xlsxreader.files.stocks.StockServicesSearchForm.MainBox.CancelButton;
import org.eclipse.scout.apps.xlsxreader.files.stocks.StockServicesSearchForm.MainBox.GroupBox;
import org.eclipse.scout.apps.xlsxreader.files.stocks.StockServicesSearchForm.MainBox.OkButton;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractSearchForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractResetButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractSearchButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipse.scout.apps.xlsxreader.files.stocks.StockServicesSearchForm.MainBox.GroupBox.CompanyField;
import org.eclipse.scout.apps.xlsxreader.files.stocks.StockServicesSearchForm.MainBox.GroupBox.StockField;

@FormData(value = StockServicesSearchFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class StockServicesSearchForm extends AbstractSearchForm {
	
	private Long companyID;
	
	@Override
	protected String getConfiguredTitle() {
		// TODO [Kristian Knezevic] verify translation
		return TEXTS.get("StockServicesSearch.Search");
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

			@Order(1000)
			public class CompanyField extends AbstractSmartField<Long> {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("StockServicesSearch.MainBox.SearchBox.Company");
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
					return TEXTS.get("StockServicesSearch.MainBox.SearchBox.Stock");
				}
				
				@Override
				protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
					return BranchLookupCall.class;
				}
				
				@Override
				protected void execPrepareLookup(ILookupCall<Long> call) {
					BranchLookupCall bls = (BranchLookupCall) call;
					companyID = getCompanyField().getValue();
					bls.setCompanyID(companyID);
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
