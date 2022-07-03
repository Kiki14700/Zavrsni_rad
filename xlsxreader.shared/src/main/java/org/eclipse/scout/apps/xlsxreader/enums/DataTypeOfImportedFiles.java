package org.eclipse.scout.apps.xlsxreader.enums;

public enum DataTypeOfImportedFiles {
	
	CLIENT(0, "Client"), SERVICE(1, "Serivice"), STOCK(2, "Stock"), PRICE(3, "Price");

    public final int code;
    public final String nameKey;
    
    DataTypeOfImportedFiles(final int code, final String nameKey) {
    	this.code = code;
    	this.nameKey = nameKey;
        }
	
    public static DataTypeOfImportedFiles getValueOf(final int code) {
    	for (final DataTypeOfImportedFiles data : values()) {
    	    if (data.code == code) {
    		return data;
    	    }
    	}
    	throw new IllegalArgumentException("DataType: " + code);
        }

}
