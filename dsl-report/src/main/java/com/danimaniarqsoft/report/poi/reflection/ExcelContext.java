package com.danimaniarqsoft.report.poi.reflection;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;

public class ExcelContext {

  private Map<String, CellStyle>   cellStyles;
  private List<ExcelColumnContext> columnContextList;

  public Map<String, CellStyle> getCellStyles() {
    return cellStyles;
  }

  public void setCellStyles(Map<String, CellStyle> cellStyles) {
    this.cellStyles = cellStyles;
  }

  public List<ExcelColumnContext> getColumnContextList() {
    return columnContextList;
  }

  public void setColumnContextList(List<ExcelColumnContext> columnContextList) {
    this.columnContextList = columnContextList;
  }

}
