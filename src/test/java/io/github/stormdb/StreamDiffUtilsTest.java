package io.github.stormdb;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StreamDiffUtilsTest {

    @Test
    public void diffStrings() throws IOException {
        BufferedReader original = new BufferedReader(new StringReader("aaa\nbbb\nccc"));
        BufferedReader revised = new BufferedReader(new StringReader("aaa\nccc\nddd\neee"));

        Patch patch = new Patch() {

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

    @Test
    public void diffStreams() throws IOException, StreamException {
        BufferedReader original = new BufferedReader(new StringReader("aaa\nbbb\nccc"));
        BufferedReader revised = new BufferedReader(new StringReader("aaa\nccc\nddd\neee"));

        PatchOutputStream<String> patch = (position, type, delta) -> System.out.println("@" + position + " " + type + ": " + delta);

        StreamDiffUtils.diff(new SortedStringInputStream(original), new SortedStringInputStream(revised), patch);
    }

    @Test
    public void test1() {
        ArrayList<String> list = new ArrayList<>(List.of("a_9", "a_10", "a_8", "a_11"));
        Collections.sort(list);
        System.out.println(list);
    }
}