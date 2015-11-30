package com.danimaniarqsoft.report.poi.builders;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

import com.danimaniarqsoft.report.constants.FontFormat;
import com.danimaniarqsoft.report.constants.TextPosition;
import com.danimaniarqsoft.report.poi.reflection.ExcelColumnContext;
import com.danimaniarqsoft.report.poi.reflection.ExcelContext;

/**
 * Builder for create Styles
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class StyleBuilder {

  private StyleBuilder() {}

  public static Map<String, CellStyle> createCellStyles(ExcelContext context, Workbook workbook) {
    Map<String, CellStyle> cellStyles = new HashMap<String, CellStyle>();
    for (ExcelColumnContext colContext : context.getColumnContextList()) {
      CellStyle cellStyle = createCellStyle(colContext, workbook);
      cellStyles.put(colContext.getPropertyName(), cellStyle);
    }
    return cellStyles;
  }

  private static CellStyle createCellStyle(ExcelColumnContext context, Workbook workbook) {
    CellStyle cellStyle = workbook.createCellStyle();
    Font font = workbook.createFont();
    TextPosition[] textPosition = context.getCellFormatContext().getTextPosition();
    FontFormat[] fontFormat = context.getCellFormatContext().getFontFormat();
    addTextPosition(cellStyle, textPosition);
    addFontFormat(cellStyle, font, fontFormat);
    return cellStyle;

  }

  public static void addFontFormat(CellStyle cellStyle, Font font, FontFormat[] fontFormat) {
    font.setFontHeightInPoints((short) 10);
    font.setFontName("Arial");
    configFont(font, fontFormat);
    cellStyle.setFont(font);
  }

  private static void configFont(Font font, FontFormat[] fontFormat) {
    if (fontFormat != null) {
      for (FontFormat format : fontFormat) {
        switch (format) {
          case BOLD:
          case NORMAL:
            font.setBoldweight(format.getKeyCode());
            break;
          case ITALIC:
            font.setItalic(true);
            break;
          case STRIKEOUT:
            font.setStrikeout(true);
            break;
          case COLOR_NORMAL:
          case COLOR_RED:
          case COLOR_BLUE:
          case COLOR_BROWN:
          case COLOR_GRAY:
          case COLOR_GREEN:
          case COLOR_ORANGE:
          case COLOR_PINK:
          case COLOR_PURPLE:
          case COLOR_WHITE:
          case COLOR_YELLOW:
            font.setColor(format.getKeyCode());
            break;
          case ARIAL:
          case CALIBRI:
          case TIMES_NEW_ROMAN:
            font.setFontName(format.getFontName());
            break;
          default:
            break;
        }
      }
    }
  }

  public static void addTextPosition(CellStyle cellStyle, TextPosition[] textPosition) {
    for (TextPosition position : textPosition) {
      switch (position) {
        case ALIGN_CENTER:
        case ALIGN_CENTER_SELECTION:
        case ALIGN_FILL:
        case ALIGN_GENERAL:
        case ALIGN_LEFT:
        case ALIGN_RIGHT:
          cellStyle.setAlignment(position.getKeyCode());
          break;
        case VERTICAL_BOTTOM:
        case VERTICAL_CENTER:
        case VERTICAL_JUSTIFY:
        case VERTICAL_TOP:
          cellStyle.setVerticalAlignment(position.getKeyCode());
          break;
        default:
          break;
      }
    }
  }
}
