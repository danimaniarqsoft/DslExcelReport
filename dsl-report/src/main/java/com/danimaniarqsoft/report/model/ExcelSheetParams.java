package com.danimaniarqsoft.report.model;

import com.danimaniarqsoft.report.poi.dsl.WorkbookContext;

public class ExcelSheetParams {

    private int firstRowData;
    private int offset;
    private int minSum;
    private int maxNum;
    private int tablaAncho;

    public ExcelSheetParams(int fromColumnNum, WorkbookContext context){
        this.firstRowData = context.getFirstRowNumWithData();
        this.offset = fromColumnNum;
        this.minSum = this.firstRowData + 1;
        this.maxNum = context.getCurrentSheet().getLastRowNum();
        this.tablaAncho = context.getCurrentSheet().getRow(0).getLastCellNum();
    }
    public int getFirstRowData() {
        return firstRowData;
    }

    public void setFirstRowData(int firstRowData) {
        this.firstRowData = firstRowData;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getMinSum() {
        return minSum;
    }

    public void setMinSum(int minSum) {
        this.minSum = minSum;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public int getTablaAncho() {
        return tablaAncho;
    }

    public void setTablaAncho(int tablaAncho) {
        this.tablaAncho = tablaAncho;
    }

}
