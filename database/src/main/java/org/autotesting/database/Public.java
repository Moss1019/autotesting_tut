/*
 * This file is generated by jOOQ.
 */
package org.autotesting.database;


import java.util.Arrays;
import java.util.List;

import org.autotesting.database.tables.Account;
import org.autotesting.database.tables.Candle;
import org.autotesting.database.tables.Currency;
import org.autotesting.database.tables.Trade;
import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.account</code>.
     */
    public final Account ACCOUNT = Account.ACCOUNT;

    /**
     * The table <code>public.candle</code>.
     */
    public final Candle CANDLE = Candle.CANDLE;

    /**
     * The table <code>public.currency</code>.
     */
    public final Currency CURRENCY = Currency.CURRENCY;

    /**
     * The table <code>public.trade</code>.
     */
    public final Trade TRADE = Trade.TRADE;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Account.ACCOUNT,
            Candle.CANDLE,
            Currency.CURRENCY,
            Trade.TRADE
        );
    }
}