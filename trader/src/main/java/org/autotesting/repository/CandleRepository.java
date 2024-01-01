package org.autotesting.repository;

import org.autotesting.database.tables.Currency;
import org.autotesting.extensions.CandleExtension;
import org.autotesting.model.Candle;

import java.util.List;

public class CandleRepository extends Repository {
    private static final org.autotesting.database.tables.Candle tbl = org.autotesting.database.tables.Candle.CANDLE;

    private static final Currency cTbl = Currency.CURRENCY;

    public CandleRepository() {
        init();
    }

    public List<Candle> get(int limit, int offset, String currency) {
        var select = ctx.select()
                .from(tbl)
                .join(cTbl)
                .on(cTbl.CURRENCY_ID.eq(tbl.CURRENCY_ID))
                .where(cTbl.TITLE.eq(currency))
                .offset(offset)
                .limit(limit);
        var candles = select.fetch().stream()
                .map(CandleExtension::fromRecord)
                .toList();
        return candles;
    }

    public boolean insert(Candle candle) {
        var currencyIdRes = ctx
                .select(cTbl.CURRENCY_ID)
                .from(cTbl)
                .where(cTbl.TITLE.eq(candle.currency))
                .fetchOne();
        if(currencyIdRes == null) {
            return false;
        }
        var insert = ctx
                .insertInto(tbl)
                .columns(tbl.CURRENCY_ID, tbl.OPEN_PRICE, tbl.CLOSE_PRICE, tbl.HIGH_PRICE, tbl.LOW_PRICE)
                .values(currencyIdRes.get(cTbl.CURRENCY_ID), candle.open, candle.close, candle.high, candle.low);
        return insert.execute() > 0;
    }
}
