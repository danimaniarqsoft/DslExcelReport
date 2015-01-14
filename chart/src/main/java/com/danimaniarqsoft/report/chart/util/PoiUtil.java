package com.danimaniarqsoft.report.chart.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import net.sf.jasperreports.engine.export.oasis.StyleBuilder;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility Class used for build a Workbook from Excel Annotations.
 * <p>
 * This Class is has a lot of methods for build rows, and cells with differents
 * formats.
 * 
 * @author Daniel Cortes Pichardo
 * 
 */
public class PoiUtil {

	private static final Logger LOG = LoggerFactory.getLogger(PoiUtil.class);

	/**
	 * Create a Sheet safety, it is a Wrapper of the WorkbookUtil of Apache poi
	 * 
	 * @param workbook
	 * @param sheetName
	 * @return Sheet
	 */
	public static Sheet createSheet(Workbook workbook, String sheetName) {
		return workbook
				.createSheet(WorkbookUtil.createSafeSheetName(sheetName));
	}

	/**
	 * Method that create a boolean Cell
	 * 
	 * @param row
	 * @param nextCol
	 * @param value
	 * @param cellStyle
	 * @return
	 */
	public static Cell createCell(Row row, int nextCol, boolean value,
			CellStyle cellStyle) {

		Cell cell = row.createCell(nextCol++);
		cell.setCellValue(value);
		cell.setCellStyle(cellStyle);
		return cell;
	}

	/**
	 * Method that create a Calendar Cell
	 * 
	 * @param row
	 * @param nextCol
	 * @param value
	 * @param cellStyle
	 * @return
	 */
	public static Cell createCell(Row row, int nextCol, Calendar value,
			CellStyle cellStyle) {
		CellStyle s = row.getSheet().getWorkbook().createCellStyle();
		s.setAlignment(CellStyle.ALIGN_CENTER);
		s.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		Cell cell = row.createCell(nextCol++);
		cell.setCellValue(value);
		cell.setCellStyle(s);
		return cell;
	}

	/**
	 * Method that create a RichTextString Cell
	 * 
	 * @param row
	 * @param nextCol
	 * @param value
	 * @param cellStyle
	 * @return
	 */
	public static Cell createCell(Row row, int nextCol, RichTextString value,
			CellStyle cellStyle) {
		Cell cell = row.createCell(nextCol++);
		cell.setCellValue(value);
		cell.setCellStyle(cellStyle);
		return cell;
	}

	/**
	 * Method that create a String Cell
	 * 
	 * @param row
	 * @param nextCol
	 * @param value
	 * @param cellStyle
	 * @return
	 */

	public static Cell createCell(Row row, int nextCol, String value,
			CellStyle cellStyle) {
		Cell cell = row.createCell(nextCol++);
		cell.setCellValue(value);
		cell.setCellStyle(cellStyle);
		return cell;
	}

	/**
	 * Method that create a BigDecimal Cell
	 * 
	 * @param row
	 * @param nextCol
	 * @param value
	 * @param cellStyle
	 * @return
	 */
	public static Cell createCell(Row row, int nextCol, BigDecimal value,
			CellStyle cellStyle) {
		return createCell(row, nextCol, value.doubleValue(), cellStyle);
	}

	/**
	 * Method that create a Double Cell
	 * 
	 * @param row
	 * @param nextCol
	 * @param value
	 * @param cellStyle
	 * @return
	 */
	public static Cell createCell(Row row, int nextCol, Double value,
			CellStyle cellStyle) {
		Cell cell = row.createCell(nextCol);
		cell.setCellValue(value);
		cell.setCellStyle(cellStyle);
		return cell;
	}

	/**
	 * Method that create a Formula Cell
	 * 
	 * @param row
	 * @param nextCol
	 * @param formula
	 * @return
	 */
	public static Cell createCellFormula(Row row, int nextCol, String formula) {
		LOG.debug("nextCol: {}, formula: {}", nextCol, formula);
		Cell cell = row.createCell(nextCol);
		cell.setCellFormula(formula);
		return cell;
	}

	/**
	 * Method that create a Integer Cell
	 * 
	 * @param row
	 * @param nextCol
	 * @param value
	 * @param cellStyle
	 * @return
	 */
	public static Cell createCell(Row row, int nextCol, Integer value,
			CellStyle cellStyle) {

		Cell cell = row.createCell(nextCol);
		cell.setCellValue(value);
		cell.setCellStyle(cellStyle);
		return cell;
	}

	/**
	 * Method that create a Date Cell
	 * 
	 * @param row
	 * @param nextCol
	 * @param value
	 * @param wb
	 * @param dateFormatPatter
	 * @param cellStyle
	 * @return
	 */
	public static Cell createDateCell(Row row, int nextCol, Date value,
			Workbook wb, String dateFormatPatter, CellStyle cellStyle) {

		CreationHelper createHelper = wb.getCreationHelper();
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(
				dateFormatPatter));
		Cell cell = row.createCell(nextCol);
		cell.setCellValue(value);
		cell.setCellStyle(cellStyle);
		return cell;
	}

	public static <T> T getCellValue(Cell cell, Class<T> type) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
		case Cell.CELL_TYPE_ERROR:
		case Cell.CELL_TYPE_FORMULA:
			return type.cast(cell.toString());
		case Cell.CELL_TYPE_BOOLEAN:
			return type.cast(cell.getBooleanCellValue());
		case Cell.CELL_TYPE_NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				return type.cast(cell.getDateCellValue());
			} else {
				if (type.equals(Integer.class)) {
					return type.cast(Integer.valueOf((int) cell
							.getNumericCellValue()));
				} else {
					return type.cast(cell.getNumericCellValue());
				}
			}
		case Cell.CELL_TYPE_STRING:
			return type.cast(cell.getStringCellValue());
		default:
			throw new IllegalArgumentException(
					"The Type of Cell Does not exist");
		}
	}

	public static void addJFreeChart(Workbook workbook, JFreeChart chart)
			throws IOException {
		int width = 5000; /* Width of the chart */
		int height = 480; /* Height of the chart */
		float quality = 1; /* Quality factor */

		ByteArrayOutputStream charOut = new ByteArrayOutputStream();
		ChartUtilities.writeChartAsJPEG(charOut, quality, chart, width, height);
		int pictureId = workbook.addPicture(charOut.toByteArray(),
				Workbook.PICTURE_TYPE_JPEG);
		charOut.close();
		/* Create the drawing container */
		XSSFSheet sheet = ((XSSFWorkbook) workbook).getSheetAt(0);
		XSSFDrawing drawing = sheet.createDrawingPatriarch();
		/* Create an anchor point */
		ClientAnchor anchor = new XSSFClientAnchor();
		/* Define top left corner, and we can resize picture suitable from there */
		anchor.setCol1(4);
		anchor.setRow1(5);
		/* Invoke createPicture and pass the anchor point and ID */
		XSSFPicture picture = drawing.createPicture(anchor, pictureId);
		/* Call resize method, which resizes the image */
		picture.resize();
	}
}
