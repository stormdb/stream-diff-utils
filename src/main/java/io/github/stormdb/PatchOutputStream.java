package io.github.stormdb;

public interface PatchOutputStream<T> {

    void addDelta(int position, DeltaType type, T delta);
}
