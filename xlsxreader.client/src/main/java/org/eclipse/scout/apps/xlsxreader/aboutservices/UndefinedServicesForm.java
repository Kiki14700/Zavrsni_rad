package org.eclipse.scout.apps.xlsxreader.aboutservices;

import java.util.List;


import org.eclipse.scout.apps.xlsxreader.aboutservices.UndefinedServicesForm.MainBox.CancelButton;
import org.eclipse.scout.apps.xlsxreader.aboutservices.UndefinedServicesForm.MainBox.GroupBox;
import org.eclipse.scout.apps.xlsxreader.aboutservices.UndefinedServicesForm.MainBox.GroupBox.SearchServicesBox;
import org.eclipse.scout.apps.xlsxreader.aboutservices.UndefinedServicesForm.MainBox.GroupBox.SearchServicesBox.CompanyField;
import org.eclipse.scout.apps.xlsxreader.aboutservices.UndefinedServicesForm.MainBox.GroupBox.UndefinedServicesTableField;
import org.eclipse.scout.apps.xlsxreader.aboutservices.UndefinedServicesForm.MainBox.OkButton;
import org.eclipse.scout.apps.xlsxreader.aboutservices.UndefinedServicesFormData.UndefinedServicesTable.UndefinedServicesTableRowData;
import org.eclipse.scout.apps.xlsxreader.files.lookup.CompanyLookupCall;
import org.eclipse.scout.apps.xlsxreader.lookup.ServiceCategoryLookupCall;
import org.eclipse.scout.apps.xlsxreader.lookup.WorkplaceLookupcall;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.action.IAction;
import org.eclipse.scout.rt.client.ui.action.keystroke.IKeyStroke;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractIntegerColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractSmartColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.IOutline;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.tablefield.AbstractTableField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipse.scout.apps.xlsxreader.aboutservices.UndefinedServicesForm.MainBox.GroupBox.SearchServicesBox.SearchButton;

@FormData(value = UndefinedServicesFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class UndefinedServicesForm extends AbstractForm {
	
	
	
	@Override
	protected String getConfiguredTitle() {
		// TODO [Kristian Knezevic] verify translation
		return TEXTS.get("UndefinedServices.UndefinedServices");
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public GroupBox getGroupBox() {
		return getFieldByClass(GroupBox.class);
	}

	public UndefinedServicesTableField getServicesTableField() {
		return getFieldByClass(UndefinedServicesTableField.class);
	}

	public CompanyField getCompanyField() {
		return getFieldByClass(CompanyField.class);
	}

	public SearchServicesBox getSearchServicesBox() {
		return getFieldByClass(SearchServicesBox.class);
	}

	public SearchButton getSearchButton() {
		return getFieldByClass(SearchButton.class);
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

			@Order(-1000)
			public class SearchServicesBox extends AbstractSequenceBox {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("UndefinedServices.MainBox.GroupBox.SearchServiceBox");
				}

				@Override
				protected boolean getConfiguredAutoCheckFromTo() {
					return false;
				}

				@Override
				protected boolean getConfiguredLabelVisible() {
					return false;
				}

				@Order(0)
				public class CompanyField extends AbstractSmartField<Long> {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("UndefinedServices.MainBox.GroupBox.Company");
					}

					@Override
					protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
						return CompanyLookupCall.class;
					}

					@Override
					protected void execChangedValue() {
						if (getCompanyField().getValue() == null) {
							getSearchButton().setEnabled(false);
						} else {
							getSearchButton().setEnabled(true);
						}
					}
				}

				@Order(1000)
				public class SearchButton extends AbstractButton {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("UndefinedServices.MainBox.GroupBox.SearchServiceBox.Search");
					}

					@Override
					protected void execClickAction() {
						fetchServices(getCompanyField().getValue().intValue());
					}
				}

			}

			@Order(1000)
			public class UndefinedServicesTableField extends AbstractTableField<UndefinedServicesTableField.Table> {

				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("UndefinedServices.Table");
				}

				@Override
				protected int getConfiguredGridH() {
					return 6;
				}

				@Override
				protected boolean getConfiguredLabelVisible() {
					return false;
				}

				public class Table extends AbstractTable {
					
					

					@Override
					protected boolean getConfiguredAutoResizeColumns() {
						return true;
					}

					public CodeColumn getCodeColumn() {
						return getColumnSet().getColumnByClass(CodeColumn.class);
					}

					public CategoryColumn getCategoryColumn() {
						return getColumnSet().getColumnByClass(CategoryColumn.class);
					}

					public WorkplaceColumn getWorkplaceColumn() {
						return getColumnSet().getColumnByClass(WorkplaceColumn.class);
					}

					public NameColumn getNameColumn() {
						return getColumnSet().getColumnByClass(NameColumn.class);
					}

					public IDColumn getIDColumn() {
						return getColumnSet().getColumnByClass(IDColumn.class);
					}

					@Order(1000)
					public class IDColumn extends AbstractIntegerColumn {
						@Override
						protected String getConfiguredHeaderText() {
							return TEXTS.get("UndefinedServices.Table.ID");
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
							return TEXTS.get("UndefinedServices.Table.Name");
						}

						@Override
						protected int getConfiguredWidth() {
							return 100;
						}
					}

					@Order(3000)
					public class CodeColumn extends AbstractStringColumn {
						@Override
						protected String getConfiguredHeaderText() {
							return TEXTS.get("UndefinedServices.Table.Code");
						}

						@Override
						protected int getConfiguredWidth() {
							return 100;
						}
					}

					@Order(4000)
					public class CategoryColumn extends AbstractSmartColumn<Integer> {
						@Override
						protected String getConfiguredHeaderText() {
							return TEXTS.get("UndefinedServices.Table.Category");
						}

						@Override
						protected int getConfiguredWidth() {
							return 100;
						}

						@Override
						protected Class<? extends ILookupCall<Integer>> getConfiguredLookupCall() {
							return ServiceCategoryLookupCall.class;
						}
					}

					@Order(5000)
					public class WorkplaceColumn extends AbstractSmartColumn<Integer> {
						@Override
						protected String getConfiguredHeaderText() {
							return TEXTS.get("UndefinedServices.Table.Workplace");
						}

						@Override
						protected int getConfiguredWidth() {
							return 100;
						}

						@Override
						protected Class<? extends ILookupCall<Integer>> getConfiguredLookupCall() {
							return WorkplaceLookupcall.class;
						}
					}

				}

			}

		}

		

		@Order(3000)
		public class OkButton extends AbstractOkButton {

			@Override
			protected String getConfiguredLabel() {
				return TEXTS.get("UndefinedServicesForm.MainBox.GroupBox.Close");
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

		@Order(4000)
		public class CancelButton extends AbstractCancelButton {

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
			UndefinedServicesFormData formData = new UndefinedServicesFormData();
			exportFormData(formData);
			formData = BEANS.get(IUndefinedServicesService.class).prepareCreate(formData);
			importFormData(formData);
		}

		@Override
		protected void execStore() {
			UndefinedServicesFormData formData = new UndefinedServicesFormData();
			exportFormData(formData);
			formData = BEANS.get(IUndefinedServicesService.class).create(formData);
			importFormData(formData);
		}
	}

	public class ModifyHandler extends AbstractFormHandler {
		@Override
		protected void execLoad() {
			UndefinedServicesFormData formData = new UndefinedServicesFormData();
			exportFormData(formData);
			formData = BEANS.get(IUndefinedServicesService.class).load(formData);
			importFormData(formData);
		}

		@Override
		protected void execStore() {
			UndefinedServicesFormData formData = new UndefinedServicesFormData();
			exportFormData(formData);
			formData = BEANS.get(IUndefinedServicesService.class).store(formData);
			importFormData(formData);
		}
	}

	public void fetchServices(int companyID) {
		List<UndefinedServicesTableRowData> rowData = BEANS.get(IUndefinedServicesService.class)
				.fetchServices(companyID);

		getServicesTableField().getTable().importFromTableRowBeanData(rowData, UndefinedServicesTableRowData.class);
	}
	
	
}
