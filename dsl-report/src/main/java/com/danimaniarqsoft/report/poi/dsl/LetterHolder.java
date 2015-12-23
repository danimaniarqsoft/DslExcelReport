package com.danimaniarqsoft.report.poi.dsl;

public class LetterHolder {

  // ASCII val for the character "["
  public static final int ASCII_CAPITAL_A = 'A';
  public static final int ASCII_CAPITAL_Z = 'Z';
  private StringBuilder   columnConcatenated;
  private char            currentChar;
  private char            countChar;

  public LetterHolder(char initialChar) {
    if (initialChar < ASCII_CAPITAL_A || initialChar > ASCII_CAPITAL_Z) {
      throw new IllegalArgumentException(
          "The initial Char must be between \"A\" and \"B\" Letters");
    }
    columnConcatenated = new StringBuilder();
    currentChar = initialChar;
    countChar = ASCII_CAPITAL_A;
  }

  public String nextLetterColumn() {
    if (currentChar > ASCII_CAPITAL_Z) {
      currentChar = ASCII_CAPITAL_A;
      if (countChar > ASCII_CAPITAL_Z) {
        countChar = ASCII_CAPITAL_A;
      }
      columnConcatenated.append(countChar++);
    }
    return columnConcatenated.toString() + currentChar++;
  }

  public static String compute(StringBuilder sb) {
    char lastChar = returnSaveChar(sb);
    if (lastChar + 1 > 90) {
      return compute(returnSaveValue(sb)) + "A";
    } else {
      sb.replace(returnSaveIndex(sb), sb.length(), String.valueOf((char) (lastChar + 1)));
      return sb.toString();
    }
  }

  public static char returnSaveChar(StringBuilder sb) {
    if ((sb.length() - 1) == -1) {
      return '@';
    } else {
      return sb.charAt(sb.length() - 1);
    }
  }

  public static int returnSaveIndex(StringBuilder sb) {
    if ((sb.length() - 1) == -1) {
      return 0;
    } else {
      return sb.length() - 1;
    }
  }

  public static String returnSaveValue(StringBuilder sb) {
    if ((sb.length() - 1) == -1) {
      return "A";
    } else {
      return sb.substring(0, sb.length() - 1);
    }
  }

  public static String compute(String cad) {
    StringBuilder sb = new StringBuilder(cad);
    return compute(sb);
  }
}
