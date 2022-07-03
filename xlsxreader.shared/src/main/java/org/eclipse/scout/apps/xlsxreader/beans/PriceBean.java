package org.eclipse.scout.apps.xlsxreader.beans;

import java.io.Serializable;

public class PriceBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String priceHRK;
	private String tax;
	private String date;
	private String serviceCode;

	public String getPriceHRK() {
		return priceHRK;
	}

	public void setPriceHRK(String priceHRK) {
		this.priceHRK = priceHRK;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

}
