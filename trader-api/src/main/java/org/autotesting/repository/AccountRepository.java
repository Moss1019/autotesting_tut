package org.autotesting.repository;

import jakarta.enterprise.context.ApplicationScoped;
import org.autotesting.database.tables.Trade;
import org.autotesting.exception.ConnectionLostException;
import org.autotesting.extensions.AccountExtension;
import org.autotesting.model.Account;
import org.jboss.logging.Logger;

@ApplicationScoped
public class AccountRepository extends Repository {
    private static final Logger log = Logger.getLogger(AccountRepository.class);

    private static final org.autotesting.database.tables.Account tbl = org.autotesting.database.tables.Account.ACCOUNT;

    private static final Trade tradeTbl = Trade.TRADE;

    public AccountRepository() {
        init();
    }

    public Account getForUserName(String username) throws ConnectionLostException {
        var select = ctx.select()
                .from(tbl)
                .where(tbl.USERNAME.eq(username));
        try {
            var result = select.fetchOne();
            if (result == null) {
                return null;
            }
            return AccountExtension.fromRecord(result);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            isConnected = false;
            throw new ConnectionLostException(ex);
        }
    }

    public boolean insert(Account newAccount) throws ConnectionLostException {
        var insert = ctx.insertInto(tbl)
                .columns(tbl.ACCOUNT_NUMBER, tbl.USERNAME)
                .values(newAccount.accountNumber, newAccount.username);
        try {
            var result = insert.execute();
            return result > 0;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            isConnected = false;
            throw new ConnectionLostException(ex);
        }
    }

    public int getTotalOpenTrades(String username) throws ConnectionLostException {
        var subQuery = ctx.select(tbl.ACCOUNT_ID)
                .from(tbl)
                .where(tbl.USERNAME.eq(username));
        var idResult = subQuery.fetchOne();
        if(idResult == null) {
            return -1;
        }
        var select = ctx.selectCount()
                .from(tradeTbl)
                .where(tradeTbl.ACCOUNT_ID.eq(idResult.get(tbl.ACCOUNT_ID)));
        try {
            var result = select.fetchOne();
            if (result != null) {
                return result.get("count", Integer.class);
            }
            return -1;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            isConnected = false;
            throw new ConnectionLostException(ex);
        }
    }
}
