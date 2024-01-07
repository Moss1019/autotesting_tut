package org.autotesting.extensions;

import org.autotesting.database.tables.Account;
import org.autotesting.database.tables.Currency;
import org.autotesting.model.Trade;

public class TradeExtension {
    private static org.autotesting.database.tables.Trade tbl = org.autotesting.database.tables.Trade.TRADE;

    private static Account aTbl = Account.ACCOUNT;

    private static Currency cTbl = Currency.CURRENCY;

    public static Trade fromRecord(org.jooq.Record record) {
        var trade = new Trade();
        trade.id = record.get(tbl.TRADE_ID);
        trade.accountNumber = record.get(aTbl.ACCOUNT_NUMBER).trim();
        trade.currency = record.get(cTbl.TITLE).trim();
        trade.openPrice = record.get(tbl.BID_PRICE);
        trade.openTime = record.get(tbl.OPEN_TIME);
        return trade;
    }
}
