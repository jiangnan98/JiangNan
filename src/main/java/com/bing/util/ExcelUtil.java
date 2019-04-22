package com.bing.util;

import org.apache.poi.ss.usermodel.Cell;

import java.text.DecimalFormat;
import java.text.ParseException;

public class ExcelUtil {

	public static String getCellValue(Cell cell) {
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_BLANK:
				return "";
			case Cell.CELL_TYPE_NUMERIC:
				String strValue = String.valueOf(cell.getNumericCellValue());
				if (strValue != null && strValue.indexOf(".") != -1
						&& strValue.indexOf("E") != -1) {
					try {
						return new DecimalFormat().parse(strValue).toString();
					} catch (ParseException e) {
						e.printStackTrace();
					}
				} else {
					if (strValue.endsWith(".0")) {
						return strValue.substring(0, strValue.indexOf(".0"));
					} else {
						return strValue;
					}
				}
			case Cell.CELL_TYPE_STRING:
				return (cell.getStringCellValue() + "").trim();
			case Cell.CELL_TYPE_FORMULA:
				return (cell.getCellFormula() + "").trim();
			case Cell.CELL_TYPE_BOOLEAN:
				return cell.getBooleanCellValue() + "";
			case Cell.CELL_TYPE_ERROR:
				return cell.getErrorCellValue() + "";
			}
		}
		return "";
	}
}
