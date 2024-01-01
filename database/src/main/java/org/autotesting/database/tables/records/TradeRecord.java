/*
 * This file is generated by jOOQ.
 */
package org.autotesting.database.tables.records;


import java.time.LocalDateTime;

import org.autotesting.database.tables.Trade;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TradeRecord extends UpdatableRecordImpl<TradeRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.trade.trade_id</code>.
     */
    public void setTradeId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.trade.trade_id</code>.
     */
    public Integer getTradeId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.trade.bid_price</code>.
     */
    public void setBidPrice(Double value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.trade.bid_price</code>.
     */
    public Double getBidPrice() {
        return (Double) get(1);
    }

    /**
     * Setter for <code>public.trade.open_time</code>.
     */
    public void setOpenTime(LocalDateTime value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.trade.open_time</code>.
     */
    public LocalDateTime getOpenTime() {
        return (LocalDateTime) get(2);
    }

    /**
     * Setter for <code>public.trade.currency_id</code>.
     */
    public void setCurrencyId(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.trade.currency_id</code>.
     */
    public Integer getCurrencyId() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>public.trade.account_id</code>.
     */
    public void setAccountId(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.trade.account_id</code>.
     */
    public Integer getAccountId() {
        return (Integer) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TradeRecord
     */
    public TradeRecord() {
        super(Trade.TRADE);
    }

    /**
     * Create a detached, initialised TradeRecord
     */
    public TradeRecord(Integer tradeId, Double bidPrice, LocalDateTime openTime, Integer currencyId, Integer accountId) {
        super(Trade.TRADE);

        setTradeId(tradeId);
        setBidPrice(bidPrice);
        setOpenTime(openTime);
        setCurrencyId(currencyId);
        setAccountId(accountId);
        resetChangedOnNotNull();
    }
}