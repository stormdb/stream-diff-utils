package io.github.stormdb;

import java.io.BufferedReader;
import java.io.IOException;

public class SortedStringInputStream extends SortedInputStream<String> {

    private final BufferedReader reader;

    public SortedStringInputStream(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    protected String getNextElement() throws StreamException {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new StreamException("Failed to read next element: " + e, e);
        }
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
