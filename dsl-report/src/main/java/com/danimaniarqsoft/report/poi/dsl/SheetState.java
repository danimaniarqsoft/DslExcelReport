package com.danimaniarqsoft.report.poi.dsl;

import java.util.Map;

import com.danimaniarqsoft.report.headers.SheetHeaderBuilder;
import com.danimaniarqsoft.report.headers.SheetHeaderDefaultBuilder;
import com.danimaniarqsoft.report.headers.SheetHeaderFromClassBuilder;

public class SheetState {
  private WorkbookContext context;

  public SheetState(WorkbookContext context) {
    this.context = context;
  }

  public RowState createHeader(Map<String, Object> headers) {
    return createHeader(headers, new SheetHeaderDefaultBuilder());
  }

  public <T> RowState createHeader(Class<T> headers) {
    return createHeader(headers, new SheetHeaderFromClassBuilder<T>());
  }

  public <T> RowState createHeader(T headers, SheetHeaderBuilder<T> headerBuilder) {
    int nextRowNum = headerBuilder.buildHeader(context.getCurrentSheet(), headers);
    context.setFirstRowNumWithData(nextRowNum);
    context.setNextRowNum(nextRowNum);
    return new RowState(context);
  }
}
