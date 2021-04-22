package fr.m2i.restexample.exceptions;

public abstract class M2IException extends Exception {
    private static final long serialVersionUID = 1L;

    protected M2IException(final String message) {
        super(message);
    }

    protected M2IException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
