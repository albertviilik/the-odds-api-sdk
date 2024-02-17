package com.albertarie.lib;

import com.albertarie.lib.core.NetworkService;
import com.albertarie.lib.data.Game;
import com.albertarie.lib.data.Sport;
import com.albertarie.lib.url.*;
import com.albertarie.lib.util.Constants;
import com.albertarie.lib.util.UrlUtils;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.net.http.HttpClient;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.albertarie.lib.util.Constants.API_BASE;

/**
 * TheOddsApi class provides methods to interact with The Odds API.
 */
public class TheOddsApi {
    protected static final Logger logger = LogManager.getLogger();
    private final String apiKey;
    private final NetworkService networkService;
    private final Gson gson;

    public TheOddsApi(
            String apiKey,
            NetworkService networkService,
            Gson gson
    ) {
        this.apiKey = apiKey;
        this.networkService = networkService;
        this.gson = gson;
    }

    public TheOddsApi(String apiKey) {
        this(apiKey, new NetworkService(HttpClient::newHttpClient), new Gson());
    }

    /**
     * Returns the default parameters for API calls.
     *
     * @return A Map containing the default parameters.
     */
    private Map<String, String> defaultParams() {
        HashMap<String, String> params = new HashMap<>();
        params.put("apiKey", apiKey);
        return params;
    }

    /**
     * Retrieves the list of sports.
     *
     * @param onlyInSeason A boolean value indicating whether to only return sports currently in season.
     * @return A List of Sport objects representing the available sports.
     * @throws Exception if an error occurs during the API call.
     */
    public List<Sport> getSports(
            Boolean onlyInSeason
    ) throws Exception {
        logger.info("Fetching sports data...");

        Map<String, String> urlParams = defaultParams();

        if (onlyInSeason) {
            logger.info("Retrieving only sports in season");
            urlParams.put("onlyInSeason", onlyInSeason.toString());
        }

        String url = UrlUtils.buildUrl(API_BASE, "", urlParams);
        URI uri = URI.create(url);

        logger.info("Sending request to {}", uri);
        String response = networkService.get(uri, null);

        return List.of(gson.fromJson(response, Sport[].class));
    }

    /**
     * Fetches odds data based on the given parameters.
     *
     * @param sport            The sport to retrieve odds for.
     * @param regions          The regions to retrieve odds for.
     * @param markets          The markets to retrieve odds for.
     * @param dateFormat       The date format for the odds.
     * @param oddsFormat       The odds format for the odds.
     * @param eventIds         The event ids to filter odds by.
     * @param bookmakers       The bookmakers to retrieve odds from.
     * @param commenceTimeFrom The minimum commence time for the odds.
     * @param commenceTimeTo   The maximum commence time for the odds.
     * @return A List of Game objects representing the odds matching the given parameters.
     * @throws Exception if an error occurs during the API call.
     */
    public List<Game> getOdds(
            com.albertarie.lib.url.Sport sport,
            List<Region> regions,
            List<Market> markets,
            DateFormat dateFormat,
            OddsFormat oddsFormat,
            List<String> eventIds,
            List<BookMaker> bookmakers,
            LocalDateTime commenceTimeFrom,
            LocalDateTime commenceTimeTo
    ) throws Exception {
        logger.info("Fetching odds data...");

        if (sport == null) {
            throw new IllegalArgumentException("Sport cannot be null");
        }

        if (regions == null || regions.isEmpty()) {
            throw new IllegalArgumentException("Regions cannot be null or empty");
        }

        Map<String, String> urlParams = defaultParams();

        urlParams.put("regions", regions.stream().map(Region::toString).collect(Collectors.joining(",")));

        if (markets != null && !markets.isEmpty()) {
            urlParams.put("markets", markets.stream().map(Market::toString).collect(Collectors.joining(",")));
        }

        if (dateFormat != null) {
            urlParams.put("dateFormat", dateFormat.toString());
        }

        if (oddsFormat != null) {
            urlParams.put("oddsFormat", oddsFormat.toString());
        }

        if (eventIds != null && !eventIds.isEmpty()) {
            urlParams.put("eventIds", String.join(",", eventIds));
        }

        if (bookmakers != null && !bookmakers.isEmpty()) {
            urlParams.put("bookmakers", bookmakers.stream().map(BookMaker::toString).collect(Collectors.joining(",")));
        }

        if (commenceTimeFrom != null) {
            urlParams.put("commenceTimeFrom", UrlUtils.formatDateTime(commenceTimeFrom));
        }

        if (commenceTimeTo != null) {
            urlParams.put("commenceTimeTo", UrlUtils.formatDateTime(commenceTimeTo));
        }

        String endpoint = String.format(Constants.ODDS_ENDPOINT_FMT, sport);
        String url = UrlUtils.buildUrl(Constants.API_BASE, endpoint, urlParams);
        URI uri = URI.create(url);

        logger.info("Sending request to {}", uri);
        String response = networkService.get(uri, null);

        return List.of(gson.fromJson(response, Game[].class));
    }
}
