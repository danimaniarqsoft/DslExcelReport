package com.danimaniarqsoft.report.poi.dsl;

import com.danimaniarqsoft.report.poi.PoiUtil;

public class WorkbookBuilder {

  private WorkbookBuilder() {

  }

  public static WorkbookState createWorkbook(WorkbookEnum option) {
    return new WorkbookState(new WorkbookContext(PoiUtil.createWorkbook(option)));
  }
}
