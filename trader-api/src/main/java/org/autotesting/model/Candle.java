package org.autotesting.model;

public class Candle {
    public int id;

    public String currency;

    public double open;

    public double close;

    public double high;

    public double low;

    @Override
    public String toString() {
        return "Candle{" +
                "id=" + id +
                ", currency='" + currency + '\'' +
                ", open=" + open +
                ", close=" + close +
                ", high=" + high +
                ", low=" + low +
                '}';
    }
}
