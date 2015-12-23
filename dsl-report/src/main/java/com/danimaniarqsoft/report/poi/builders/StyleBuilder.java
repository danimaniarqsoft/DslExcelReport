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

  private static final String ARIAL_FONT = "Arial";

  private StyleBuilder() {

  }

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
    font.setFontName(ARIAL_FONT);
    configFont(font, fontFormat);
    cellStyle.setFont(font);
  }

  public static void addTextPosition(CellStyle cellStyle, TextPosition[] textPosition) {
    for (TextPosition position : textPosition) {
      if (isCenterOrFillAlignment(position) || isGeneralOrLeftOrRightAlignment(position)) {
        cellStyle.setAlignment(position.getKeyCode());
      } else if (isBottomOrCenterVerticalAlignment(position)
          || isJustifyOrTopVerticalAlignment(position)) {
        cellStyle.setVerticalAlignment(position.getKeyCode());
      }
    }
  }

  private static boolean isCenterOrFillAlignment(TextPosition position) {
    return position.equals(TextPosition.ALIGN_CENTER)
        || position.equals(TextPosition.ALIGN_CENTER_SELECTION)
        || position.equals(TextPosition.ALIGN_FILL);
  }

  private static boolean isGeneralOrLeftOrRightAlignment(TextPosition position) {
    return position.equals(TextPosition.ALIGN_GENERAL) || position.equals(TextPosition.ALIGN_LEFT)
        || position.equals(TextPosition.ALIGN_RIGHT);
  }

  private static boolean isBottomOrCenterVerticalAlignment(TextPosition position) {
    return position.equals(TextPosition.VERTICAL_BOTTOM)
        || position.equals(TextPosition.VERTICAL_CENTER);
  }

  private static boolean isJustifyOrTopVerticalAlignment(TextPosition position) {
    return position.equals(TextPosition.VERTICAL_JUSTIFY)
        || position.equals(TextPosition.VERTICAL_TOP);
  }

  private static void configFont(Font font, FontFormat[] fontFormat) {
    if (fontFormat != null) {
      for (FontFormat format : fontFormat) {
        if (isBoldWeight(format)) {
          font.setBoldweight(format.getKeyCode());
        } else if (isItalic(format)) {
          font.setItalic(true);
        } else if (isStrikeout(format)) {
          font.setStrikeout(true);
        } else if (isColor(format)) {
          font.setColor(format.getKeyCode());
        } else if (isFont(format)) {
          font.setFontName(format.getFontName());
        }
      }
    }
  }

  private static boolean isBoldWeight(FontFormat fontFormat) {
    return fontFormat.equals(FontFormat.BOLD) || fontFormat.equals(FontFormat.NORMAL);
  }

  private static boolean isItalic(FontFormat fontFormat) {
    return fontFormat.equals(FontFormat.ITALIC);
  }

  private static boolean isStrikeout(FontFormat fontFormat) {
    return fontFormat.equals(FontFormat.STRIKEOUT);
  }

  private static boolean isColor(FontFormat fontFormat) {
    return isNormalOrRedColor(fontFormat) || isBlueOrBrownColor(fontFormat)
        || isOrangeOrPink(fontFormat) || isPurpleOrWhiteOrYellowColor(fontFormat);
  }

  private static boolean isNormalOrRedColor(FontFormat fontFormat) {
    return fontFormat.equals(FontFormat.COLOR_NORMAL) || fontFormat.equals(FontFormat.COLOR_RED);
  }

  private static boolean isBlueOrBrownColor(FontFormat fontFormat) {
    return fontFormat.equals(FontFormat.COLOR_BLUE) || fontFormat.equals(FontFormat.COLOR_BROWN);
  }

  private static boolean isOrangeOrPink(FontFormat fontFormat) {
    return fontFormat.equals(FontFormat.COLOR_ORANGE) || fontFormat.equals(FontFormat.COLOR_PINK);
  }

  private static boolean isPurpleOrWhiteOrYellowColor(FontFormat fontFormat) {
    return fontFormat.equals(FontFormat.COLOR_PURPLE) || fontFormat.equals(FontFormat.COLOR_WHITE)
        || fontFormat.equals(FontFormat.COLOR_YELLOW);
  }

  private static boolean isFont(FontFormat fontFormat) {
    return fontFormat.equals(FontFormat.ARIAL) || fontFormat.equals(FontFormat.CALIBRI)
        || fontFormat.equals(FontFormat.TIMES_NEW_ROMAN);
  }
}
