package org.eclipse.scout.apps.xlsxreader.files.lookup;

import java.util.List;

import org.eclipse.scout.apps.xlsxreader.server.ServerSession;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;
import org.eclipse.scout.rt.testing.platform.runner.RunWithSubject;
import org.eclipse.scout.rt.testing.server.runner.RunWithServerSession;
import org.eclipse.scout.rt.testing.server.runner.ServerTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWithSubject("anonymous")
@RunWith(ServerTestRunner.class)
@RunWithServerSession(ServerSession.class)
public class StockLookupCallTest {
	// TODO [Kristian Knezevic] add test cases

	protected StockLookupCall createLookupCall() {
		return new StockLookupCall();
	}

	@Test
	public void testGetDataByAll() {
		StockLookupCall call = createLookupCall();
		// TODO [Kristian Knezevic] fill call
		List<? extends ILookupRow<Long>> data = call.getDataByAll();
		// TODO [Kristian Knezevic] verify data
	}

	@Test
	public void testGetDataByKey() {
		StockLookupCall call = createLookupCall();
		// TODO [Kristian Knezevic] fill call
		List<? extends ILookupRow<Long>> data = call.getDataByKey();
		// TODO [Kristian Knezevic] verify data
	}

	@Test
	public void testGetDataByText() {
		StockLookupCall call = createLookupCall();
		// TODO [Kristian Knezevic] fill call
		List<? extends ILookupRow<Long>> data = call.getDataByText();
		// TODO [Kristian Knezevic] verify data
	}
}
