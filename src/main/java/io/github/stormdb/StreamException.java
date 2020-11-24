package io.github.stormdb;

public class StreamException extends Exception {

    private static final long serialVersionUID = 1;

    public StreamException(String message) {
        super(message);
    }

    public StreamException(String message, Throwable cause) {
        super(message, cause);
    }
}
