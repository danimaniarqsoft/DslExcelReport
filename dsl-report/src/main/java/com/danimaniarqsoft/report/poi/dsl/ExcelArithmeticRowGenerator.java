package com.danimaniarqsoft.report.poi.dsl;

import com.danimaniarqsoft.report.model.ExcelSheetParams;

public class ExcelArithmeticRowGenerator {

  // ASCII val for the character "["
  public static final char ASCII_CAPITAL_A             = 'A';
  public static final char ASCII_CAPITAL_Z             = 'Z';
  public static final char ASCII_CHAR_BEFORE_CAPITAL_A = '@';

  public ExcelArithmeticRowGenerator() {

  }

  public static void createArithmeticRow(ExcelSheetParams params, CellState cellState) {
    char[] cellLetter = new char[] {(char) (LetterHolder.ASCII_CAPITAL_A + params.getOffset())};
    StringBuilder letterHolder = new StringBuilder();
    for (int i = params.getOffset(); i < params.getTablaAncho(); i++) {
      letterHolder.setLength(0);
      letterHolder.append(cellLetter);
      cellState
          .createCellFormula(buildSumFormula(letterHolder, params.getMinSum(), params.getMaxNum()));
      cellLetter = ExcelArithmeticRowGenerator.calculateNextSequence(cellLetter);
    }
  }

  public static void createAverageRow(ExcelSheetParams params, CellState cellState) {

    char[] cellLetter = new char[] {(char) (LetterHolder.ASCII_CAPITAL_A + params.getOffset())};
    StringBuilder letterHolder = new StringBuilder();
    for (int i = params.getOffset(); i < params.getTablaAncho(); i++) {
      letterHolder.setLength(0);
      letterHolder.append(cellLetter);
      cellState.createCellFormula(
          buildAverageFormula(letterHolder, params.getMinSum(), params.getMaxNum()));
      cellLetter = ExcelArithmeticRowGenerator.calculateNextSequence(cellLetter);
    }
  }

  private static String buildAverageFormula(StringBuilder sb, int minSum, int maxSum) {
    StringBuilder formulaBuilder = new StringBuilder();
    formulaBuilder.append("SUM(");
    formulaBuilder.append(sb.toString()).append(minSum).append(":");
    formulaBuilder.append(sb.toString()).append(maxSum).append(")");
    formulaBuilder.append("/").append((maxSum + 1) - minSum);
    return formulaBuilder.toString();
  }

  private static String buildSumFormula(StringBuilder sb, int minSum, int maxSum) {
    StringBuilder formulaBuilder = new StringBuilder();
    formulaBuilder.append("SUM(");
    formulaBuilder.append(sb.toString()).append(minSum).append(":");
    formulaBuilder.append(sb.toString()).append(maxSum).append(")");
    return formulaBuilder.toString();
  }

  private static char[] calculateNextSequence(char[] sb) {
    char lastChar = returnLastChar(sb);
    if (lastChar + 1 > ASCII_CAPITAL_Z) {
      return addChar(calculateNextSequence(returnSaveValue(sb)), ASCII_CAPITAL_A);
    } else {
      replaceChartAt(sb, (char) (lastChar + 1), returnSaveIndex(sb));
      return sb;
    }
  }

  private static char returnLastChar(char[] sb) {
    if ((sb.length == 0)) {
      return ASCII_CHAR_BEFORE_CAPITAL_A;
    } else {
      return sb[sb.length - 1];
    }
  }

  private static int returnSaveIndex(char[] sb) {
    if ((sb.length == 0)) {
      return 0;
    } else {
      return sb.length - 1;
    }
  }

  private static char[] returnSaveValue(char[] sb) {
    if ((sb.length == 1)) {
      return new char[] {ASCII_CHAR_BEFORE_CAPITAL_A};
    } else {
      return charSubSet(sb, 0, sb.length - 1);
    }
  }

  private static char[] addChar(char[] baseSequence, char newChar) {
    char[] newCharSequence = new char[] {newChar};
    char[] mergeSequence = new char[baseSequence.length + newCharSequence.length];
    System.arraycopy(baseSequence, 0, mergeSequence, 0, baseSequence.length);
    System.arraycopy(newCharSequence, 0, mergeSequence, baseSequence.length,
        newCharSequence.length);
    return mergeSequence;
  }

  private static char[] charSubSet(char[] sequence, int from, int to) {
    char[] subSet = new char[to - from];
    System.arraycopy(sequence, from, subSet, 0, to);
    return subSet;
  }

  private static void replaceChartAt(char[] sequence, char newChar, int poss) {
    sequence[poss] = newChar;
  }

  public static void main(String[] args) {
    char[] cad = new char[] {ASCII_CAPITAL_A};
    for (int i = 0; i < 1999; i++) {
      cad = calculateNextSequence(cad);
      System.out.println(cad);
    }
  }
}
