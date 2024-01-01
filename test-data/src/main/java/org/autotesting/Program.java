package org.autotesting;

import org.autotesting.database.tables.Account;
import org.autotesting.database.tables.Candle;
import org.autotesting.database.tables.Currency;
import org.autotesting.database.tables.Trade;
import org.autotesting.database.tables.records.CandleRecord;
import org.autotesting.database.tables.records.TradeRecord;
import org.jooq.DSLContext;
import org.jooq.Insert;
import org.jooq.impl.DSL;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;
import java.util.UUID;

public class Program {
    private static Account aTbl = Account.ACCOUNT;
    private static Candle cTbl = Candle.CANDLE;
    private static Currency cuTbl = Currency.CURRENCY;
    private static Trade tTbl = Trade.TRADE;

    private static DSLContext ctx;

    public static void main(String[] args) {
        ctx = DSL.using("jdbc:postgresql://localhost:5432/trader_db",
                "postgres",
                "secret123!");

        ctx.deleteFrom(tTbl).execute();
        ctx.deleteFrom(cTbl).execute();
        ctx.deleteFrom(cuTbl).execute();
        ctx.deleteFrom(aTbl).execute();

        var insertAccounts = ctx
                .insertInto(aTbl)
                .columns(aTbl.USERNAME, aTbl.ACCOUNT_NUMBER)
                .values("henner192", UUID.randomUUID().toString())
                .values("someone022", UUID.randomUUID().toString());

        var insertCurrencies = ctx
                .insertInto(cuTbl)
                .columns(cuTbl.TITLE)
                .values("eur/usd")
                .values("eur/chf");

        ctx.batch(insertAccounts, insertCurrencies).execute();
        ctx.batch(generateCandles(), generateTrades()).execute();
    }

    private static Insert<CandleRecord> generateCandles() {
        var usdId = ctx
                .select(cuTbl.CURRENCY_ID)
                .from(cuTbl)
                .where(cuTbl.TITLE.eq("eur/usd"))
                .fetchOne()
                .get(cuTbl.CURRENCY_ID);
        var chfId = ctx
                .select(cuTbl.CURRENCY_ID)
                .from(cuTbl)
                .where(cuTbl.TITLE.eq("eur/chf"))
                .fetchOne()
                .get(cuTbl.CURRENCY_ID);
        var insert = ctx
                .insertInto(cTbl)
                .columns(cTbl.CURRENCY_ID,
                        cTbl.OPEN_PRICE,
                        cTbl.CLOSE_PRICE,
                        cTbl.HIGH_PRICE,
                        cTbl.LOW_PRICE);
        var random = new Random();
        for (int i = 0; i < 100; ++i) {
            var open = 1.14 * random.nextDouble();
            var close = 1.14 * random.nextDouble();
            insert = insert
                    .values(usdId,
                            open,
                            close,
                            Math.max(open, close) * 1.0005,
                            Math.min(open, close) * 1.0005);
        }
        for (int i = 0; i < 100; ++i) {
            var open = 0.95 * random.nextDouble();
            var close = 0.95 * random.nextDouble();
            insert = insert
                    .values(chfId,
                            open,
                            close,
                            Math.max(open, close) * 1.0005,
                            Math.min(open, close) * 1.0005);
        }
        return insert;
    }

    private static Insert<TradeRecord> generateTrades() {
        var hennerId = ctx
                .select(aTbl.ACCOUNT_ID)
                .from(aTbl)
                .where(DSL.trim(aTbl.USERNAME).eq("henner192"))
                .fetchOne()
                .get(aTbl.ACCOUNT_ID);
        var someoneId = ctx
                .select(aTbl.ACCOUNT_ID)
                .from(aTbl)
                .where(DSL.trim(aTbl.USERNAME).eq("someone022"))
                .fetchOne()
                .get(aTbl.ACCOUNT_ID);
        var usdId = ctx
                .select(cuTbl.CURRENCY_ID)
                .from(cuTbl)
                .where(cuTbl.TITLE.eq("eur/usd"))
                .fetchOne()
                .get(cuTbl.CURRENCY_ID);
        var chfId = ctx
                .select(cuTbl.CURRENCY_ID)
                .from(cuTbl)
                .where(cuTbl.TITLE.eq("eur/chf"))
                .fetchOne()
                .get(cuTbl.CURRENCY_ID);
        var random = new Random();
        var insert = ctx
                .insertInto(tTbl)
                .columns(tTbl.ACCOUNT_ID, tTbl.CURRENCY_ID, tTbl.BID_PRICE, tTbl.OPEN_TIME)
                .values(hennerId, usdId, 1.14 * (random.nextDouble() + 0.5), getTime().minusDays(4))
                .values(hennerId, chfId, 0.97 * (random.nextDouble() + 0.5), getTime().minusDays(5))
                .values(someoneId, usdId, 1.14 * (random.nextDouble() + 0.5), getTime().minusDays(3))
                .values(someoneId, usdId, 1.14 * (random.nextDouble() + 0.5), getTime().minusDays(1));
        return insert;
    }

    private static LocalDateTime getTime() {
        return LocalDateTime.now(ZoneOffset.UTC);
    }
}
