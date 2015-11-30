package com.danimaniarqsoft.report.poi.dsl;

import java.io.Serializable;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Clas utilizada dentro del DSL para construir un Workbook
 * 
 * @author Daniel Cortes Pichardo
 * 
 */
public class WorkbookContext implements Serializable {

  private static final long serialVersionUID = 7491634228097843743L;
  private Workbook          workbook;
  private Sheet             currentSheet;
  private Row               currentRow;
  private int               firstRowNumWithData;
  private int               nextRowNum;
  private int               nextColumnNum;

  public Workbook getWorkbook() {
    return workbook;
  }

  public void setWorkbook(Workbook workbook) {
    this.workbook = workbook;
  }

  public Sheet getCurrentSheet() {
    return currentSheet;
  }

  public void setCurrentSheet(Sheet currentSheet) {
    this.currentSheet = currentSheet;
  }

  public Row getCurrentRow() {
    return currentRow;
  }

  public void setCurrentRow(Row currentRow) {
    this.currentRow = currentRow;
  }

  public int getFirstRowNumWithData() {
    return firstRowNumWithData;
  }

  public void setFirstRowNumWithData(int firstRowNumWithData) {
    this.firstRowNumWithData = firstRowNumWithData;
  }

  public int getNextRowNum() {
    return nextRowNum++;
  }

  public void setNextRowNum(int nextRowNum) {
    this.nextRowNum = nextRowNum;
  }

  public int getNextColumnNum() {
    return nextColumnNum++;
  }

  public void setNextColumnNum(int nextColumnNum) {
    this.nextColumnNum = nextColumnNum;
  }

  public WorkbookContext(Workbook workbook) {
    this.workbook = workbook;
  }
}
