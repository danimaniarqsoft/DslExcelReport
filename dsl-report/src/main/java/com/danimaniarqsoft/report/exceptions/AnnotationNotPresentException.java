package com.danimaniarqsoft.report.exceptions;

/**
 * AnnotationNotPresentException es throw by a service that parse some kind of
 * annotation and is mandatory that class has one or more exceptions.
 * 
 * @author Daniel Cortes Pichardo
 * 
 */
public class AnnotationNotPresentException extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;

	public AnnotationNotPresentException(String message) {
		super(message);
	}
}
