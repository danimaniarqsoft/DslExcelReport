package com.danimaniarqsoft.report.poi.dsl;

import org.apache.poi.ss.usermodel.Sheet;

import com.danimaniarqsoft.report.poi.PoiUtil;

public class WorkbookState {

  private WorkbookContext context;

  public WorkbookState(WorkbookContext context) {
    this.context = context;
  }

  public SheetState createSheet(String sheetName) {
    Sheet sheet = PoiUtil.createSheet(context.getWorkbook(), sheetName);
    context.setCurrentSheet(sheet);
    return new SheetState(context);
  }
}
