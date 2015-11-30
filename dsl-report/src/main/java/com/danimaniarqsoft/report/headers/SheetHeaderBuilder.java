package com.danimaniarqsoft.report.headers;

import org.apache.poi.ss.usermodel.Sheet;

/**
 * SheetHederBuilder interface used for create The headers on Excel Documents. on a Class
 * 
 * @author Daniel Cortes Pichardo
 *
 * @param <T>
 */
public interface SheetHeaderBuilder<T> {
  /**
   * Create the headers columns on Excel documents. It is Used by DSL engine
   * <p>
   * This interface can be implemented for create custom builders for the headers of Excel
   * Documents.
   * 
   * @param sheet the current sheet of the workbook.
   * @param headersSource a generic datasource for create the headers
   * @return int the next row from which the DSL engine going to create the nexts rows.
   */
  public int buildHeader(Sheet sheet, T headersSource);

}
