package org.autotesting.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.autotesting.exception.ConnectionLostException;
import org.autotesting.model.Candle;
import org.autotesting.repository.CandleRepository;
import org.autotesting.repository.Repository;
import org.jboss.logging.Logger;

import java.util.List;

@ApplicationScoped
public class CandleService {
    private static final Logger log = Logger.getLogger(CandleService.class);

    @Inject
    CandleRepository repo;

    public List<Candle> getCandles(String currency, int limit, int offset) {
        try {
            return repo.get(limit, offset, currency);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            log.debug(Repository.getError());
            Repository.init();
            return null;
        }
    }

    public boolean create(Candle candle) {
        try {
            return repo.insert(candle);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            log.debug(Repository.getError());
            Repository.init();
            return false;
        }
    }
}
