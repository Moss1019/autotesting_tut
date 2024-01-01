package org.autotesting.model;

import java.time.LocalDateTime;

public class Trade {
    public int id;

    public double openPrice;

    public LocalDateTime openTime;

    public String currency;

    public String accountNumber;

    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", openPrice=" + openPrice +
                ", openTime=" + openTime +
                ", currency='" + currency + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
