package com.albertarie.lib.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.stream.Collectors;

public class UrlUtils {
    /**
     * Builds a URL by appending the endpoint and parameters to the base URL.
     *
     * @param baseUrl    The base URL of the API.
     * @param endpoint   The API endpoint.
     * @param parameters A Map of query parameters.
     * @return The built URL as a string.
     */
    public static String buildUrl(String baseUrl, String endpoint, Map<String, String> parameters) {
        String paramString = parameters.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
        return baseUrl + endpoint + "?" + paramString;
    }

    /**
     * Formats the given LocalDateTime object as a string in the format "yyyy-MM-dd'T'HH:mm:ss'Z'".
     *
     * @param dateTime the LocalDateTime object to format
     * @return the formatted date and time as a string
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return dateTime.format(formatter);
    }
}
