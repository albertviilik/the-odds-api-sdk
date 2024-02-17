package com.albertarie.lib.url;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum BookMaker {
    // US Bookmakers
    BETONLINEAG("betonlineag", Region.US),
    BETMGM("betmgm", Region.US),
    BETRIVERS("betrivers", Region.US),
    BETUS("betus", Region.US),
    BOVADA("bovada", Region.US),
    DRAFTKINGS("draftkings", Region.US),
    FANDUEL("fanduel", Region.US),
    LOWVIG("lowvig", Region.US),
    MYBOOKIEAG("mybookieag", Region.US),
    POINTSBETUS("pointsbetus", Region.US),
    SUPERBOOK("superbook", Region.US),
    UNIBET_US("unibet_us", Region.US),
    WILLIAMHILL_US("williamhill_us", Region.US),
    WYNNBET("wynnbet", Region.US),
    ESPNBET("espnbet", Region.US),
    HARDROCKBET("hardrockbet", Region.US),
    SISPORTSBOOK("sisportsbook", Region.US),
    TIPICO_US("tipico_us", Region.US),
    // UK Bookmakers
    SPORT888("sport888", Region.UK),
    BETFAIR_EX_UK("betfair_ex_uk", Region.UK),
    BETFAIR_SB_UK("betfair_sb_uk", Region.UK),
    BETVICTOR("betvictor", Region.UK),
    BETWAY("betway", Region.UK),
    BOYLESPORTS("boylesports", Region.UK),
    CASUMO("casumo", Region.UK),
    CORAL("coral", Region.UK),
    GROSVENOR("grosvenor", Region.UK),
    LADBROKES_UK("ladbrokes_uk", Region.UK),
    LEOVEGAS("leovegas", Region.UK),
    LIVESCOREBET("livescorebet", Region.UK),
    MATCHBOOK("matchbook", Region.UK),
    MRGREEN("mrgreen", Region.UK),
    PADDYPOWER("paddypower", Region.UK),
    SKYBET("skybet", Region.UK),
    UNIBET_UK("unibet_uk", Region.UK),
    VIRGINBET("virginbet", Region.UK),
    WILLIAMHILL("williamhill", Region.UK),
    // EU Bookmakers
    ONEXBET("onexbet", Region.EU),
    BETCLIC("betclic", Region.EU),
    BETFAIR_EX_EU("betfair_ex_eu", Region.EU),
    BETSSON("betsson", Region.EU),
    COOLBET("coolbet", Region.EU),
    EVERYGAME("everygame", Region.EU),
    LIVESCOREBET_EU("livescorebet_eu", Region.EU),
    MARATHONBET("marathonbet", Region.EU),
    NORDICBET("nordicbet", Region.EU),
    PINNACLE("pinnacle", Region.EU),
    SUPRABETS("suprabets", Region.EU),
    UNIBET_EU("unibet_eu", Region.EU),
    // AU Bookmakers
    BETFAIR_EX_AU("betfair_ex_au", Region.AU),
    BETR_AU("betr_au", Region.AU),
    BLUEBET("bluebet", Region.AU),
    LADBROKES_AU("ladbrokes_au", Region.AU),
    NEDS("neds", Region.AU),
    PLAYUP("playup", Region.AU),
    POINTSBETAU("pointsbetau", Region.AU),
    SPORTSBET("sportsbet", Region.AU),
    TAB("tab", Region.AU),
    TOPSPORT("topsport", Region.AU),
    UNIBET("unibet", Region.AU);

    private final String bookMaker;
    private final Region region;

    BookMaker(String bookMaker, Region region) {
        this.bookMaker = bookMaker;
        this.region = region;
    }

    public static List<BookMaker> getByRegion(Region region) {
        return Arrays.stream(values())
                .filter(bm -> bm.region == region)
                .collect(Collectors.toList());
    }

    public Region getRegion() {
        return region;
    }

    @Override
    public String toString() {
        return bookMaker;
    }
}
