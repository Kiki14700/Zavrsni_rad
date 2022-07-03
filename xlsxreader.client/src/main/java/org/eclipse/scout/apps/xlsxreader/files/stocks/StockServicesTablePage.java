package org.eclipse.scout.apps.xlsxreader.files.stocks;

import org.eclipse.scout.apps.xlsxreader.files.stocks.StockServicesTablePage.Table;
import org.eclipse.scout.apps.xlsxreader.lookup.WorkplaceLookupcall;
import org.eclipse.scout.apps.xlsxreader.shared.Icons;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractIntegerColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractSmartColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.ISearchForm;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@Data(StockServicesTablePageData.class)
public class StockServicesTablePage extends AbstractPageWithTable<Table> {
	@Override
	protected boolean getConfiguredLeaf() {
		return true;
	}
	
	@Override
	protected Class<? extends ISearchForm> getConfiguredSearchForm() {
		return StockServicesSearchForm.class;
	}

	@Override
	protected void execLoadData(SearchFilter filter) {
		importPageData(BEANS.get(IStockServicesService.class).getStockServicesTableData( (StockServicesSearchFormData) filter.getFormData()));
	}

	@Override
	protected String getConfiguredTitle() {
		// TODO [Kristian Knezevic] verify translation
		return TEXTS.get("StockServicesTablePage.NumberOfServices");
	}

	@Override
	protected String getConfiguredIconId() {
		return Icons.Sum;
	}
	
	public class Table extends AbstractTable {

		@Override
		protected boolean getConfiguredAutoResizeColumns() {
		    return true;
		}

		public CodeColumn getCodeColumn() {
			return getColumnSet().getColumnByClass(CodeColumn.class);
		}

		public WorkplaceColumn getWorkplaceColumn() {
			return getColumnSet().getColumnByClass(WorkplaceColumn.class);
		}

		public NumOfServicesColumn getNumOfServicesColumn() {
			return getColumnSet().getColumnByClass(NumOfServicesColumn.class);
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
				return TEXTS.get("StockServicesTablePage.Table.ID");
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
		public class CodeColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("StockServicesTablePage.Table.Code");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(3000)
		public class NameColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("StockServicesTablePage.Table.Name");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(4000)
		public class WorkplaceColumn extends AbstractSmartColumn<Integer> {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("StockServicesTablePage.Table.Workplace");
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

		@Order(5000)
		public class NumOfServicesColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("StockServicesTablePage.Table.NumberOfServices");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

	
		
	}
}
