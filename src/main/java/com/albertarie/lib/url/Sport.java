package com.albertarie.lib.url;

public enum Sport {
    UPCOMING("upcoming"),
    AMERICANFOOTBALL_NFL("americanfootball_nfl"),
    AMERICANFOOTBALL_NFL_SUPER_BOWL_WINNER("americanfootball_nfl_super_bowl_winner"),
    AUSSIERULES_AFL("aussierules_afl"),
    BASKETBALL_NBA("basketball_nba"),
    BASKETBALL_NBA_CHAMPIONSHIP_WINNER("basketball_nba_championship_winner"),
    BASKETBALL_NCAAB("basketball_ncaab"),
    BASKETBALL_NCAAB_CHAMPIONSHIP_WINNER("basketball_ncaab_championship_winner"),
    BOXING_BOXING("boxing_boxing"),
    CRICKET_ODI("cricket_odi"),
    CRICKET_TEST_MATCH("cricket_test_match"),
    GOLF_MASTERS_TOURNAMENT_WINNER("golf_masters_tournament_winner"),
    GOLF_PGA_CHAMPIONSHIP_WINNER("golf_pga_championship_winner"),
    GOLF_THE_OPEN_CHAMPIONSHIP_WINNER("golf_the_open_championship_winner"),
    GOLF_US_OPEN_WINNER("golf_us_open_winner"),
    ICEHOCKEY_NHL("icehockey_nhl"),
    ICEHOCKEY_NHL_CHAMPIONSHIP_WINNER("icehockey_nhl_championship_winner"),
    ICEHOCKEY_SWEDEN_ALLSVENSKAN("icehockey_sweden_allsvenskan"),
    ICEHOCKEY_SWEDEN_HOCKEY_LEAGUE("icehockey_sweden_hockey_league"),
    MMA_MIXED_MARTIAL_ARTS("mma_mixed_martial_arts"),
    POLITICS_US_PRESIDENTIAL_ELECTION_WINNER("politics_us_presidential_election_winner"),
    RUGBYLEAGUE_NRL("rugbyleague_nrl"),
    SOCCER_AFRICA_CUP_OF_NATIONS("soccer_africa_cup_of_nations"),
    SOCCER_AUSTRALIA_ALEAGUE("soccer_australia_aleague"),
    SOCCER_AUSTRIA_BUNDESLIGA("soccer_austria_bundesliga"),
    SOCCER_BELGIUM_FIRST_DIV("soccer_belgium_first_div"),
    SOCCER_CHILE_CAMPEONATO("soccer_chile_campeonato"),
    SOCCER_CONMEBOL_COPA_LIBERTADORES("soccer_conmebol_copa_libertadores"),
    SOCCER_DENMARK_SUPERLIGA("soccer_denmark_superliga"),
    SOCCER_EFL_CHAMP("soccer_efl_champ"),
    SOCCER_ENGLAND_EFL_CUP("soccer_england_efl_cup"),
    SOCCER_ENGLAND_LEAGUE1("soccer_england_league1"),
    SOCCER_ENGLAND_LEAGUE2("soccer_england_league2"),
    SOCCER_EPL("soccer_epl"),
    SOCCER_FA_CUP("soccer_fa_cup"),
    SOCCER_FIFA_WORLD_CUP_WINNER("soccer_fifa_world_cup_winner"),
    SOCCER_FRANCE_LIGUE_ONE("soccer_france_ligue_one"),
    SOCCER_FRANCE_LIGUE_TWO("soccer_france_ligue_two"),
    SOCCER_GERMANY_BUNDESLIGA("soccer_germany_bundesliga"),
    SOCCER_GERMANY_BUNDESLIGA2("soccer_germany_bundesliga2"),
    SOCCER_GERMANY_LIGA3("soccer_germany_liga3"),
    SOCCER_GREECE_SUPER_LEAGUE("soccer_greece_super_league"),
    SOCCER_ITALY_SERIE_A("soccer_italy_serie_a"),
    SOCCER_ITALY_SERIE_B("soccer_italy_serie_b"),
    SOCCER_LEAGUE_OF_IRELAND("soccer_league_of_ireland"),
    SOCCER_MEXICO_LIGAMX("soccer_mexico_ligamx"),
    SOCCER_NETHERLANDS_EREDIVISIE("soccer_netherlands_eredivisie"),
    SOCCER_POLAND_EKSTRAKLASA("soccer_poland_ekstraklasa"),
    SOCCER_PORTUGAL_PRIMEIRA_LIGA("soccer_portugal_primeira_liga"),
    SOCCER_SPAIN_LA_LIGA("soccer_spain_la_liga"),
    SOCCER_SPAIN_SEGUNDA_DIVISION("soccer_spain_segunda_division"),
    SOCCER_SPL("soccer_spl"),
    SOCCER_SWEDEN_ALLSVENSKAN("soccer_sweden_allsvenskan"),
    SOCCER_SWITZERLAND_SUPERLEAGUE("soccer_switzerland_superleague"),
    SOCCER_TURKEY_SUPER_LEAGUE("soccer_turkey_super_league"),
    SOCCER_UEFA_CHAMPS_LEAGUE("soccer_uefa_champs_league"),
    SOCCER_UEFA_EURO_QUALIFICATION("soccer_uefa_euro_qualification"),
    SOCCER_UEFA_EUROPA_CONFERENCE_LEAGUE("soccer_uefa_europa_conference_league"),
    SOCCER_UEFA_EUROPA_LEAGUE("soccer_uefa_europa_league");

    private final String sport;

    Sport(String sport) {
        this.sport = sport;
    }

    @Override
    public String toString() {
        return sport;
    }
}

