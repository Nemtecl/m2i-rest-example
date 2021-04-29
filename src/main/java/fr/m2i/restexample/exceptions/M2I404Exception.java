package fr.m2i.restexample.exceptions;

import java.util.function.Supplier;

public class M2I404Exception extends M2IException  {

	private static final long serialVersionUID = -2944221019260698225L;

	public M2I404Exception(final String message) {
		super(message);
	}

	public M2I404Exception(final String message, final Throwable throwable) {
		super(message, throwable);
	}
}
