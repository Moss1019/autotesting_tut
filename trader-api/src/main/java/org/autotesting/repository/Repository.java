package org.autotesting.repository;

import org.jboss.logging.Logger;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

public abstract class Repository {
    private static final Logger log = Logger.getLogger(Repository.class);

    protected static DSLContext ctx;

    protected static boolean isConnected;

    private static boolean inError;

    private static String error;

    static {
        isConnected = false;
        init();
    }

    public static boolean isInError() {
        return inError;
    }

    public static String getError() {
        return error;
    }

    public static void init() {
        log.debug(String.format("Trying to open connection, connection is open: %b, in error: %b, error msg %s",
                isConnected,
                inError,
                error));
        if(!isConnected || ctx == null) {
            try {
                ctx = DSL.using("jdbc:postgresql://localhost:5432/trader_db",
                        "postgres",
                        "secret123!");
                inError = false;
                isConnected = true;
                log.info("DB initialized");
            } catch (Exception ex) {
                inError = true;
                error = ex.getMessage();
                isConnected = false;
                log.error(ex.getMessage(), ex);
            }
        }
    }
}
