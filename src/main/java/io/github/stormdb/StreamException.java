package io.github.stormdb;

public class StreamException extends Exception {

    public StreamException(String message) {
        super(message);
    }

    public StreamException(String message, Throwable cause) {
        super(message, cause);
    }
}
