package org.eclipse.scout.apps.xlsxreader.aboutservices;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.basic.table.AbstractTableRowData;
import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.rt.shared.data.form.fields.tablefield.AbstractTableFieldBeanData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications
 * recommended.
 */
@Generated(value = "org.eclipse.scout.apps.xlsxreader.aboutservices.UndefinedServicesForm", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class UndefinedServicesFormData extends AbstractFormData {
	private static final long serialVersionUID = 1L;

	public Company getCompany() {
		return getFieldByClass(Company.class);
	}

	public UndefinedServicesTable getUndefinedServicesTable() {
		return getFieldByClass(UndefinedServicesTable.class);
	}

	public static class Company extends AbstractValueFieldData<Long> {
		private static final long serialVersionUID = 1L;
	}

	public static class UndefinedServicesTable extends AbstractTableFieldBeanData {
		private static final long serialVersionUID = 1L;

		@Override
		public UndefinedServicesTableRowData addRow() {
			return (UndefinedServicesTableRowData) super.addRow();
		}

		@Override
		public UndefinedServicesTableRowData addRow(int rowState) {
			return (UndefinedServicesTableRowData) super.addRow(rowState);
		}

		@Override
		public UndefinedServicesTableRowData createRow() {
			return new UndefinedServicesTableRowData();
		}

		@Override
		public Class<? extends AbstractTableRowData> getRowType() {
			return UndefinedServicesTableRowData.class;
		}

		@Override
		public UndefinedServicesTableRowData[] getRows() {
			return (UndefinedServicesTableRowData[]) super.getRows();
		}

		@Override
		public UndefinedServicesTableRowData rowAt(int index) {
			return (UndefinedServicesTableRowData) super.rowAt(index);
		}

		public void setRows(UndefinedServicesTableRowData[] rows) {
			super.setRows(rows);
		}

		public static class UndefinedServicesTableRowData extends AbstractTableRowData {
			private static final long serialVersionUID = 1L;
			public static final String ID = "ID";
			public static final String name = "name";
			public static final String code = "code";
			public static final String category = "category";
			public static final String workplace = "workplace";
			private Integer m_ID;
			private String m_name;
			private String m_code;
			private Integer m_category;
			private Integer m_workplace;

			public Integer getID() {
				return m_ID;
			}

			public void setID(Integer newID) {
				m_ID = newID;
			}

			public String getName() {
				return m_name;
			}

			public void setName(String newName) {
				m_name = newName;
			}

			public String getCode() {
				return m_code;
			}

			public void setCode(String newCode) {
				m_code = newCode;
			}

			public Integer getCategory() {
				return m_category;
			}

			public void setCategory(Integer newCategory) {
				m_category = newCategory;
			}

			public Integer getWorkplace() {
				return m_workplace;
			}

			public void setWorkplace(Integer newWorkplace) {
				m_workplace = newWorkplace;
			}
		}
	}
}