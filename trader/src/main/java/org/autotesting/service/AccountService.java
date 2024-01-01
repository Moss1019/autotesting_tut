package org.autotesting.service;

import org.autotesting.model.Account;
import org.autotesting.repository.AccountRepository;

public class AccountService {
    private AccountRepository repo;

    public AccountService() {
        repo = new AccountRepository();
    }

    public Account getForUsername(String username) {
        return repo.getForUserName(username);
    }

    public boolean create(Account newAccount) {
        return repo.insert(newAccount);
    }

    public int getTotalOpenTrades(String username) {
        return repo.getTotalOpenTrades(username);
    }
}
