package org.eclipse.scout.apps.xlsxreader.beans;

import java.io.Serializable;

public class UndefinedServicesDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer ID;
	private String name;
	private String code;
	private Integer category;
	private Integer workplace;
	

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getWorkplace() {
		return workplace;
	}

	public void setWorkplace(Integer workplace) {
		this.workplace = workplace;
	}


}
