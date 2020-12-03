package io.github.stormdb;

import java.io.Closeable;

public interface OutputStream<T> extends Closeable {

    void write(T t);
}
