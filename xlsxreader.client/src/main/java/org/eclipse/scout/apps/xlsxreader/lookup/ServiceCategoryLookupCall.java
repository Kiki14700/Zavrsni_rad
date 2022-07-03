package org.eclipse.scout.apps.xlsxreader.lookup;

import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;
import org.eclipse.scout.rt.shared.services.lookup.LocalLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.scout.apps.xlsxreader.enums.ServiceCategoryEnum;

public class ServiceCategoryLookupCall extends LocalLookupCall<Integer>{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected List<? extends ILookupRow<Integer>> execCreateLookupRows() {
		List<ILookupRow<Integer>> rows = new ArrayList<>();
		
		for(ServiceCategoryEnum scEnum : ServiceCategoryEnum.values()) {
			rows.add(new LookupRow<Integer>(scEnum.code, scEnum.nameKey));
		}
		
		return rows;
	}
	

}
