package org.eclipse.scout.apps.xlsxreader.aboutpricelist;

import java.util.Set;

import org.eclipse.scout.apps.xlsxreader.aboutpricelist.PricelistsTablePage.Table;
import org.eclipse.scout.apps.xlsxreader.files.lookup.CompanyLookupCall;
import org.eclipse.scout.apps.xlsxreader.shared.Icons;
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
import org.eclipse.scout.rt.platform.util.TriState;
import org.eclipse.scout.rt.shared.AbstractIcons;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@Data(PricelistsTablePageData.class)
public class PricelistsTablePage extends AbstractPageWithTable<Table> {
	@Override
	protected boolean getConfiguredLeaf() {
		return true;
	}

	@Override
	protected Class<? extends ISearchForm> getConfiguredSearchForm() {
		return PricelistSearchForm.class;
	}

	@Override
	protected void execLoadData(SearchFilter filter) {
		importPageData(BEANS.get(IPricelistsService.class).getPricelistTableData( (PricelistSearchFormData)  filter.getFormData()));
	}

	@Override
	protected String getConfiguredTitle() {
		// TODO [Kristian Knezevic] verify translation
		return TEXTS.get("PricelistTablePage.Pricelist");
	}

	@Override
	protected String getConfiguredIconId() {
		return AbstractIcons.DiagramArea;
	}

	public class Table extends AbstractTable {

		@Override
		protected boolean getConfiguredAutoResizeColumns() {
			return true;
		}

		@Override
		protected TriState getConfiguredTruncatedCellTooltipEnabled() {
			return TriState.TRUE;
		}

		public CodeColumn getCodeColumn() {
			return getColumnSet().getColumnByClass(CodeColumn.class);
		}

		public NoteColumn getNoteColumn() {
			return getColumnSet().getColumnByClass(NoteColumn.class);
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

		@Override
		protected void execRowAction(ITableRow row) {

			int pricelistID = getTable().getIDColumn().getSelectedValue();
			PricelistForm form = new PricelistForm();

			form.setPricelistID(pricelistID);
			form.setTitle(getTable().getNameColumn().getSelectedValue());
			form.setSubTitle(getTable().getCodeColumn().getSelectedValue());
			form.startModify();
			form.waitFor();
			if (form.isFormStored()) {
				reloadPage();
			}

			super.execRowAction(row);
		}

		@Order(1000)
		public class AddMenu extends AbstractMenu {
			@Override
			protected String getConfiguredText() {
				return TEXTS.get("PricelistTablePage.Table.Add");
			}

			@Override
			protected Set<? extends IMenuType> getConfiguredMenuTypes() {
				return CollectionUtility.hashSet(TableMenuType.EmptySpace);
			}

			@Override
			protected String getConfiguredIconId() {
				return Icons.Plus;
			}

			@Override
			protected void execAction() {

				PricelistForm form = new PricelistForm();

				form.startNew();
				form.waitFor();
				if (form.isFormStored()) {
					reloadPage();
				}

			}
		}

		@Order(1000)
		public class IDColumn extends AbstractIntegerColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("PricelistTablePage.Table.ID");
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
				return TEXTS.get("PricelistTablePage.Table.Name");
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
				return TEXTS.get("PricelistTablePage.Table.Code");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(4000)
		public class NoteColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("PricelistTablePage.Table.Note");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(5000)
		public class CompanyColumn extends AbstractSmartColumn<Long> {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("PricelistTablePage.Table.Company");
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
