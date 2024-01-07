package org.autotesting.repository;

import jakarta.enterprise.context.ApplicationScoped;
import org.autotesting.database.tables.Account;
import org.autotesting.database.tables.Currency;
import org.autotesting.exception.ConnectionLostException;
import org.autotesting.extensions.TradeExtension;
import org.autotesting.model.Trade;
import org.jboss.logging.Logger;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class TradeRepository extends Repository {
    private static final Logger log = Logger.getLogger(TradeRepository.class);

    private static org.autotesting.database.tables.Trade tbl = org.autotesting.database.tables.Trade.TRADE;

    private static Account aTbl = Account.ACCOUNT;

    private static Currency cTbl = Currency.CURRENCY;

    public TradeRepository() {
        init();
    }

    public List<Trade> getTrades(String username) throws ConnectionLostException {
        var select = ctx
                .select()
                .from(tbl)
                .join(aTbl)
                .on(aTbl.ACCOUNT_ID.eq(tbl.ACCOUNT_ID))
                .join(cTbl)
                .on(cTbl.CURRENCY_ID.eq(tbl.CURRENCY_ID))
                .where(aTbl.USERNAME.eq(username));
        try {
            return select.fetch()
                    .stream()
                    .map(TradeExtension::fromRecord)
                    .toList();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            isConnected = false;
            throw new ConnectionLostException(ex);
        }
    }

    public boolean insert(Trade newTrade) throws ConnectionLostException {
        var accountIdResult = ctx
                .select(aTbl.ACCOUNT_ID)
                .from(aTbl)
                .where(aTbl.ACCOUNT_NUMBER.eq(newTrade.accountNumber))
                .fetchOne();
        if(accountIdResult == null) {
            log.info("Could not find account id");
            return false;
        }
        var currencyIdRes = ctx
                .select(cTbl.CURRENCY_ID)
                .from(cTbl)
                .where(cTbl.TITLE.eq(newTrade.currency))
                .fetchOne();
        if(currencyIdRes == null) {
            log.info("Could not find currency id");
            return false;
        }
        var insert = ctx
                .insertInto(tbl)
                .columns(tbl.OPEN_TIME, tbl.BID_PRICE, tbl.CURRENCY_ID, tbl.ACCOUNT_ID)
                .values(newTrade.openTime, newTrade.openPrice, currencyIdRes.get(cTbl.CURRENCY_ID), accountIdResult.get(aTbl.ACCOUNT_ID));
        try {
            return insert.execute() > 0;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            isConnected = false;
            throw new ConnectionLostException(ex);
        }
    }

    public boolean delete(Trade trade) throws ConnectionLostException {
        var delete = ctx
                .deleteFrom(tbl)
                .where(tbl.TRADE_ID.eq(trade.id));
        try {
            return delete.execute() > 0;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            isConnected = false;
            throw new ConnectionLostException(ex);
        }
    }
}
