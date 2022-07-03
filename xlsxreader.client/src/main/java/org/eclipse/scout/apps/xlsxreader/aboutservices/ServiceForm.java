package org.eclipse.scout.apps.xlsxreader.aboutservices;

import org.eclipse.scout.apps.xlsxreader.aboutservices.IServiceService;
import org.eclipse.scout.apps.xlsxreader.aboutservices.ServiceFormData;
import org.eclipse.scout.apps.xlsxreader.aboutservices.ServiceForm.MainBox.CancelButton;
import org.eclipse.scout.apps.xlsxreader.aboutservices.ServiceForm.MainBox.GroupBox;
import org.eclipse.scout.apps.xlsxreader.aboutservices.ServiceForm.MainBox.OkButton;
import org.eclipse.scout.apps.xlsxreader.aboutservices.ServiceForm.MainBox.GroupBox.CategoryField;
import org.eclipse.scout.apps.xlsxreader.aboutservices.ServiceForm.MainBox.GroupBox.CompanyField;
import org.eclipse.scout.apps.xlsxreader.aboutservices.ServiceForm.MainBox.GroupBox.NameField;
import org.eclipse.scout.apps.xlsxreader.aboutservices.ServiceForm.MainBox.GroupBox.WorkplaceField;
import org.eclipse.scout.apps.xlsxreader.buttons.AbstractXLSXReaderCancelButton;
import org.eclipse.scout.apps.xlsxreader.buttons.AbstractXLSXReaderOKButton;
import org.eclipse.scout.apps.xlsxreader.files.lookup.CompanyLookupCall;
import org.eclipse.scout.apps.xlsxreader.lookup.ServiceCategoryLookupCall;
import org.eclipse.scout.apps.xlsxreader.lookup.WorkplaceLookupcall;
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

@FormData(value = ServiceFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class ServiceForm extends AbstractSearchForm {
	
	private Integer serviceID;
	
	@FormData
	public Integer getServiceID() {
		return serviceID;
	}

	@FormData
	public void setServiceID(Integer serviceID) {
		this.serviceID = serviceID;
	}

	@Override
	protected String getConfiguredTitle() {
		// TODO [Kristian Knezevic] verify translation
		return TEXTS.get("Service.ServiceInspect");
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public GroupBox getGroupBox() {
		return getFieldByClass(GroupBox.class);
	}

	public CategoryField getCategoryField() {
		return getFieldByClass(CategoryField.class);
	}

	public CompanyField getCompanyField() {
		return getFieldByClass(CompanyField.class);
	}

	public WorkplaceField getWorkplaceField() {
		return getFieldByClass(WorkplaceField.class);
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
				return 2;
			}

			@Order(1000)
			public class NameField extends AbstractStringField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Service.MainBox.GroupBox.Name");
				}

				@Override
				protected int getConfiguredMaxLength() {
					return 128;
				}
				
			}

			@Order(2000)
			public class CategoryField extends AbstractSmartField<Integer> {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Service.MainBox.GroupBox.Category");
				}

				@Override
				protected int getConfiguredMaxLength() {
					return 128;
				}
				
				@Override
				protected Class<? extends ILookupCall<Integer>> getConfiguredLookupCall() {
					
					return ServiceCategoryLookupCall.class;
				}
			}

			@Order(3000)
			public class CompanyField extends AbstractSmartField<Long> {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Service.MainBox.GroupBox.Company");
				}

				@Override
				protected int getConfiguredMaxLength() {
					return 128;
				}
				
				@Override
				protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
					return CompanyLookupCall.class;
				}
			}

			@Order(4000)
			public class WorkplaceField extends AbstractSmartField<Integer> {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Service.MainBox.GroupBox.Workplace");
				}

				@Override
				protected int getConfiguredMaxLength() {
					return 128;
				}
				
				@Override
				protected Class<? extends ILookupCall<Integer>> getConfiguredLookupCall() {
					return WorkplaceLookupcall.class;
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
			ServiceFormData formData = new ServiceFormData();
			exportFormData(formData);
			formData = BEANS.get(IServiceService.class).prepareCreate(formData);
			importFormData(formData);
		}

		@Override
		protected void execStore() {
			ServiceFormData formData = new ServiceFormData();
			exportFormData(formData);
			formData = BEANS.get(IServiceService.class).create(formData);
			importFormData(formData);
		}
	}

	public class ModifyHandler extends AbstractFormHandler {
		@Override
		protected void execLoad() {
			ServiceFormData formData = new ServiceFormData();
			exportFormData(formData);
			formData = BEANS.get(IServiceService.class).load(formData);
			importFormData(formData);
		}

		@Override
		protected void execStore() {
			ServiceFormData formData = new ServiceFormData();
			exportFormData(formData);
			formData = BEANS.get(IServiceService.class).store(formData);
			importFormData(formData);
		}
	}
}
