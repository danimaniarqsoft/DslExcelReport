package com.danimaniarqsoft.report.exceptions;

/**
 * Runtime Exception lanzada desde la aplicaciÃ³n del usuario
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class ApplicationException extends Exception {

  private static final long serialVersionUID = 1L;

  public ApplicationException() {
    super();
  }

  public ApplicationException(String s) {
    super(s);
  }

  public ApplicationException(String s, Throwable throwable) {
    super(s, throwable);
  }

  public ApplicationException(Throwable throwable) {
    super(throwable);
  }

}
