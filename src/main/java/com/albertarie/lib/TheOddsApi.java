package com.albertarie.lib;

import com.albertarie.lib.core.NetworkService;
import com.albertarie.lib.data.Sport;
import com.albertarie.lib.util.UrlUtils;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.albertarie.lib.util.Constants.API_BASE;

/**
 * TheOddsApi class provides methods to interact with The Odds API.
 */
public class TheOddsApi {
    protected static final Logger logger = LogManager.getLogger();
    private final String apiKey;
    private final NetworkService networkService;
    private final Gson gson;

    private TheOddsApi(
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


}
