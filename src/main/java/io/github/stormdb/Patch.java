package io.github.stormdb;

import java.io.IOException;

public interface Patch {

    void addInsert(String line) throws IOException;

    void addDelete(String line) throws IOException;
}
