package org.eclipse.scout.apps.xlsxreader.lookup;

import java.util.ArrayList;
import java.util.List;


import org.eclipse.scout.apps.xlsxreader.enums.WorkplaceEnum;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;
import org.eclipse.scout.rt.shared.services.lookup.LocalLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

public class WorkplaceLookupcall extends LocalLookupCall<Integer>{
	
	private static final long serialVersionUID = 1L;
	private List<WorkplaceEnum> enumList = new ArrayList<>();
	
	@Override
	protected List<? extends ILookupRow<Integer>> execCreateLookupRows() {


		List<ILookupRow<Integer>> rows = new ArrayList<>();
		
		if(enumList == null) {
			return rows;
		}
		
		
		for(WorkplaceEnum wEnum : WorkplaceEnum.values()) {
			rows.add(new LookupRow<Integer>(wEnum.code, wEnum.nameKey));
		}
		
		
		return rows;
	}

}
