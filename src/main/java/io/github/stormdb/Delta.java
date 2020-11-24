package io.github.stormdb;

public class Delta<T> {

    private final DeltaType type;

    public Delta(DeltaType type) {
        this.type = type;
    }
}
