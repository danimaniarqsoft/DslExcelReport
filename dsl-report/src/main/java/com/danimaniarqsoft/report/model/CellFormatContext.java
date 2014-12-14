package com.danimaniarqsoft.report.model;

import com.danimaniarqsoft.report.constants.CellType;
import com.danimaniarqsoft.report.constants.FontFormat;
import com.danimaniarqsoft.report.constants.TextPosition;

/**
 * Class used for build a Cell
 * 
 * @author Daniel Cortes Pichardo
 * 
 */
public class CellFormatContext {
	private CellType cellType;
	private String dateFormat;
	private TextPosition[] textPosition;
	private FontFormat[] fontFormat;

	public CellType getCellType() {
		return cellType;
	}

	public void setCellType(CellType cellType) {
		this.cellType = cellType;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public TextPosition[] getTextPosition() {
		return textPosition;
	}

	public void setTextPosition(TextPosition[] textPosition) {
		this.textPosition = textPosition;
	}

	public FontFormat[] getFontFormat() {
		return fontFormat;
	}

	public void setFontFormat(FontFormat[] fontFormat) {
		this.fontFormat = fontFormat;
	}

}
