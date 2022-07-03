package org.eclipse.scout.apps.xlsxreader.helpers;

import java.math.BigDecimal;
import java.util.Date;

import org.eclipse.scout.rt.platform.exception.ProcessingException;

public class SQLHelper {
	
	 public static int getInt(Object[][] result) throws ProcessingException {
			int retval = -1;
			if (result != null && result.length == 1)
			    try {
				retval = ((Long) result[0][0]).intValue();
			    } catch (Exception e) {
				return -1;
			    }

			return retval;
		    }

		    public static BigDecimal getBigDecimal(Object[][] result) throws ProcessingException {
			BigDecimal retval = new BigDecimal(0);
			if (result != null && result.length == 1)
			    try {
				retval = ((BigDecimal) result[0][0]);
			    } catch (Exception e) {
				return new BigDecimal(0);
			    }

			return retval;
		    }

		    public static Date getDate(Object[][] result) throws ProcessingException {
			Date retval = new Date();
			if (result != null && result.length == 1)
			    try {
				retval = ((Date) result[0][0]);
			    } catch (Exception e) {
				return null;
			    }
			else
			    return null;

			return retval;
		    }

		    public static String getString(Object[][] result) throws ProcessingException {
			String retval = "";
			if (result != null && result.length == 1)
			    try {
				retval = ((String) result[0][0]);
			    } catch (Exception e) {
				return "";
			    }

			return retval;
		    }

		    public static boolean getBoolean(Object[][] result) throws ProcessingException {
			boolean retval = false;
			if (result != null && result.length == 1)
			    try {
				retval = ((boolean) result[0][0]);
			    } catch (Exception e) {
				return false;
			    }

			return retval;
		    }

}
