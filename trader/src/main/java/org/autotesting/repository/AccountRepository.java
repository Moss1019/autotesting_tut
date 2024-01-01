package org.autotesting.repository;

import org.autotesting.database.tables.Trade;
import org.autotesting.extensions.AccountExtension;
import org.autotesting.model.Account;

public class AccountRepository extends Repository {
    private static final org.autotesting.database.tables.Account tbl = org.autotesting.database.tables.Account.ACCOUNT;

    private static final Trade tradeTbl = Trade.TRADE;

    public AccountRepository() {
        init();
    }

    public Account getForUserName(String username) {
        var select = ctx.select()
                .from(tbl)
                .where(tbl.USERNAME.eq(username));
        var result = select.fetchOne();
        if(result == null) {
            return null;
        }
        return AccountExtension.fromRecord(result);
    }

    public boolean insert(Account newAccount) {
        var insert = ctx.insertInto(tbl)
                .columns(tbl.ACCOUNT_NUMBER, tbl.USERNAME)
                .values(newAccount.accountNumber, newAccount.username);
        var result = insert.execute();
        return result > 0;
    }

    public int getTotalOpenTrades(String username) {
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
        var result = select.fetchOne();
        if(result != null) {
            return result.get("count", Integer.class);
        }
        return -1;
    }
}
