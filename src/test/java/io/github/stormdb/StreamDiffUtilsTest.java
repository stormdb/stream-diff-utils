package io.github.stormdb;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

public class StreamDiffUtilsTest {

    @Test
    public void diff() throws IOException {
        BufferedReader original = new BufferedReader(new StringReader("aaa\nbbb\nccc"));
        BufferedReader revised = new BufferedReader(new StringReader("aaa\nccc\nddd\neee"));

        StreamPatch patch = new StreamPatch() {

            @Override
            public void addInsert(String line) throws IOException {
                System.out.println("+++ " + line);
            }

            @Override
            public void addDelete(String line) throws IOException {
                System.out.println("--- " + line);
            }
        };

        StreamDiffUtils.diff(original, revised, patch);

        original.close();
        revised.close();
    }
}