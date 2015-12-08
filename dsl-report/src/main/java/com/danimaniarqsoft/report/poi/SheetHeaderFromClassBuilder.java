package com.danimaniarqsoft.report.poi;

import java.lang.reflect.Field;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.danimaniarqsoft.report.annotations.ExcelColumn;

/**
 * SheetHeder Builder for create The headers on Excel Documents based on a Class
 * 
 * @author Daniel Cortes Pichardo
 *
 * @param <T>
 */
public class SheetHeaderFromClassBuilder<T> implements SheetHeaderBuilder<Class<T>> {

  @Override
  public int buildHeader(Sheet sheet, Class<T> headers) {
    Field[] fields = headers.getDeclaredFields();
    int nextRow = 0;
    int cellCount = 0;
    Row row = sheet.createRow(nextRow++);
    for (Field field : fields) {
      if (field.isAnnotationPresent(ExcelColumn.class)) {
        ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
        Cell cell = row.createCell(cellCount++);
        cell.setCellValue(annotation.name());
        cell.setCellStyle(addDefaultCellStyle(sheet));
      }
    }
    return nextRow;
  }

  private static CellStyle addDefaultCellStyle(Sheet sheet) {
    CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
    cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
    cellStyle.setVerticalAlignment(CellStyle.VERTICAL_JUSTIFY);
    cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
    cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
    return cellStyle;
  }
}
