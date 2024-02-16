package com.albertarie.lib.util;

import java.util.Map;
import java.util.stream.Collectors;

public class UrlUtils {
    /**
     * Builds a URL by appending the endpoint and parameters to the base URL.
     *
     * @param baseUrl     The base URL of the API.
     * @param endpoint    The API endpoint.
     * @param parameters  A Map of query parameters.
     * @return The built URL as a string.
     */
    public static String buildUrl(String baseUrl, String endpoint, Map<String, String> parameters) {
        String paramString = parameters.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
        return baseUrl + endpoint + "?" + paramString;
    }
}
