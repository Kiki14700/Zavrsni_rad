package org.eclipse.scout.apps.xlsxreader.common.forms;

import java.util.Arrays;
import java.util.List;

import org.eclipse.scout.apps.xlsxreader.common.forms.XlsxTemplateFormData.TemplateTable.TemplateTableRowData;
import org.eclipse.scout.rt.platform.holders.BeanArrayHolder;
import org.eclipse.scout.rt.platform.holders.ByteArrayHolder;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.util.IOUtility;
import org.eclipse.scout.rt.server.jdbc.SQL;

public class XlsxTemplateService implements IXlsxTemplateService {
	@Override
	public XlsxTemplateFormData prepareCreate(XlsxTemplateFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}

	@Override
	public XlsxTemplateFormData create(XlsxTemplateFormData formData) {

		return formData;
	}

	@Override
	public XlsxTemplateFormData load(XlsxTemplateFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}

	@Override
	public XlsxTemplateFormData store(XlsxTemplateFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}

	@Override
	public void addTemplateFile(Long formID, String fileName, byte[] content) {

		StringBuffer  varname1 = new StringBuffer();
		varname1.append("INSERT INTO FILE ");
		varname1.append("            (name, ");
		varname1.append("             file_content, ");
		varname1.append("             import_time, ");
		varname1.append("             form_id) ");
		varname1.append("VALUES      (:fileName, ");
		varname1.append("             :content, ");
		varname1.append("             Now(), ");
		varname1.append("             :formID)");
		
		SQL.insert(varname1.toString(), new NVPair("fileName", fileName),
				new NVPair("content", content), new NVPair("formID", formID));
		
	}

	@Override
	public List<TemplateTableRowData> fetchTempalateFiles(Long formID) {

		BeanArrayHolder<TemplateTableRowData> rowData = new BeanArrayHolder<>(TemplateTableRowData.class);
		
		StringBuffer  varname1 = new StringBuffer();
		varname1.append("SELECT id, ");
		varname1.append("       NAME ");
		varname1.append("FROM   FILE ");
		varname1.append("WHERE  form_id = :formID ");
		varname1.append("into   :{rows.ID}, ");
		varname1.append("       :{rows.Name}");
		
		SQL.selectInto(varname1.toString(), new NVPair("formID", formID), new NVPair("rows", rowData));
		
		return Arrays.asList(rowData.getBeans());
	}

	@Override
	public byte[] getTemplateFile(Integer fileID) {
		
		ByteArrayHolder content = new ByteArrayHolder();
		
		StringBuffer  varname1 = new StringBuffer();
		varname1.append("SELECT file_content ");
		varname1.append("FROM   file ");
		varname1.append("WHERE  id = :fileID ");
		varname1.append("INTO   :content");
		
		SQL.selectInto(varname1.toString(), new NVPair("fileID", fileID), new NVPair("content", content));

		
		return content.getValue();
	}

	@Override
	public void deleteFile(List<Integer> fileID) {

		StringBuffer  varname1 = new StringBuffer();
		varname1.append("DELETE FROM FILE ");
		varname1.append("WHERE  id = :fileID");
		
		SQL.delete(varname1.toString(), new NVPair("fileID", fileID));
		
	}

}
