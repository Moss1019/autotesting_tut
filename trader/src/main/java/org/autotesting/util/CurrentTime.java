package org.autotesting.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class CurrentTime {
    public LocalDateTime get() {
        return LocalDateTime.now(ZoneOffset.UTC);
    }
}
