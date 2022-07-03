package org.eclipse.scout.apps.xlsxreader.xlsxhelper;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;
import org.docx4j.openpackaging.parts.SpreadsheetML.WorksheetPart;
import org.eclipse.scout.apps.xlsxreader.beans.ClientBean;
import org.eclipse.scout.apps.xlsxreader.beans.PriceBean;
import org.eclipse.scout.apps.xlsxreader.beans.ServiceBean;
import org.eclipse.scout.apps.xlsxreader.beans.StockBean;
import org.eclipse.scout.apps.xlsxreader.common.helpers.MessageBoxHelpers;
import org.xlsx4j.sml.Row;

public class ScoutXlsxSpreadsheetAdapter {
	
	public static List<ClientBean> newClientBeanFromXLSXFile(InputStream is) {
		ArrayList<ClientBean> bl = new ArrayList<ClientBean>();

		try {

			XlsxAdapter xlsxAdapter = new XlsxAdapter(is);
			xlsxAdapter.getSheets();
			WorksheetPart a = xlsxAdapter.getSheets().get(0);
			List<Row> data = a.getJaxbElement().getSheetData().getRow();
			for (int i = 1; i < data.size(); i++) {
				ClientBean bean = new ClientBean();
				String s1 = xlsxAdapter.getCellValue(a, i, 0);
				String s2 = xlsxAdapter.getCellValue(a, i, 1);
				String s3 = xlsxAdapter.getCellValue(a, i, 2);
				String s4 = xlsxAdapter.getCellValue(a, i, 3);
				String s5 = xlsxAdapter.getCellValue(a, i, 4);
				String s6 = xlsxAdapter.getCellValue(a, i, 5);
				String s7 = xlsxAdapter.getCellValue(a, i, 6);
				String s8 = xlsxAdapter.getCellValue(a, i, 7);
				if (s1 == null || s2 == null || s3 == null || s4 == null || s5 == null || s6 == null || s7 == null
						|| s8 == null)
					continue;
				bean.setName(s1.trim());
				bean.setSurname(s2.trim());
				bean.setTown(s3.trim());
				bean.setAddress(s4.trim());
				bean.setOIB(s5.trim());
				bean.setEmail(s6.trim());
				bean.setPhone(s7.trim());
				bean.setDateOfBirth(s8.trim());
				bl.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return bl;
	}

	public static List<ClientBean> updateClientBeanFromXLSXFile(InputStream is) {
		ArrayList<ClientBean> bl = new ArrayList<ClientBean>();
		try {

			XlsxAdapter xlsxAdapter = new XlsxAdapter(is);
			xlsxAdapter.getSheets();
			WorksheetPart a = xlsxAdapter.getSheets().get(0);
			List<Row> data = a.getJaxbElement().getSheetData().getRow();
			for (int i = 1; i < data.size(); i++) {
				ClientBean bean = new ClientBean();
				String s1 = xlsxAdapter.getCellValue(a, i, 0);
				String s2 = xlsxAdapter.getCellValue(a, i, 1);
				String s3 = xlsxAdapter.getCellValue(a, i, 2);
				String s4 = xlsxAdapter.getCellValue(a, i, 3);
				String s5 = xlsxAdapter.getCellValue(a, i, 4);
				String s6 = xlsxAdapter.getCellValue(a, i, 5);
				String s7 = xlsxAdapter.getCellValue(a, i, 6);
				String s8 = xlsxAdapter.getCellValue(a, i, 7);
				String s9 = xlsxAdapter.getCellValue(a, i, 8);
				if (s1 == null || s2 == null || s3 == null || s4 == null || s5 == null || s6 == null || s7 == null
						|| s8 == null || s9 == null)
					continue;
				bean.setCode(s1.trim());
				bean.setName(s2.trim());
				bean.setSurname(s3.trim());
				bean.setTown(s4.trim());
				bean.setAddress(s5.trim());
				bean.setOIB(s6.trim());
				bean.setEmail(s7.trim());
				bean.setPhone(s8.trim());
				bean.setDateOfBirth(s9.trim());
				bl.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return bl;
	}

	public static List<ServiceBean> newServiceBeanFromXLSXFile(InputStream is) {
		ArrayList<ServiceBean> bl = new ArrayList<ServiceBean>();
		try {

			XlsxAdapter xlsxAdapter = new XlsxAdapter(is);
			xlsxAdapter.getSheets();
			WorksheetPart a = xlsxAdapter.getSheets().get(0);
			List<Row> data = a.getJaxbElement().getSheetData().getRow();
			for (int i = 1; i < data.size(); i++) {
				ServiceBean bean = new ServiceBean();
				String s1 = xlsxAdapter.getCellValue(a, i, 0);
				String s2 = xlsxAdapter.getCellValue(a, i, 1);
				String s3 = xlsxAdapter.getCellValue(a, i, 2);

				if (s1 == null || s2 == null || s3 == null)
					continue;
				bean.setName(s1.trim());
				bean.setCategory(s2.trim());
				bean.setWorkplace(s3.trim());
				bl.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return bl;
	}

	public static List<ServiceBean> updateServiceBeanFromXLSXFile(InputStream is) {
		ArrayList<ServiceBean> bl = new ArrayList<ServiceBean>();
		try {

			XlsxAdapter xlsxAdapter = new XlsxAdapter(is);
			xlsxAdapter.getSheets();
			WorksheetPart a = xlsxAdapter.getSheets().get(0);
			List<Row> data = a.getJaxbElement().getSheetData().getRow();
			for (int i = 1; i < data.size(); i++) {
				ServiceBean bean = new ServiceBean();
				String s1 = xlsxAdapter.getCellValue(a, i, 0);
				String s2 = xlsxAdapter.getCellValue(a, i, 1);
				String s3 = xlsxAdapter.getCellValue(a, i, 2);
				String s4 = xlsxAdapter.getCellValue(a, i, 3);
				if (s1 == null || s2 == null || s3 == null || s4 == null)
					continue;
				bean.setCode(s1.trim());
				bean.setName(s2.trim());
				bean.setCategory(s3.trim());
				bean.setWorkplace(s4.trim());
				bl.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return bl;
	}

	public static List<PriceBean> addPriceBeanFromXLSXFile(InputStream is) {
		ArrayList<PriceBean> bl = new ArrayList<PriceBean>();
		try {

			XlsxAdapter xlsxAdapter = new XlsxAdapter(is);
			xlsxAdapter.getSheets();
			WorksheetPart a = xlsxAdapter.getSheets().get(0);
			List<Row> data = a.getJaxbElement().getSheetData().getRow();
			for (int i = 1; i < data.size(); i++) {
				PriceBean bean = new PriceBean();
				String s1 = xlsxAdapter.getCellValue(a, i, 0);
				String s2 = xlsxAdapter.getCellValue(a, i, 1);
				String s3 = xlsxAdapter.getCellValue(a, i, 2);
				String s4 = xlsxAdapter.getCellValue(a, i, 3);

				if (s1 == null || s2 == null || s3 == null || s4 == null)
					continue;

				bean.setServiceCode(s1.trim());
				bean.setTax(s2.trim());
				bean.setDate(s3.trim());
				bean.setPriceHRK(s4.trim());
				bl.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return bl;
	}

	public static List<StockBean> fillStockImportBeanFromXLSXFile(InputStream is) {
		
	
		
		ArrayList<StockBean> bl = new ArrayList<StockBean>();
		try {

			XlsxAdapter xlsxAdapter = new XlsxAdapter(is);
			xlsxAdapter.getSheets();
			WorksheetPart a = xlsxAdapter.getSheets().get(0);
			List<Row> data = a.getJaxbElement().getSheetData().getRow();
			for (int i = 1; i < data.size(); i++) {
				StockBean bean = new StockBean();
				String s1 = xlsxAdapter.getCellValue(a, i, 0);
				String s2 = xlsxAdapter.getCellValue(a, i, 1);
				if (s1 == null || s2 == null) {
					continue;
				}
					
				bean.setCode(s1.trim());
				bean.setQuantity(Integer.valueOf(s2.trim()));
				bl.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return bl;
	}

}
