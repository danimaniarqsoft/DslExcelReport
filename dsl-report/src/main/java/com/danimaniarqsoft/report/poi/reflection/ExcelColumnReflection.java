package com.danimaniarqsoft.report.poi.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

import com.danimaniarqsoft.report.annotations.ExcelColumn;
import com.danimaniarqsoft.report.exceptions.ApplicationException;
import com.danimaniarqsoft.report.model.CellFormatContext;
import com.danimaniarqsoft.report.poi.builders.StyleBuilder;
import com.danimaniarqsoft.report.poi.dsl.CellState;
import com.danimaniarqsoft.report.poi.dsl.WorkbookContext;
import com.danimaniarqsoft.report.util.ReflectionUtil;

/**
 * Class used for read The ExcelAnnotation from a Class.
 * <p>
 * It Class is used to create excel Rows on the class RowState
 * 
 * @author Daniel Cortes Pichardo
 * 
 */
public class ExcelColumnReflection {

  private ExcelColumnReflection() {

  }

  /**
   * Create a rows based on ExcelAnnotation class to create Excel rows.
   * 
   * @param wbContext The workbookContext
   * @param rows
   * @param classWithAnnotation
   * @throws NoSuchMethodException
   * @throws SecurityException
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws InvocationTargetException
   */
  public static <T> void createRowStateFromExcelAnnotation(WorkbookContext wbContext, List<T> rows,
      Class<T> classWithAnnotation) throws ApplicationException {

    ExcelContext excelContext = readExcelColumnAnnotations(classWithAnnotation);
    createStylesForCells(excelContext, wbContext.getWorkbook());
    createRows(wbContext, rows, excelContext);
  }

  private static void createStylesForCells(ExcelContext excelContext, Workbook workbook) {
    Map<String, CellStyle> cellSytles = StyleBuilder.createCellStyles(excelContext, workbook);
    excelContext.setCellStyles(cellSytles);
  }

  private static <T> void createRows(WorkbookContext wbContext, List<T> rows,
      ExcelContext excelContext) throws ApplicationException {
    for (T classWithAnnotation : rows) {
      createCells(wbContext, excelContext, classWithAnnotation);
    }
  }

  private static <T> void createCells(WorkbookContext wbContext, ExcelContext excelContext,
      T classWithAnnotation) throws ApplicationException {
    CellState cellState = new CellState(wbContext);
    Map<String, CellStyle> stylesMap = excelContext.getCellStyles();
    for (ExcelColumnContext context : excelContext.getColumnContextList()) {
      Object val;
      try {
        val = context.getMethod().invoke(classWithAnnotation, new Object[] {});
        createCell(cellState, context, val, stylesMap.get(context.getPropertyName()));
      } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
        throw new ApplicationException("CreateCell Error", e);
      }
    }
  }

  private static void createCell(CellState cellState, ExcelColumnContext context, Object val,
      CellStyle cellStyle) {
    switch (context.getCellFormatContext().getCellType()) {
      case DATE:
        cellState.createDateCell((Date) val, context.getCellFormatContext().getDateFormat(),
            cellStyle);
        break;
      case BLANK:
      case BOOLEAN:
      case ERROR:
      case FORMULA:
      case STRING:
      case NUMERIC:
      default:
        cellState.createCell(val, cellStyle);
        break;
    }
  }

  public static <T> ExcelContext readExcelColumnAnnotations(Class<T> clazz)
      throws ApplicationException {
    ExcelContext context = new ExcelContext();
    List<ExcelColumnContext> excelColumnAnnotationContextList = new ArrayList<ExcelColumnContext>();
    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      if (field.isAnnotationPresent(ExcelColumn.class)) {
        excelColumnAnnotationContextList.add(readAnnotationProperties(field, clazz));
      }
    }
    context.setColumnContextList(excelColumnAnnotationContextList);
    return context;
  }

  private static <T> ExcelColumnContext readAnnotationProperties(Field field, Class<T> clazz)
      throws ApplicationException {

    ExcelColumnContext columnContext = new ExcelColumnContext();
    ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
    columnContext.setPropertyType(field.getType());
    readExcelColumnContext(columnContext, annotation);
    readPropertyAndMethodForSettingExcelValues(field, clazz, columnContext);
    return columnContext;
  }

  private static <T> void readPropertyAndMethodForSettingExcelValues(Field field, Class<T> clazz,
      ExcelColumnContext context) throws ApplicationException {
    context.setPropertyName(field.getName());
    String sMethod = ReflectionUtil.toGetterFormat(field.getName());
    try {
      Method method = clazz.getMethod(sMethod, new Class[] {});
      context.setMethod(method);
    } catch (NoSuchMethodException | SecurityException e) {
      throw new ApplicationException("readPropertyException", e);
    }
  }

  private static void readExcelColumnContext(ExcelColumnContext columnContext,
      ExcelColumn annotation) {
    readColumnHeaderProperties(columnContext, annotation);
    readCellProperties(columnContext.getCellFormatContext(), annotation);
  }

  private static void readColumnHeaderProperties(ExcelColumnContext context,
      ExcelColumn annotation) {
    context.setColumnName(annotation.name());
  }

  private static void readCellProperties(CellFormatContext context, ExcelColumn annotation) {
    context.setCellType(annotation.type());
    context.setDateFormat(annotation.dateFormat());
    context.setTextPosition(annotation.textPosition());
    context.setFontFormat(annotation.fontFormat());
  }

}
