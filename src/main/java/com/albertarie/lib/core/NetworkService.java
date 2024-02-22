package com.albertarie.lib.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.function.Supplier;

/**
 * The NetworkService class provides functionality for sending HTTP GET requests.
 */
public class NetworkService {
    protected static final Logger logger = LogManager.getLogger();
    private final Supplier<HttpClient> httpClientSupplier;

    public NetworkService(Supplier<HttpClient> httpClientSupplier) {
        this.httpClientSupplier = httpClientSupplier;
    }

    /**
     * Sends a GET request to the specified URI with optional headers.
     *
     * @param uri     The URI to send the request to.
     * @param headers A Map of headers to include in the request (optional).
     * @return The response body as a String if the request was successful.
     * @throws Exception if an error occurs during the request.
     */
    public String get(URI uri, Map<String, String> headers) throws Exception {
        logger.debug("Sending GET request to: " + uri.toString());

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();
        requestBuilder.uri(uri);
        requestBuilder.GET();

        if (headers != null) {
            logger.debug("Adding headers to request: " + headers);
            headers.forEach(requestBuilder::header);
        }

        HttpRequest request = requestBuilder.build();

        HttpClient httpClient = httpClientSupplier.get();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            logger.debug("Request to " + uri + " was successful");
            return response.body();
        } else {
            logger.error("Request to " + uri + " failed with status code: " + response.statusCode() + " and body: " + response.body());
            throw new Exception("HTTP request failed with status code: " + response.statusCode() + " and body: " + response.body());
        }
    }
}
