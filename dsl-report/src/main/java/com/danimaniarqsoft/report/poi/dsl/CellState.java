package com.danimaniarqsoft.report.poi.dsl;

import static com.googlecode.charts4j.collect.Preconditions.checkNotNull;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.danimaniarqsoft.report.constants.TextPosition;
import com.danimaniarqsoft.report.model.CellFormatContext;
import com.danimaniarqsoft.report.poi.PoiUtil;

/**
 * State for the el DSL in Workbook
 * 
 * @author Daniel Cortes Pichardo
 * 
 */
public class CellState {

	private WorkbookContext context;

	public CellState(WorkbookContext context) {
		this.context = context;
		this.context.setNextColumnNum(0);
		createNextRow(context);

	}

	public CellState createRow() {
		this.context.setNextColumnNum(0);
		createNextRow(context);
		return this;
	}

	public CellState createRow(Map<String, Object> rowMap) {
		this.context.setNextColumnNum(0);
		createNextRow(context);
		for (Map.Entry<String, Object> entry : rowMap.entrySet()) {
			this.createCell(entry.getValue());
		}
		return this;
	}

	public void createCell(Object value) {
		CellStyle cellStyle = PoiUtil.createCellStyle(context.getWorkbook(),
				defaultCellFormatContext());
		createCell(value, cellStyle);
	}

	private CellFormatContext defaultCellFormatContext() {
		CellFormatContext defaultContext = new CellFormatContext();
		defaultContext.setTextPosition(new TextPosition[] {
				TextPosition.ALIGN_CENTER, TextPosition.VERTICAL_CENTER });
		return defaultContext;
	}

	public CellState createCell(Object value, CellStyle cellStyle) {
		checkNotNull(value, "createCell: The value for the Cell cannot be null");
		Class<? extends Object> classTest = value.getClass();
		if (classTest == BigDecimal.class) {
			PoiUtil.createCell(context.getCurrentRow(),
					context.getNextColumnNum(), (BigDecimal) value, cellStyle);
		} else if (classTest == Double.class) {
			PoiUtil.createCell(context.getCurrentRow(),
					context.getNextColumnNum(), (Double) value, cellStyle);
		} else if (classTest == String.class) {
			PoiUtil.createCell(context.getCurrentRow(),
					context.getNextColumnNum(), (String) value, cellStyle);
		} else if (classTest == Boolean.class) {
			PoiUtil.createCell(context.getCurrentRow(),
					context.getNextColumnNum(), (Boolean) value, cellStyle);
		} else if (classTest == Calendar.class) {
			PoiUtil.createCell(context.getCurrentRow(),
					context.getNextColumnNum(), (Calendar) value, cellStyle);
		} else if (classTest == RichTextString.class) {
			PoiUtil.createCell(context.getCurrentRow(),
					context.getNextColumnNum(), (RichTextString) value,
					cellStyle);
		} else if (classTest == Integer.class) {
			PoiUtil.createCell(context.getCurrentRow(),
					context.getNextColumnNum(), (Integer) value, cellStyle);
		}
		return this;
	}

	public CellState createDateCell(Date value, String dateFormat,
			CellStyle cellStyle) {
		PoiUtil.createDateCell(context.getCurrentRow(),
				context.getNextColumnNum(), value, context.getWorkbook(),
				dateFormat, cellStyle);
		return this;
	}

	public CellState createCellFormula(String formula) {
		PoiUtil.createCellFormula(context.getCurrentRow(),
				context.getNextColumnNum(), formula);
		return this;
	}

	public SheetState createSheet(String sheetName) {
		Sheet sheet = PoiUtil.createSheet(context.getWorkbook(), sheetName);
		context.setCurrentSheet(sheet);
		return new SheetState(context);
	}

	public Workbook buildWorkbook() {
		return context.getWorkbook();
	}

	private void createNextRow(WorkbookContext context) {
		context.setCurrentRow(context.getCurrentSheet().createRow(
				this.context.getNextRowNum()));
	}
}
