package org.autotesting;

import org.autotesting.bot.SmaBot;
import org.autotesting.indicator.SimpleMovingAverage;
import org.autotesting.model.Account;
import org.autotesting.service.AccountService;
import org.autotesting.service.CandleService;
import org.autotesting.service.TradeService;
import org.autotesting.util.UserInput;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Program {
    private static boolean isRunning;

    private static final Thread worker = new Thread(Program::doWork);

    private static AccountService accountService = new AccountService();

    private static TradeService tradeService = new TradeService();

    private static CandleService candleService = new CandleService();

    private static Account testAccount = setUpTestAccount();

    private static Logger log = LogManager.getLogger(Program.class);

    public static void main(String[] args) {
        var user = new UserInput(System.in);
        isRunning = true;
        worker.start();
        log.info("Worker started");
        while(isRunning) {
            var input = user.getInput();
            switch (input) {
                case "-q" -> {
                    isRunning = false;
                }

                case "-trades" -> {
                    System.out.printf("You have %d open trades%n", accountService.getTotalOpenTrades(testAccount.username));
                    var trades = tradeService.getTrades(testAccount.username);
                    for(var t: trades) {
                        System.out.println(t);
                    }
                }

                case "-usd" -> {
                    var candles = candleService.getCandles("eur/usd", 10, 0);
                    for(var c: candles) {
                        System.out.println(c);
                    }
                }

                case "-chf" -> {
                    var candles = candleService.getCandles("eur/chf", 10, 0);
                    for(var c: candles) {
                        System.out.println(c);
                    }
                }

                case "-indusd" -> {
                    var sma = new SimpleMovingAverage(10);
                    sma.calculate(candleService.getCandles("eur/usd", 50, 0));
                    for(var v: sma.getValues()) {
                        System.out.println(v);
                    }
                }

                case "-indchf" -> {
                    var sma = new SimpleMovingAverage(10);
                    sma.calculate(candleService.getCandles("eur/chf", 50, 0));
                    for(var v: sma.getValues()) {
                        System.out.println(v);
                    }
                }
            }
        }
        try {
            worker.join();
        } catch (Exception ignored) {}

    }

    private static void doWork() {
        var bot = new SmaBot("eur/usd");
        while(isRunning) {
            if(bot.process(testAccount)) {
                log.info("Trade placed");
            }
            try {
                Thread.sleep(5000);
            } catch (Exception ignored) {}
        }
    }

    private static Account setUpTestAccount() {
        return accountService.getForUsername("henner192");
    }
}
