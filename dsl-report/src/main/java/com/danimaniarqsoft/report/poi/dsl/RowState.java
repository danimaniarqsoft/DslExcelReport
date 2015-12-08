package com.danimaniarqsoft.report.poi.dsl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.danimaniarqsoft.report.constants.MessageConstants;
import com.danimaniarqsoft.report.model.ExcelSheetParams;
import com.danimaniarqsoft.report.poi.reflection.ExcelColumnReflection;

public class RowState {

  private static final Logger LOG = LoggerFactory.getLogger(RowState.class);

  WorkbookContext             context;

  public RowState(WorkbookContext context) {
    this.context = context;
  }

  public CellState createRow() {
    return new CellState(context);
  }

  public RowState createRow(Map<String, Object> row) {
    CellState cellState = new CellState(context);
    for (Entry<String, Object> entry : row.entrySet()) {
      cellState.createCell(entry.getValue());
    }
    return this;
  }

  public RowState createRows(List<Map<String, Object>> rows) {
    for (Map<String, Object> row : rows) {
      createRow((Map<String, Object>) row);
    }
    return this;
  }

  public <T> RowState createRows(List<T> rows, Class<T> classWithAnnotation) {
    try {
      ExcelColumnReflection.createRowStateFromExcelAnnotation(context, rows, classWithAnnotation);
    } catch (Exception e) {
      LOG.error(MessageConstants.REFLECTION_ERROR, e);
    }
    return this;
  }

  public Workbook buildWorkbook() {
    return context.getWorkbook();
  }

  public Workbook buildWorkbookWithSumRow(int fromColumnNum) {
    CellState cellState = new CellState(context);
    ExcelSheetParams excelSheetParams = new ExcelSheetParams(fromColumnNum, context);
    mergeRowArea(cellState, excelSheetParams.getMaxNum(), 0, excelSheetParams.getOffset());
    ExcelArithmeticRowGenerator.createArithmeticRow(excelSheetParams, cellState);
    return context.getWorkbook();
  }

  public Workbook buildWorkbookWithAverageRow(int fromColumnNum) {
    CellState cellState = new CellState(context);
    ExcelSheetParams excelSheetParams = new ExcelSheetParams(fromColumnNum, context);
    mergeRowArea(cellState, excelSheetParams.getMaxNum(), 0, excelSheetParams.getOffset());
    ExcelArithmeticRowGenerator.createAverageRow(excelSheetParams, cellState);
    return context.getWorkbook();
  }

  private void mergeRowArea(CellState cellState, int rowNum, int fromCellNum, int toCellNum) {
    cellState.createCell("TOTAL");
    for (int i = fromCellNum + 1; i < toCellNum; i++) {
      cellState.createCell("");
    }
    context.getCurrentSheet()
        .addMergedRegion(new CellRangeAddress(rowNum, rowNum, fromCellNum, toCellNum - 1));
  }

  public Workbook buildWorkbookWithSumRow() {
    // Comenzamos en la segunda columna
    return buildWorkbookWithSumRow(1);
  }
}
