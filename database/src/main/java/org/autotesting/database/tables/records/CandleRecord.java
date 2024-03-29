/*
 * This file is generated by jOOQ.
 */
package org.autotesting.database.tables.records;


import org.autotesting.database.tables.Candle;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CandleRecord extends UpdatableRecordImpl<CandleRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.candle.candle_id</code>.
     */
    public void setCandleId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.candle.candle_id</code>.
     */
    public Integer getCandleId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.candle.open_price</code>.
     */
    public void setOpenPrice(Double value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.candle.open_price</code>.
     */
    public Double getOpenPrice() {
        return (Double) get(1);
    }

    /**
     * Setter for <code>public.candle.close_price</code>.
     */
    public void setClosePrice(Double value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.candle.close_price</code>.
     */
    public Double getClosePrice() {
        return (Double) get(2);
    }

    /**
     * Setter for <code>public.candle.high_price</code>.
     */
    public void setHighPrice(Double value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.candle.high_price</code>.
     */
    public Double getHighPrice() {
        return (Double) get(3);
    }

    /**
     * Setter for <code>public.candle.low_price</code>.
     */
    public void setLowPrice(Double value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.candle.low_price</code>.
     */
    public Double getLowPrice() {
        return (Double) get(4);
    }

    /**
     * Setter for <code>public.candle.currency_id</code>.
     */
    public void setCurrencyId(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.candle.currency_id</code>.
     */
    public Integer getCurrencyId() {
        return (Integer) get(5);
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
     * Create a detached CandleRecord
     */
    public CandleRecord() {
        super(Candle.CANDLE);
    }

    /**
     * Create a detached, initialised CandleRecord
     */
    public CandleRecord(Integer candleId, Double openPrice, Double closePrice, Double highPrice, Double lowPrice, Integer currencyId) {
        super(Candle.CANDLE);

        setCandleId(candleId);
        setOpenPrice(openPrice);
        setClosePrice(closePrice);
        setHighPrice(highPrice);
        setLowPrice(lowPrice);
        setCurrencyId(currencyId);
        resetChangedOnNotNull();
    }
}
