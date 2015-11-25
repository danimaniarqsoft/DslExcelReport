package com.danimaniarqsoft.report.constants;

import org.apache.poi.ss.usermodel.Font;

/**
 * Enum for Font format, It is Used for ExcelColumn Annotation and it represent
 * a Wrapper for org.apache.poi.ss.usermodel.Font Constant Class
 * 
 * @author Daniel Cortes Pichardo
 * 
 */
public enum BackGroundColor {
    BOLD(Font.BOLDWEIGHT_BOLD), NORMAL(Font.BOLDWEIGHT_NORMAL);
    private short keyCode;

    private BackGroundColor(short keyCode) {
        this.keyCode = keyCode;
    }

    public short getKeyCode() {
        return this.keyCode;
    }
}
