package org.autotesting.extensions;

import org.autotesting.database.tables.Currency;
import org.autotesting.model.Candle;

public class CandleExtension {
    private static final org.autotesting.database.tables.Candle tbl = org.autotesting.database.tables.Candle.CANDLE;

    private static final Currency cTbl = Currency.CURRENCY;

    public static Candle fromRecord(org.jooq.Record record) {
        var candle = new Candle();
        candle.id = record.get(tbl.CANDLE_ID);
        candle.currency = record.get(cTbl.TITLE);
        candle.open = record.get(tbl.OPEN_PRICE);
        candle.close = record.get(tbl.CLOSE_PRICE);
        candle.high = record.get(tbl.HIGH_PRICE);
        candle.low = record.get(tbl.LOW_PRICE);
        return candle;
    }
}
