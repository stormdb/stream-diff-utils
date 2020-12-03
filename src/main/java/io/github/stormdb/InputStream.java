package io.github.stormdb;

import java.io.Closeable;

public interface InputStream<T> extends Closeable {

    T read();
}
