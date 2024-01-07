package org.autotesting.repository;

import jakarta.enterprise.context.ApplicationScoped;
import org.autotesting.database.tables.Currency;
import org.autotesting.exception.ConnectionLostException;
import org.autotesting.extensions.CandleExtension;
import org.autotesting.model.Candle;
import org.jboss.logging.Logger;

import java.util.List;

@ApplicationScoped
public class CandleRepository extends Repository {
    private static final Logger log = Logger.getLogger(CandleRepository.class);

    private static final org.autotesting.database.tables.Candle tbl = org.autotesting.database.tables.Candle.CANDLE;

    private static final Currency cTbl = Currency.CURRENCY;

    public CandleRepository() {
        init();
    }

    public List<Candle> get(int limit, int offset, String currency) throws ConnectionLostException {
        var select = ctx.select()
                .from(tbl)
                .join(cTbl)
                .on(cTbl.CURRENCY_ID.eq(tbl.CURRENCY_ID))
                .where(cTbl.TITLE.eq(currency))
                .offset(offset)
                .limit(limit);
        try {
            return select.fetch().stream()
                    .map(CandleExtension::fromRecord)
                    .toList();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            isConnected = false;
            throw new ConnectionLostException(ex);
        }
    }

    public boolean insert(Candle candle) throws ConnectionLostException {
        var currencyIdRes = ctx
                .select(cTbl.CURRENCY_ID)
                .from(cTbl)
                .where(cTbl.TITLE.eq(candle.currency))
                .fetchOne();
        if(currencyIdRes == null) {
            log.error("Could not find currency");
            return false;
        }
        var insert = ctx
                .insertInto(tbl)
                .columns(tbl.CURRENCY_ID, tbl.OPEN_PRICE, tbl.CLOSE_PRICE, tbl.HIGH_PRICE, tbl.LOW_PRICE)
                .values(currencyIdRes.get(cTbl.CURRENCY_ID), candle.open, candle.close, candle.high, candle.low);
        try {
            return insert.execute() > 0;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            isConnected = false;
            throw new ConnectionLostException(ex);
        }
    }
}
