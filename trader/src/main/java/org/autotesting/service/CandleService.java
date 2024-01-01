package org.autotesting.service;

import org.autotesting.model.Candle;
import org.autotesting.repository.CandleRepository;

import java.util.List;

public class CandleService {
    private final CandleRepository repo;

    public CandleService() {
        repo = new CandleRepository();
    }

    public List<Candle> getCandles(String currency, int limit, int offset) {
        return repo.get(limit, offset, currency);
    }

    public boolean create(Candle candle) {
        return repo.insert(candle);
    }
}
