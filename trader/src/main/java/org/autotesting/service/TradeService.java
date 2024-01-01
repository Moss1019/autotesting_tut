package org.autotesting.service;

import org.autotesting.model.Trade;
import org.autotesting.repository.TradeRepository;

import java.util.List;

public class TradeService {
    private TradeRepository repo;

    public TradeService() {
        repo = new TradeRepository();
    }

    public List<Trade> getTrades(String username) {
        return repo.getTrades(username);
    }

    public boolean openTrade(Trade trade) {
        return repo.insert(trade);
    }

    public boolean closeTrade(Trade trade) {
        return repo.delete(trade);
    }
}
