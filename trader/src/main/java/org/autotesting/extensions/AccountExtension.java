package org.autotesting.extensions;

import org.autotesting.model.Account;

public class AccountExtension {
    private static final org.autotesting.database.tables.Account tbl = org.autotesting.database.tables.Account.ACCOUNT;

    public static Account fromRecord(org.jooq.Record record) {
        var account = new Account();
        account.id = record.get(tbl.ACCOUNT_ID);
        account.username = record.get(tbl.USERNAME).trim();
        account.accountNumber = record.get(tbl.ACCOUNT_NUMBER).trim();
        return account;
    }


}
