package com.danimaniarqsoft.report.headers;

import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * SheetHeaderDefaultBuilder
 * 
 * This Class is the Default Sheet Header template used to build The header of a Excell Sheet.
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class SheetHeaderDefaultBuilder implements SheetHeaderBuilder<Map<String, Object>> {

  /**
   * {@inheritDoc}
   */
  @Override
  public int buildHeader(Sheet sheet, Map<String, Object> headers) {
    int nextRow = 0;
    Row row = sheet.createRow(nextRow++);
    int cellCount = 0;
    for (Map.Entry<String, Object> header : headers.entrySet()) {
      Cell cell = row.createCell(cellCount++);
      cell.setCellValue(header.getKey());
    }
    return nextRow;
  }
}
