package org.eclipse.scout.apps.xlsxreader.lookup;

import java.util.ArrayList;

import java.util.List;

import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;
import org.eclipse.scout.rt.shared.services.lookup.LocalLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;
import org.eclipse.scout.apps.xlsxreader.constants.Constants;

public class ImportTypeLookupCall extends LocalLookupCall<Long>{
	
	private static final long serialVersionUID = 1L;

    @Override
    protected List<? extends ILookupRow<Long>> execCreateLookupRows() {

	List<ILookupRow<Long>> rows = new ArrayList<>();
	rows.add(new LookupRow<Long>(Constants.Dodavanje_novih_stavki, TEXTS.get("ServiceImportTypeLookupCall.ImportNewItems")));
	rows.add(new LookupRow<Long>(Constants.Azuriranje, TEXTS.get("ServiceImportTypeLookupCall.Updating")));
	return rows;
    }

}
