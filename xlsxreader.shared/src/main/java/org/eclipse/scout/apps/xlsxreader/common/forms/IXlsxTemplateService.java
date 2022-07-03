package org.eclipse.scout.apps.xlsxreader.common.forms;

import java.util.List;

import org.eclipse.scout.apps.xlsxreader.common.forms.XlsxTemplateFormData.TemplateTable.TemplateTableRowData;
import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IXlsxTemplateService extends IService {
	XlsxTemplateFormData prepareCreate(XlsxTemplateFormData formData);

	XlsxTemplateFormData create(XlsxTemplateFormData formData);

	XlsxTemplateFormData load(XlsxTemplateFormData formData);

	XlsxTemplateFormData store(XlsxTemplateFormData formData);


	void addTemplateFile(Long formID, String fileName, byte[] content);

	List<TemplateTableRowData> fetchTempalateFiles(Long formID);

	byte[] getTemplateFile(Integer fileID);

	void deleteFile(List<Integer> fileID);
}
