package org.autotesting.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.autotesting.model.Account;
import org.autotesting.repository.AccountRepository;
import org.autotesting.repository.Repository;
import org.jboss.logging.Logger;

import java.util.UUID;

@ApplicationScoped
public class AccountService {
    private static final Logger log = Logger.getLogger(AccountService.class);

    @Inject
    AccountRepository repo;

    public Account getForUsername(String username) {
        try {
            return repo.getForUserName(username);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            log.debug(Repository.getError());
            Repository.init();
            return null;
        }
    }

    public boolean create(Account newAccount) {
        try {
            newAccount.accountNumber = UUID.randomUUID().toString();
            return repo.insert(newAccount);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            log.debug(Repository.getError());
            Repository.init();
            return false;
        }
    }

    public int getTotalOpenTrades(String username) {
        try {
            return repo.getTotalOpenTrades(username);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            log.debug(Repository.getError());
            Repository.init();
            return -1;
        }
    }
}
