package com.danimaniarqsoft.report.constants;

import org.apache.poi.ss.usermodel.CellStyle;

/**
 * Enum for Text Position or aligment, It is Used for ExcelColumn Annotation and
 * it represent a Wrapper for org.apache.poi.ss.usermodel.CellStyle Constant
 * Class
 * 
 * @author Daniel Cortes Pichardo
 * 
 */
public enum TextPosition {
	ALIGN_CENTER(CellStyle.ALIGN_CENTER), VERTICAL_BOTTOM(CellStyle.VERTICAL_BOTTOM), ALIGN_CENTER_SELECTION(
			CellStyle.ALIGN_CENTER_SELECTION), VERTICAL_CENTER(CellStyle.VERTICAL_CENTER), ALIGN_FILL(
					CellStyle.ALIGN_FILL), ALIGN_GENERAL(CellStyle.ALIGN_CENTER), VERTICAL_JUSTIFY(
							CellStyle.VERTICAL_JUSTIFY), VERTICAL_TOP(CellStyle.VERTICAL_TOP), ALIGN_LEFT(
									CellStyle.ALIGN_LEFT), ALIGN_RIGHT(CellStyle.ALIGN_RIGHT);

	private short keyCode;

	private TextPosition(short keyCode) {
		this.keyCode = keyCode;
	}

	public short getKeyCode() {
		return this.keyCode;
	}
}