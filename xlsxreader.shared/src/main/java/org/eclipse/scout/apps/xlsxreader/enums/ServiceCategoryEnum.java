package org.eclipse.scout.apps.xlsxreader.enums;

import java.util.ArrayList;
import java.util.List;

public enum ServiceCategoryEnum {

	LICE(0, "Lice"), LASER_DERMA(1, "Laser derma"), EDUKACIJA(2, "Edukacija"), UGRADNJA(3, "Ugradnja"),
	PREGLED(4, "Pregled"), MALOPRODAJA(5, "Maloprodaja"), SITNI_ZAHVATI(6, "Sitni zahvati"),
	VELIKI_ZAHVATI(7, "Veliki zahvati");

	public final int code;
	public final String nameKey;

	ServiceCategoryEnum(final int code, final String nameKey) {
		this.code = code;
		this.nameKey = nameKey;
	}

	public static ServiceCategoryEnum getValueByCode(final int code) {
		for (final ServiceCategoryEnum data : values()) {
			if (data.code == code) {
				return data;
			}
		}
		throw new IllegalArgumentException("DataType: " + code);
	}
	
	public static Integer getValueByName(final String name) {
		for (final ServiceCategoryEnum data : values()) {
			if (data.nameKey.equals(name)) {
				return data.code;
			}
		}
		return null;
		//throw new IllegalArgumentException("DataType: " + name);
	}
	
	
	

}
