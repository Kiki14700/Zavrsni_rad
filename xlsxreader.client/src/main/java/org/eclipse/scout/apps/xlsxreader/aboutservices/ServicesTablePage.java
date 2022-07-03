package org.eclipse.scout.apps.xlsxreader.aboutservices;

import java.util.Set;

import org.eclipse.scout.apps.xlsxreader.aboutservices.IServicesService;
import org.eclipse.scout.apps.xlsxreader.aboutservices.ServicesTablePageData;
import org.eclipse.scout.apps.xlsxreader.aboutservices.ServicesTablePage.Table;
import org.eclipse.scout.apps.xlsxreader.files.lookup.CompanyLookupCall;
import org.eclipse.scout.apps.xlsxreader.files.services.ServicesFilesSearchForm;
import org.eclipse.scout.apps.xlsxreader.lookup.ServiceCategoryLookupCall;
import org.eclipse.scout.apps.xlsxreader.lookup.WorkplaceLookupcall;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractIntegerColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractSmartColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.ISearchForm;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.shared.AbstractIcons;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@Data(ServicesTablePageData.class)
public class ServicesTablePage extends AbstractPageWithTable<Table> {
	@Override
	protected boolean getConfiguredLeaf() {
		return true;
	}
	
	@Override
	protected Class<? extends ISearchForm> getConfiguredSearchForm() {
		return ServicesSearchForm.class;
	}

	@Override
	protected void execLoadData(SearchFilter filter) {
		importPageData(BEANS.get(IServicesService.class).getServicesTableData( (ServicesSearchFormData) filter.getFormData()));
	}

	@Override
	protected String getConfiguredIconId() {
		return AbstractIcons.Group;
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("ServicesTablePage.Services");
	}

	public class Table extends AbstractTable {

		@Override
		protected void execRowAction(ITableRow row) {

			int serviceID = getTable().getIDColumn().getSelectedValue();
			String name = getTable().getNameColumn().getSelectedValue();
			String code = getTable().getCodeColumn().getSelectedValue();

			ServiceForm form = new ServiceForm();
			form.setServiceID(serviceID);
			form.setTitle(name);
			form.setSubTitle(code);
			form.startModify();
			form.waitFor();
			if (form.isFormStored()) {
				reloadPage();
			}
		}

		//prikaz usluga bez cijena
		@Order(1000)
		public class OtherServicesMenu extends AbstractMenu {
			@Override
			protected String getConfiguredText() {
				return TEXTS.get("ServicesTablePage.Table.OtherServices");
			}

			@Override
			protected Set<? extends IMenuType> getConfiguredMenuTypes() {
				return CollectionUtility.hashSet(TableMenuType.EmptySpace);
			}
			
			@Override
			protected String getConfiguredIconId() {
				return AbstractIcons.Star;
			}

			@Override
			protected void execAction() {
				
				UndefinedServicesForm form = new UndefinedServicesForm();
				form.start();
				form.getSearchButton().setEnabled(false);
			}
		}
		
		

		@Override
		protected boolean getConfiguredAutoResizeColumns() {
			return true;
		}

		public CodeColumn getCodeColumn() {
			return getColumnSet().getColumnByClass(CodeColumn.class);
		}

		public ServiceCategoryColumn getServiceCategoryColumn() {
			return getColumnSet().getColumnByClass(ServiceCategoryColumn.class);
		}

		public WorkplaceColumn getWorkplaceColumn() {
			return getColumnSet().getColumnByClass(WorkplaceColumn.class);
		}

		public CompanyColumn getCompanyColumn() {
			return getColumnSet().getColumnByClass(CompanyColumn.class);
		}

		public PriceColumn getPriceColumn() {
			return getColumnSet().getColumnByClass(PriceColumn.class);
		}

		public TaxColumn getTaxColumn() {
			return getColumnSet().getColumnByClass(TaxColumn.class);
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
				return TEXTS.get("ServicesTablePage.Table.ID");
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
				return TEXTS.get("ServicesTablePage.Table.Name");
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
				return TEXTS.get("ServicesTablePage.Table.Code");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(4000)
		public class ServiceCategoryColumn extends AbstractSmartColumn<Integer> {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("ServicesTablePage.Table.ServiceCategory");
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
		public class CompanyColumn extends AbstractSmartColumn<Long> {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("ServicesTablePage.Table.Company");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}

			@Override
			protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
				return CompanyLookupCall.class;
			}
		}

		@Order(6000)
		public class WorkplaceColumn extends AbstractSmartColumn<Integer> {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("ServicesTablePage.Table.Workplace");
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

		@Order(7000)
		public class PriceColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("ServicesTablePage.Table.Price");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(8000)
		public class TaxColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("ServicesTablePage.Table.Tax");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}
		
		
		
		

	}
}
