package org.autotesting;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.jboss.logging.Logger;

@QuarkusMain
public class Program implements QuarkusApplication {
    private static Logger log;

    public static void main(String[] args) {
        System.setProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager");
        log = Logger.getLogger(Program.class);
        Quarkus.run(Program.class, args);
    }

    @Override
    public int run(String... args) throws Exception {
        log.info("Starting application");
        Quarkus.waitForExit();
        log.info("Shutting down");
        return 0;
    }
}
