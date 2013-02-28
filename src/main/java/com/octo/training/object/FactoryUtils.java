package com.octo.training.object;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FactoryUtils {

	private FactoryUtils() {
	}

	public static Object getValueFromRs(ResultSet rs, String columnName) {
		Object val = null;
		try {
			
			val = rs.getObject(columnName);
			if ("".equals(val))
			{
				val = null;
			}
		}
			
		catch (SQLException sqle) {
			val = null;
		}
		return val;
	}

	public static Long getLongValueFromRs(ResultSet rs, String columnName) {
		Long val = null;
		try {
			if (rs.getString(columnName) != null) {
				val = new Long(rs.getLong(columnName));
			}
		}
		catch (SQLException sqle) {
			val = null;
		}
		return val;
	}

	public static Byte getByteValueFromRs(ResultSet rs, String columnName) {
		Byte val = null;
		try {
			if (rs.getString(columnName) != null) {
				val = new Byte(rs.getByte(columnName));
			}
		}
		catch (SQLException sqle) {
			val = null;
		}
		return val;
	}

}
