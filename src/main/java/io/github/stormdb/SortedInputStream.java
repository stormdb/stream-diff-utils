package io.github.stormdb;

import java.io.Closeable;

public abstract class SortedInputStream<T extends Comparable<T>> implements Closeable {

    private T previous = null;

    public T next() throws StreamException {
        T next = getNextElement();
        if (next != null && previous != null) {
            if (next.compareTo(previous) < 0) {
                throw new StreamException("Next stream element (" + next + ") is smaller than previous (" + previous + ")");
            }
        }
        previous = next;
        return next;
    }

    protected abstract T getNextElement() throws StreamException;
}
