package com.danimaniarqsoft.report.poi.builders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.danimaniarqsoft.report.constants.MessageConstants;
import com.danimaniarqsoft.report.poi.PoiUtil;
import com.danimaniarqsoft.report.poi.reflection.ExcelColumnContext;
import com.danimaniarqsoft.report.poi.reflection.ExcelColumnReflection;
import com.danimaniarqsoft.report.poi.reflection.ExcelContext;
import com.danimaniarqsoft.report.util.ReflectionUtil;

/**
 * Model Object Builder is used to create Objects from differents ways.
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class ModelObjectBuilder {

  private static final Logger LOG = LoggerFactory.getLogger(ModelObjectBuilder.class);

  private ModelObjectBuilder() {

  }

  public static <T extends Object> List<T> createModelObject(Workbook workbook,
      Class<T> modelObjectTarget) throws NoSuchMethodException {
    Sheet sheet = workbook.getSheetAt(0);
    ExcelContext excelContext = ExcelColumnReflection.readExcelColumnAnnotations(modelObjectTarget);
    return processSheet(sheet, excelContext, modelObjectTarget);
  }

  private static <T> List<T> processSheet(Sheet sheet, ExcelContext excelContext,
      Class<T> modelObjectTarget) {
    Iterator<Row> rowIterator = sheet.iterator();
    rowIterator.next();
    return processRows(rowIterator, excelContext, modelObjectTarget);
  }

  private static <T> List<T> processRows(Iterator<Row> rowIterator, ExcelContext excelContext,
      Class<T> modelObjectTarget) {
    List<T> newList = new ArrayList<T>();
    while (rowIterator.hasNext()) {

      Row row = rowIterator.next();
      Iterator<Cell> cellIterator = row.iterator();
      T newObject = processCells(cellIterator, excelContext, modelObjectTarget);
      newList.add(newObject);
    }

    return newList;
  }

  private static <T> T processCells(Iterator<Cell> cellIterator, ExcelContext excelContext,
      Class<T> modelObjectTarget) {
    T newObject = ReflectionUtil.createNewInstance(modelObjectTarget);

    List<ExcelColumnContext> columns = excelContext.getColumnContextList();

    int columnIndex = 0;
    while (cellIterator.hasNext()) {
      Cell cell = cellIterator.next();
      try {
        ExcelColumnContext columnContext = columns.get(columnIndex);
        Object value = PoiUtil.getCellValue(cell, columnContext.getPropertyType());
        ReflectionUtil.setFieldValue(newObject, columnContext.getPropertyName(), value);
      } catch (Exception e) {
        LOG.error(MessageConstants.CELL_ITERATOR_ERROR, e);
      }
      columnIndex++;
    }
    return newObject;
  }
}
