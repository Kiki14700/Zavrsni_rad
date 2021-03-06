package org.eclipse.scout.apps.xlsxreader.files.klijenti;

import java.util.Date;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications
 * recommended.
 */
@Generated(value = "org.eclipse.scout.apps.xlsxreader.files.klijenti.ClientsFileSearchForm", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class ClientsFileSearchFormData extends AbstractFormData {
	private static final long serialVersionUID = 1L;

	public DateFrom getDateFrom() {
		return getFieldByClass(DateFrom.class);
	}

	public DateTo getDateTo() {
		return getFieldByClass(DateTo.class);
	}

	public FilesCompany getFilesCompany() {
		return getFieldByClass(FilesCompany.class);
	}

	public FilesImportType getFilesImportType() {
		return getFieldByClass(FilesImportType.class);
	}

	public FilesName getFilesName() {
		return getFieldByClass(FilesName.class);
	}

	public static class DateFrom extends AbstractValueFieldData<Date> {
		private static final long serialVersionUID = 1L;
	}

	public static class DateTo extends AbstractValueFieldData<Date> {
		private static final long serialVersionUID = 1L;
	}

	public static class FilesCompany extends AbstractValueFieldData<Long> {
		private static final long serialVersionUID = 1L;
	}

	public static class FilesImportType extends AbstractValueFieldData<Long> {
		private static final long serialVersionUID = 1L;
	}

	public static class FilesName extends AbstractValueFieldData<String> {
		private static final long serialVersionUID = 1L;
	}
}
