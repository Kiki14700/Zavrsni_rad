package org.eclipse.scout.apps.xlsxreader.lookup;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.scout.apps.xlsxreader.constants.Constants;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;
import org.eclipse.scout.rt.shared.services.lookup.LocalLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

public class StockImportTypeLookupCall extends LocalLookupCall<Long>{
	
	private static final long serialVersionUID = 1L;
	
	 @Override
	    protected List<? extends ILookupRow<Long>> execCreateLookupRows() {

		List<ILookupRow<Long>> rows = new ArrayList<>();
		rows.add(new LookupRow<Long>(Constants.Dodavanje_usluga_na_skladište, TEXTS.get("StockImportTypeLookupCall.ImportNewItems")));
		rows.add(new LookupRow<Long>(Constants.Nabava_robe, TEXTS.get("StockImportTypeLookupCall.AcquiringGoods")));
		rows.add(new LookupRow<Long>(Constants.Inventura, TEXTS.get("StockImportTypeLookupCall.Inventory")));
		return rows;
	    }

}
