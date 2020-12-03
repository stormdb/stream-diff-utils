package io.github.stormdb;

import java.util.Objects;

public class Delta<T> {

    private final int position;
    private final DeltaType type;
    private final T delta;

    public Delta(int position, DeltaType type, T delta) {
        this.position = position;
        this.type = type;
        this.delta = delta;
    }

    public int getPosition() {
        return position;
    }

    public DeltaType getType() {
        return type;
    }

    public T getDelta() {
        return delta;
    }

    @Override
    public String toString() {
        return "Delta{" +
                "position=" + position +
                ", type=" + type +
                ", delta=" + delta +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delta<?> delta1 = (Delta<?>) o;
        return position == delta1.position && type == delta1.type && delta.equals(delta1.delta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, type, delta);
    }
}
