package org.eclipse.scout.apps.xlsxreader.aboutclients;

import java.util.List;
import java.util.Set;

import org.eclipse.scout.apps.xlsxreader.aboutclients.ClientsTablePage.Table;
import org.eclipse.scout.apps.xlsxreader.files.lookup.CompanyLookupCall;
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
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@Data(ClientsTablePageData.class)
public class ClientsTablePage extends AbstractPageWithTable<Table> {
	@Override
	protected boolean getConfiguredLeaf() {
		return true;
	}

	@Override
	protected void execLoadData(SearchFilter filter) {
		importPageData(BEANS.get(IClientsService.class).getClientsTableData(filter));
	}

	@Override
	protected String getConfiguredTitle() {
		// TODO [Kristian Knezevic] verify translation
		return TEXTS.get("ClientsTablePage.Clients");
	}

	@Override
	protected String getConfiguredIconId() {
		return Icons.PersonSolid;
	}

	public class Table extends AbstractTable {
		
		@Override
		protected void execRowAction(ITableRow row) {
			
			int userID = getTable().getIDColumn().getSelectedValue();
			String name = getTable().getNameColumn().getSelectedValue();
			String surname = getTable().getSurameColumn().getSelectedValue();
			String code = getTable().getCodeColumn().getSelectedValue();
			
			ClientForm form = new ClientForm();
		    form.setUserID(userID);
		    form.setUserName(name);
		    form.setUserSurname(surname);
		    form.setUserCode(code);
		    form.startModify();
		    form.waitFor();
		    if (form.isFormStored()) {
			reloadPage();
		    }
			
			super.execRowAction(row);
		}

		
		
		

		@Override
		protected boolean getConfiguredAutoResizeColumns() {
			return true;
		}

		public CodeColumn getCodeColumn() {
			return getColumnSet().getColumnByClass(CodeColumn.class);
		}

		public SurnameColumn getSurameColumn() {
			return getColumnSet().getColumnByClass(SurnameColumn.class);
		}

		public OIBColumn getOIBColumn() {
			return getColumnSet().getColumnByClass(OIBColumn.class);
		}

		public DateOfBirthColumn getDateOfBirthColumn() {
			return getColumnSet().getColumnByClass(DateOfBirthColumn.class);
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
				return TEXTS.get("ClientsTablePage.Table.ID");
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
				return TEXTS.get("ClientsTablePage.Table.Code");
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
				return TEXTS.get("ClientsTablePage.Table.Name");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(4000)
		public class SurnameColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("ClientsTablePage.Table.Surname");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}
		
		@Order(5000)
		public class DateOfBirthColumn extends AbstractDateColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("ClientsTablePage.Table.DateOfBirth");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}

			@Override
			protected String getConfiguredFormat() {
				return "dd.MM.YYYY";
			}
		}

		@Order(6000)
		public class OIBColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("ClientsTablePage.Table.OIB");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(7000)
		public class CompanyColumn extends AbstractSmartColumn<Long> {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("ClientsTablePage.Table.Company");
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
