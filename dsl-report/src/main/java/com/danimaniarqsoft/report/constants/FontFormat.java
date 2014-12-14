package com.danimaniarqsoft.report.constants;

import org.apache.poi.ss.usermodel.Font;

/**
 * Enum for Font format, It is Used for ExcelColumn Annotation and it represent
 * a Wrapper for org.apache.poi.ss.usermodel.Font Constant Class
 * 
 * @author Daniel Cortes Pichardo
 * 
 */
public enum FontFormat {
	BOLD(Font.BOLDWEIGHT_BOLD), 
	NORMAL(Font.BOLDWEIGHT_NORMAL), 
	COLOR_RED(Font.COLOR_RED),
	COLOR_GREEN((short)17),
	COLOR_BLUE((short)12),
	COLOR_BROWN((short)16),
	COLOR_ORANGE((short)53),
	COLOR_GRAY((short)23),
	COLOR_PINK((short)6),
	COLOR_PURPLE((short)20),
	COLOR_YELLOW((short)51),
	COLOR_WHITE((short)1),
	COLOR_NORMAL(Font.COLOR_NORMAL), 
	ITALIC, 
	STRIKEOUT,
	/** Enums for Fonts Names **/
	ARIAL("Arial"), CALIBRI("Calibri"), TIMES_NEW_ROMAN("Times New Roman")
	;
	private short keyCode;

	private String fontName = "[NAME NOT SOPORTED]";

	private FontFormat() {
	}

	private FontFormat(short keyCode) {
		this.keyCode = keyCode;
	}

	private FontFormat(String fontName) {
		this.fontName = fontName;
	}

	public short getKeyCode() {
		return this.keyCode;
	}

	public String getFontName() {
		return fontName;
	}
}
