package org.autotesting.util;

import java.io.InputStream;

public class UserInput {
    private final InputStream stream;

    public UserInput(InputStream stream) {
        this.stream = stream;
    }

    public String getInput() {
        var buffer = new byte[32];
        try {
            var read = stream.read(buffer);
            return new String(buffer, 0, read - 1);
        } catch (Exception ignored) {
            return "--";
        }
    }
}
