package fr.m2i.restexample.exceptions;

public class M2I500Exception extends M2IException {

	private static final long serialVersionUID = -2944221019260698225L;

	public M2I500Exception(final String message) {
		super(message);
	}

	public M2I500Exception(final String message, final Throwable throwable) {
		super(message, throwable);
	}
}
