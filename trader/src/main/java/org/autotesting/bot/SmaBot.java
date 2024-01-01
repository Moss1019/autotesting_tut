package org.autotesting.bot;


import org.autotesting.indicator.SimpleMovingAverage;
import org.autotesting.model.Account;
import org.autotesting.model.Trade;
import org.autotesting.service.CandleService;
import org.autotesting.service.TradeService;
import org.autotesting.util.CurrentTime;

import java.util.Random;

public class SmaBot {
    private final TradeService tradeService = new TradeService();

    private final CandleService candleService = new CandleService();

    private final CurrentTime time = new CurrentTime();

    private final String currency;

    public SmaBot(String currency) {
        this.currency = currency;
    }

    public boolean process(Account trader) {
        var random = new Random();
        var limit = Math.abs(random.nextInt() % 100);
        var offset = limit > 50 ? 0 : 50;
        var candles = candleService.getCandles(currency, limit, offset);
        var sma10 = new SimpleMovingAverage(10);
        sma10.calculate(candles);
        var sma5 = new SimpleMovingAverage(5);
        sma5.calculate(candles);
        if(sma5.getValue(0) > sma10.getValue(0)) {
            var trade = new Trade();
            trade.openTime = time.get();
            trade.openPrice = candles.get(candles.size() - 1).open;
            trade.accountNumber = trader.accountNumber;
            trade.currency = currency;
            tradeService.openTrade(trade);
            return true;
        }
        return false;
    }
}
