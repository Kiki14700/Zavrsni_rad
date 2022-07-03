package org.eclipse.scout.apps.xlsxreader.beans;

import java.io.Serializable;

public class StockBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;
	private Integer quantity;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
