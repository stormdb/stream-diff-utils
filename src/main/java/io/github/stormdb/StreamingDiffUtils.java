package io.github.stormdb;

import java.io.BufferedReader;
import java.io.IOException;

public final class StreamingDiffUtils {

    public static <T extends Comparable<T>> void diff(InputStream<T> original, InputStream<T> revised, OutputStream<Delta<T>> patch) throws StreamException {
        int position = 0;
        T line1 = original.read();
        position++;
        T line2 = revised.read();

        while (line1 != null && line2 != null) {
            int result = line1.compareTo(line2);
            if (result == 0) {
                line1 = original.read();
                position++;
                line2 = revised.read();
            } else if (result < 0) {
                patch.write(new Delta<T>(position, DeltaType.DELETE, line1));
                line1 = original.read();
                position++;
            } else {
                patch.write(new Delta<T>(position, DeltaType.INSERT, line2));
                line2 = revised.read();
            }
        }

        while (line1 != null) {
            patch.write(new Delta<T>(position, DeltaType.DELETE, line1));
            line1 = original.read();
            position++;
        }

        while (line2 != null) {
            patch.write(new Delta<T>(position, DeltaType.INSERT, line2));
            line2 = revised.read();
        }
    }

    private StreamingDiffUtils() {
    }
}
