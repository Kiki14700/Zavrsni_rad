package org.eclipse.scout.apps.xlsxreader.enums;

public enum WorkplaceEnum {
	
	KIRURGIJA(0, "Kirurgija"), ESTETSKA_MEDICINA(1, "Estetska medicina"), KOZMETOLOGIJA(2, "Kozmetologija"),
	STOMATOLOGIJA(3, "Stomatologija"),ORTOPEDIJA(4, "Ortopedija"), ANESTEZIOLOGIJA(5, "Anesteziologija"),
	PEDIJATRIJA(6, "Pedijatrija"), RADIOLOGIJA(7, "Radiologija"), ORTODONCIJA(8, "Ortodoncija");

    public final int code;
    public final String nameKey;
    
    WorkplaceEnum(final int code, final String nameKey) {
    	this.code = code;
    	this.nameKey = nameKey;
        }
	
    public static WorkplaceEnum getValueOf(final int code) {
    	for (final WorkplaceEnum data : values()) {
    	    if (data.code == code) {
    		return data;
    	    }
    	}
    	throw new IllegalArgumentException("DataType: " + code);
        }


    public static Integer getValueByName(final String name) {
		for (final WorkplaceEnum data : values()) {
			if (data.nameKey.equals(name)) {
				return data.code;
			}
		}
		return null;
		//throw new IllegalArgumentException("DataType: " + name);
	}
}
