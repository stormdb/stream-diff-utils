package io.github.stormdb;

import java.io.BufferedReader;
import java.io.IOException;

public class StringInputStream implements InputStream<String> {

    private final BufferedReader reader;

    public StringInputStream(BufferedReader reader) {
        this.reader = reader;
    }

    public String read() throws StreamException {
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
