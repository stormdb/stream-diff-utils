package io.github.stormdb;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class StreamingDiffUtilsTest {

    @Test
    public void diffStreams() {
        BufferedReader original = new BufferedReader(new StringReader("aaa\nbbb\nccc\nfff"));
        BufferedReader revised = new BufferedReader(new StringReader("aaa\nccc\nddd\neee\nfff"));

        List<Delta<String>> deltas = new ArrayList<>();
        OutputStream<Delta<String>> patch = new OutputStream<Delta<String>>() {

            @Override
            public void write(Delta<String> d) {
                System.out.println("@" + d.getPosition() + " " + d.getType() + ": " + d.getDelta());
                deltas.add(d);
            }

            @Override
            public void close() {
            }
        };

        StreamingDiffUtils.diff(new StringInputStream(original), new StringInputStream(revised), patch);

        List<Delta<String>> expected = Arrays.asList(
                new Delta<>(2, DeltaType.DELETE, "bbb"),
                new Delta<>(4, DeltaType.INSERT, "ddd"),
                new Delta<>(4, DeltaType.INSERT, "eee")
        );

        assertThat(deltas, is(equalTo(expected)));
    }

}