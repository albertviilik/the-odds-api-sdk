package com.albertarie.lib.url;

public enum DateFormat {
    UNIX("unix"),
    ISO("iso");

    private final String dateFormat;

    DateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public String toString() {
        return dateFormat;
    }
}