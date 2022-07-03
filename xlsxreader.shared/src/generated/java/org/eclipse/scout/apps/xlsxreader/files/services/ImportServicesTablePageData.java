package org.eclipse.scout.apps.xlsxreader.files.services;

import java.util.Date;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.basic.table.AbstractTableRowData;
import org.eclipse.scout.rt.shared.data.page.AbstractTablePageData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications
 * recommended.
 */
@Generated(value = "org.eclipse.scout.apps.xlsxreader.files.services.ImportServicesTablePage", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class ImportServicesTablePageData extends AbstractTablePageData {
	private static final long serialVersionUID = 1L;

	@Override
	public ImportServicesTableRowData addRow() {
		return (ImportServicesTableRowData) super.addRow();
	}

	@Override
	public ImportServicesTableRowData addRow(int rowState) {
		return (ImportServicesTableRowData) super.addRow(rowState);
	}

	@Override
	public ImportServicesTableRowData createRow() {
		return new ImportServicesTableRowData();
	}

	@Override
	public Class<? extends AbstractTableRowData> getRowType() {
		return ImportServicesTableRowData.class;
	}

	@Override
	public ImportServicesTableRowData[] getRows() {
		return (ImportServicesTableRowData[]) super.getRows();
	}

	@Override
	public ImportServicesTableRowData rowAt(int index) {
		return (ImportServicesTableRowData) super.rowAt(index);
	}

	public void setRows(ImportServicesTableRowData[] rows) {
		super.setRows(rows);
	}

	public static class ImportServicesTableRowData extends AbstractTableRowData {
		private static final long serialVersionUID = 1L;
		public static final String ID = "ID";
		public static final String name = "name";
		public static final String importType = "importType";
		public static final String date = "date";
		public static final String numberOfItems = "numberOfItems";
		public static final String company = "company";
		private Integer m_ID;
		private String m_name;
		private Long m_importType;
		private Date m_date;
		private String m_numberOfItems;
		private Long m_company;

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

		public Long getImportType() {
			return m_importType;
		}

		public void setImportType(Long newImportType) {
			m_importType = newImportType;
		}

		public Date getDate() {
			return m_date;
		}

		public void setDate(Date newDate) {
			m_date = newDate;
		}

		public String getNumberOfItems() {
			return m_numberOfItems;
		}

		public void setNumberOfItems(String newNumberOfItems) {
			m_numberOfItems = newNumberOfItems;
		}

		public Long getCompany() {
			return m_company;
		}

		public void setCompany(Long newCompany) {
			m_company = newCompany;
		}
	}
}
