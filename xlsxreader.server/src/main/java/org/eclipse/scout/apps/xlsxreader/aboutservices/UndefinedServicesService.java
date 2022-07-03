package org.eclipse.scout.apps.xlsxreader.aboutservices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.docx4j.docProps.variantTypes.Array;
import org.eclipse.scout.apps.xlsxreader.aboutservices.ServicesTablePageData.ServicesTableRowData;
import org.eclipse.scout.apps.xlsxreader.aboutservices.UndefinedServicesFormData.UndefinedServicesTable.UndefinedServicesTableRowData;
import org.eclipse.scout.apps.xlsxreader.beans.UndefinedServicesDTO;
import org.eclipse.scout.rt.platform.holders.BeanArrayHolder;
import org.eclipse.scout.rt.platform.holders.IntegerArrayHolder;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.server.jdbc.SQL;

public class UndefinedServicesService implements IUndefinedServicesService {
	@Override
	public UndefinedServicesFormData prepareCreate(UndefinedServicesFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}

	@Override
	public UndefinedServicesFormData create(UndefinedServicesFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}

	@Override
	public UndefinedServicesFormData load(UndefinedServicesFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}

	@Override
	public UndefinedServicesFormData store(UndefinedServicesFormData formData) {
		// TODO [Kristian Knezevic] add business logic here.
		return formData;
	}

	@Override
	public List<UndefinedServicesTableRowData> fetchServices(int companyID) {
		BeanArrayHolder<UndefinedServicesDTO> rowData = new BeanArrayHolder<>(UndefinedServicesDTO.class);
		List<Integer> IDs = getIDServiceWithPrice();
		List<UndefinedServicesTableRowData> rowsToShow = new ArrayList<>();

		StringBuffer varname1 = new StringBuffer();
		varname1.append("SELECT id, ");
		varname1.append("       name, ");
		varname1.append("       code, ");
		varname1.append("       service_category, ");
		varname1.append("       workplace_id ");
		varname1.append("FROM   service ");
		varname1.append("WHERE  company_id = :companyID ");
		varname1.append("INTO   :{rows.ID}, ");
		varname1.append("       :{rows.name}, ");
		varname1.append("       :{rows.code}, ");
		varname1.append("       :{rows.category}, ");
		varname1.append("       :{rows.workplace} ");

		SQL.selectInto(varname1.toString(), new NVPair("rows", rowData), new NVPair("companyID", companyID));

		List<UndefinedServicesDTO> holderValues = Arrays.asList(rowData.getBeans());

		for (UndefinedServicesDTO dto : holderValues) {
			int id = dto.getID();
			if (!IDs.contains(id)) {
				UndefinedServicesTableRowData row = new UndefinedServicesTableRowData();
				row.setID(dto.getID());
				row.setName(dto.getName());
				row.setCode(dto.getCode());
				row.setCategory(dto.getCategory());
				row.setWorkplace(dto.getWorkplace());
				rowsToShow.add(row);
			}
		}

		return rowsToShow;
	}

	public List<Integer> getIDServiceWithPrice() {
		UndefinedServicesFormData formData = new UndefinedServicesFormData();
		IntegerArrayHolder IDs = new IntegerArrayHolder();

		String stmt = "SELECT DISTINCT service_id FROM price INTO :IDs";
		SQL.selectInto(stmt, new NVPair("IDs", IDs), formData);

		return Arrays.asList(IDs.getValue());
	}

}
