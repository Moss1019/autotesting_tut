package org.autotesting.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.autotesting.exception.ConnectionLostException;
import org.autotesting.model.Trade;
import org.autotesting.repository.Repository;
import org.autotesting.repository.TradeRepository;
import org.jboss.logging.Logger;

import java.util.List;

@ApplicationScoped
public class TradeService {
    private static final Logger log = Logger.getLogger(TradeService.class);

    @Inject
    TradeRepository repo;

    public List<Trade> getTrades(String username) {
        try {
            return repo.getTrades(username);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            log.debug(Repository.getError());
            Repository.init();
            return null;
        }
    }

    public boolean openTrade(Trade trade) {
        try {
            return repo.insert(trade);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            log.debug(Repository.getError());
            Repository.init();
            return false;
        }
    }

    public boolean closeTrade(Trade trade) {
        try {
            return repo.delete(trade);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            log.debug(Repository.getError());
            Repository.init();
            return false;
        }
    }
}
