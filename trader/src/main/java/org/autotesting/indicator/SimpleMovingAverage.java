package org.autotesting.indicator;

import org.autotesting.model.Candle;

import java.util.ArrayList;
import java.util.List;

public class SimpleMovingAverage {
    private final int period;

    private final List<Double> values;

    public SimpleMovingAverage(int period) {
        this.period = period;
        values = new ArrayList<>();
    }

    public List<Double> getValues() {
        return values;
    }

    public void calculate(List<Candle> candles) {
        var periodOffset = period - 1;
        for(var i = periodOffset; i < candles.size(); ++i) {
            var value = 0.0;
            for(var j = i - periodOffset; j <= i; ++j) {
                value += candles.get(j).close;
            }
            value /= period;
            values.add(value);
        }
    }

    public double getValue(int offset) {
        if(offset < values.size()) {
            return values.get(values.size() - 1 - offset);
        }
        return 0.0;
    }
}
