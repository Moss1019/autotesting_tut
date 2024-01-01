package org.autotesting.repository;

import org.autotesting.database.tables.Account;
import org.autotesting.database.tables.Currency;
import org.autotesting.extensions.TradeExtension;
import org.autotesting.model.Trade;

import java.util.List;

public class TradeRepository extends Repository {
    private static org.autotesting.database.tables.Trade tbl = org.autotesting.database.tables.Trade.TRADE;

    private static Account aTbl = Account.ACCOUNT;

    private static Currency cTbl = Currency.CURRENCY;

    public TradeRepository() {
        init();
    }

    public List<Trade> getTrades(String username) {
        var select = ctx
                .select()
                .from(tbl)
                .join(aTbl)
                .on(aTbl.ACCOUNT_ID.eq(tbl.ACCOUNT_ID))
                .join(cTbl)
                .on(cTbl.CURRENCY_ID.eq(tbl.CURRENCY_ID))
                .where(aTbl.USERNAME.eq(username));
        return select.fetch()
                .stream()
                .map(TradeExtension::fromRecord)
                .toList();
    }

    public boolean insert(Trade newTrade) {
        var accountIdResult = ctx
                .select(aTbl.ACCOUNT_ID)
                .from(aTbl)
                .where(aTbl.ACCOUNT_NUMBER.eq(newTrade.accountNumber))
                .fetchOne();
        if(accountIdResult == null) {
            return false;
        }
        var currencyIdRes = ctx
                .select(cTbl.CURRENCY_ID)
                .from(cTbl)
                .where(cTbl.TITLE.eq(newTrade.currency))
                .fetchOne();
        if(currencyIdRes == null) {
            return false;
        }
        var insert = ctx
                .insertInto(tbl)
                .columns(tbl.OPEN_TIME, tbl.BID_PRICE, tbl.CURRENCY_ID, tbl.ACCOUNT_ID)
                .values(newTrade.openTime, newTrade.openPrice, currencyIdRes.get(cTbl.CURRENCY_ID), accountIdResult.get(aTbl.ACCOUNT_ID));
        return insert.execute() > 0;
    }

    public boolean delete(Trade trade) {
        var delete = ctx
                .deleteFrom(tbl)
                .where(tbl.TRADE_ID.eq(trade.id));
        return delete.execute() > 0;
    }
}
