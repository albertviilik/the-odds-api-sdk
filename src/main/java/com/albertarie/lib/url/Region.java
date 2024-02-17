package com.albertarie.lib.url;

public enum Region {
    US("us"),
    UK("uk"),
    AU("au"),
    EU("eu");

    private final String region;

    Region(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return region;
    }
}
