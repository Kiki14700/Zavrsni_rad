package org.eclipse.scout.apps.xlsxreader.common.forms;

import javax.annotation.Generated;

import org.eclipse.scout.rt.platform.resource.BinaryResource;
import org.eclipse.scout.rt.shared.data.basic.table.AbstractTableRowData;
import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.rt.shared.data.form.fields.tablefield.AbstractTableFieldBeanData;
import org.eclipse.scout.rt.shared.data.form.properties.AbstractPropertyData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications
 * recommended.
 */
@Generated(value = "org.eclipse.scout.apps.xlsxreader.common.forms.XlsxTemplateForm", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class XlsxTemplateFormData extends AbstractFormData {
	private static final long serialVersionUID = 1L;

	/**
	 * access method for property FormID.
	 */
	public Long getFormID() {
		return getFormIDProperty().getValue();
	}

	/**
	 * access method for property FormID.
	 */
	public void setFormID(Long formID) {
		getFormIDProperty().setValue(formID);
	}

	public FormIDProperty getFormIDProperty() {
		return getPropertyByClass(FormIDProperty.class);
	}

	public ImportFile getImportFile() {
		return getFieldByClass(ImportFile.class);
	}

	public TemplateTable getTemplateTable() {
		return getFieldByClass(TemplateTable.class);
	}

	public static class FormIDProperty extends AbstractPropertyData<Long> {
		private static final long serialVersionUID = 1L;
	}

	public static class ImportFile extends AbstractValueFieldData<BinaryResource> {
		private static final long serialVersionUID = 1L;
	}

	public static class TemplateTable extends AbstractTableFieldBeanData {
		private static final long serialVersionUID = 1L;

		@Override
		public TemplateTableRowData addRow() {
			return (TemplateTableRowData) super.addRow();
		}

		@Override
		public TemplateTableRowData addRow(int rowState) {
			return (TemplateTableRowData) super.addRow(rowState);
		}

		@Override
		public TemplateTableRowData createRow() {
			return new TemplateTableRowData();
		}

		@Override
		public Class<? extends AbstractTableRowData> getRowType() {
			return TemplateTableRowData.class;
		}

		@Override
		public TemplateTableRowData[] getRows() {
			return (TemplateTableRowData[]) super.getRows();
		}

		@Override
		public TemplateTableRowData rowAt(int index) {
			return (TemplateTableRowData) super.rowAt(index);
		}

		public void setRows(TemplateTableRowData[] rows) {
			super.setRows(rows);
		}

		public static class TemplateTableRowData extends AbstractTableRowData {
			private static final long serialVersionUID = 1L;
			public static final String ID = "ID";
			public static final String name = "name";
			private Integer m_ID;
			private String m_name;

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
		}
	}
}
