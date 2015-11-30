package com.danimaniarqsoft.report.exceptions;

/**
 * MethodNotPresentException is thrown by a service that parse some kind of annotation and is
 * mandatory that class has one or more exceptions.
 * 
 * @author Daniel Cortes Pichardo
 * 
 */
public class MethodNotPresentException extends IllegalArgumentException {
  private static final long serialVersionUID = 1L;

  public MethodNotPresentException(String message, Throwable cause) {
    super(message, cause);
  }

}
