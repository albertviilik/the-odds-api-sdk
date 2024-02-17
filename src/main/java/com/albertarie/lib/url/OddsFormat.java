package com.albertarie.lib.url;

public enum OddsFormat {
    DECIMAL("decimal"),
    AMERICAN("american");

    private final String oddsFormat;

    OddsFormat(String oddsFormat) {
        this.oddsFormat = oddsFormat;
    }

    @Override
    public String toString() {
        return oddsFormat;
    }
}
