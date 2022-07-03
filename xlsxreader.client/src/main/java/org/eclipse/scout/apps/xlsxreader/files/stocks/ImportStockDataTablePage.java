package org.eclipse.scout.apps.xlsxreader.files.stocks;

import java.util.List;
import java.util.Set;

import org.eclipse.scout.apps.xlsxreader.common.forms.XlsxTemplateForm;
import org.eclipse.scout.apps.xlsxreader.constants.Constants;
import org.eclipse.scout.apps.xlsxreader.files.lookup.CompanyLookupCall;
import org.eclipse.scout.apps.xlsxreader.files.services.IImportServicesService;
import org.eclipse.scout.apps.xlsxreader.files.stocks.ImportStockDataTablePage.Table;
import org.eclipse.scout.apps.xlsxreader.lookup.StockImportTypeLookupCall;
import org.eclipse.scout.apps.xlsxreader.menus.AbstractDeleteMenu;
import org.eclipse.scout.apps.xlsxreader.menus.AbstractXLSXReaderDownloadMenu;
import org.eclipse.scout.apps.xlsxreader.shared.Icons;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractDateColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractIntegerColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractSmartColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.IDesktop;
import org.eclipse.scout.rt.client.ui.desktop.OpenUriAction;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.ISearchForm;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.resource.BinaryResource;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@Data(ImportStockDataTablePageData.class)
public class ImportStockDataTablePage extends AbstractPageWithTable<Table> {
	@Override
	protected boolean getConfiguredLeaf() {
		return true;
	}

	@Override
	protected Class<? extends ISearchForm> getConfiguredSearchForm() {
		return ImportStockDataSearchForm.class;
	}
	
	@Override
	protected void execLoadData(SearchFilter filter) {
		importPageData(BEANS.get(IImportStockDataService.class).getImportStockDataTableData( (ImportStockDataSearchFormData) filter.getFormData()));
	}

	@Override
	protected String getConfiguredTitle() {
		// TODO [Kristian Knezevic] verify translation
		return TEXTS.get("ImportStockDataTablePage.Import");
	}
	
	@Override
	protected String getConfiguredIconId() {
		return Icons.LongArrowUpPlus;
	}

	public class Table extends AbstractTable {
		
		@Override
		protected void execRowAction(ITableRow row) {
			
			int fileID = getIDColumn().getSelectedValue();
			
			ImportStockDataDetailsForm form = new ImportStockDataDetailsForm();
			form.setFileID(fileID);
			form.startModify();
			form.setSubTitle(getTable().getNameColumn().getSelectedValue());
			form.waitFor();
			if (form.isFormStored()) {
				reloadPage();
			}
			
			
			
		}
		
		@Override
		protected boolean getConfiguredAutoResizeColumns() {
			return true;
		}

		@Order(1000)
		public class AddMenu extends AbstractMenu {
			@Override
			protected String getConfiguredText() {
				return TEXTS.get("ImportStockDataTablePage.Table.Add");
			}

			@Override
			protected Set<? extends IMenuType> getConfiguredMenuTypes() {
				return CollectionUtility.hashSet(TableMenuType.EmptySpace);
			}
			
			@Override
			protected String getConfiguredIconId() {
				return Icons.LongArrowUpPlus;
			}

			@Override
			protected void execAction() {
				
				ImportStockDataForm form = new ImportStockDataForm();
				
				form.startNew();
				form.waitFor();
				if(form.isFormStored()) {
					reloadPage();
				}
			}
		}

		@Order(2000)
		public class DownloadMenu extends AbstractXLSXReaderDownloadMenu {
			
			@Override
			protected void execAction() {
				
				Integer fileId = getTable().getIDColumn().getSelectedValue();

				byte[] file = BEANS.get(IImportStockDataService.class).getFileContent(fileId);
				
				IDesktop.CURRENT.get().openUri(new BinaryResource(getTable().getNameColumn().getSelectedValue(), file), OpenUriAction.DOWNLOAD);

				
			}
		}
		
		

		@Order(3000)
		public class TemplateMenu extends AbstractMenu {
			@Override
			protected String getConfiguredText() {
				return TEXTS.get("ImportClientsTablePage.Table.Template");
			}

			@Override
			protected Set<? extends IMenuType> getConfiguredMenuTypes() {
				return CollectionUtility.hashSet(TableMenuType.EmptySpace);
			}

			@Override
			protected String getConfiguredIconId() {
				return Icons.PencilUnderlineSolid;
			}

			@Override
			protected void execAction() {

				XlsxTemplateForm form = new XlsxTemplateForm();
				Long formID = Constants.Stock;

				form.setFormID(formID);
				form.startNew();
				form.fetchTemplateFiles();
				form.waitFor();
				if (form.isFormStored()) {
					reloadPage();
				}
			}
		}

		@Order(4000)
		public class DeleteMenu extends AbstractDeleteMenu {
			@Override
			protected void execAction() {
				List<Integer> fileID = getTable().getIDColumn().getSelectedValues();
				BEANS.get(IImportStockDataService.class).deleteFile(fileID);
				reloadPage();
			}
		}
		
		

		public ImportTypeColumn getImportTypeColumn() {
			return getColumnSet().getColumnByClass(ImportTypeColumn.class);
		}

		public DateColumn getDateColumn() {
			return getColumnSet().getColumnByClass(DateColumn.class);
		}

		public NumberOfItemsColumn getNumberOfItemsColumn() {
			return getColumnSet().getColumnByClass(NumberOfItemsColumn.class);
		}

		public CompanyColumn getCompanyColumn() {
			return getColumnSet().getColumnByClass(CompanyColumn.class);
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
				return TEXTS.get("ImportStockDataTablePage.Table.ID");
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
				return TEXTS.get("ImportStockDataTablePage.Table.Name");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(3000)
		public class ImportTypeColumn extends AbstractSmartColumn<Long> {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("ImportStockDataTablePage.Table.ImportType");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
			
			@Override
			protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
				return StockImportTypeLookupCall.class;
			}
		}

		@Order(4000)
		public class DateColumn extends AbstractDateColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("ImportStockDataTablePage.Table.Date");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(5000)
		public class NumberOfItemsColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("ImportStockDataTablePage.Table.NumberOfItems");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(6000)
		public class CompanyColumn extends AbstractSmartColumn<Long> {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("ImportStockDataTablePage.Table.Company");
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
		
		
		

	}
}
