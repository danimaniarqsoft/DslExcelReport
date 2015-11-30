package com.danimaniarqsoft.report.exceptions;

/**
 * MethodNotPresentException is thrown by a service that parse some kind of annotation and is
 * mandatory that class has one or more exceptions.
 * 
 * @author Daniel Cortes Pichardo
 * 
 */
public class FieldNotExistException extends IllegalArgumentException {
  private static final long serialVersionUID = 1L;

  public FieldNotExistException(String message, Throwable cause) {
    super(message, cause);
  }
}
