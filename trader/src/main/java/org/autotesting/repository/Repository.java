package org.autotesting.repository;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;

public abstract class Repository {
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

    protected static void init() {
        if(!isConnected) {
            try {
                ctx = DSL.using("jdbc:postgresql://localhost:5432/trader_db",
                        "postgres",
                        "secret123!");
                inError = false;
                isConnected = true;
            } catch (Exception ex) {
                inError = true;
                error = ex.getMessage();
                isConnected = false;
            }
        }
    }
}
