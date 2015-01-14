package com.danimaniarqsoft.report.exceptions;

/**
 * DslException es throw by a service that parse some kind of annotation and is
 * mandatory that class has one or more exceptions.
 * 
 * @author Daniel Cortes Pichardo
 * 
 */
public class DslException extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;

	public DslException(String message) {
		super(message);
	}

	public DslException(String message, Throwable cause) {
		super(message, cause);
	}
}
