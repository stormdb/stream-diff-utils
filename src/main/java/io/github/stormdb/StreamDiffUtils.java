package io.github.stormdb;

import java.io.BufferedReader;
import java.io.IOException;

public final class StreamDiffUtils {

    public static void diff(BufferedReader original, BufferedReader revised, StreamPatch patch) throws IOException {
        BufferedReader br1 = original;
        BufferedReader br2 = revised;
        String line1 = br1.readLine();
        String line2 = br2.readLine();

        while (line1 != null && line2 != null) {
            int result = line1.compareTo(line2);
            if (result == 0) {
                line1 = br1.readLine();
                line2 = br2.readLine();
            } else if (result < 0) {
                patch.addInsert(line1);
                line1 = br1.readLine();
            } else {
                patch.addDelete(line2);
                line2 = br2.readLine();
            }
        }

        while (line1 != null) {
            patch.addInsert(line1);
            line1 = br1.readLine();
        }

        while (line2 != null) {
            patch.addDelete(line2);
            line2 = br2.readLine();
        }
    }

    private StreamDiffUtils() {
    }
}
