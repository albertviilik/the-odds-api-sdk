package com.albertarie.lib.url;

public enum Market {
    H2H("h2h"),
    SPREADS("spreads"),
    TOTALS("totals"),
    OUTRIGHTS("outrights");

    private final String market;

    Market(String market) {
        this.market = market;
    }

    @Override
    public String toString() {
        return market;
    }
}
