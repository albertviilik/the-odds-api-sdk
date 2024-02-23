package com.albertarie.lib.core;

import com.albertarie.lib.exceptions.ApiKeyException;
import com.albertarie.lib.exceptions.RateLimitException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
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
    private static final String API_KEY_ERROR_MESSAGE;
    private static final String RATE_LIMIT_ERROR_MESSAGE;
    private static final String OTHER_ERROR_MESSAGE;

    static {
        API_KEY_ERROR_MESSAGE = "API key is invalid or has expired. Please provide a valid API key.";
        RATE_LIMIT_ERROR_MESSAGE = "Rate limit exceeded. Please wait before making another request.";
        OTHER_ERROR_MESSAGE = "Request to %s failed with status code: %d and body: \n%s";
    }

    private final Supplier<HttpClient> httpClientSupplier;

    public NetworkService(Supplier<HttpClient> httpClientSupplier) {
        this.httpClientSupplier = httpClientSupplier;
    }

    /**
     * Sends a GET request to the specified URI with the provided headers and returns the response body.
     *
     * @param uri     the URI to send the GET request to
     * @param headers the headers to include in the GET request (optional)
     * @return the response body as a String
     * @throws RateLimitException   if the request exceeds the rate limit
     * @throws ApiKeyException      if the API key is invalid or has expired
     * @throws RuntimeException     if the request fails with a status code other than 200, 401, or 429
     * @throws IOException          if an I/O error occurs while sending the request
     * @throws InterruptedException if the thread is interrupted while waiting for the response
     */
    public String get(URI uri, Map<String, String> headers) throws RateLimitException, ApiKeyException, RuntimeException, IOException, InterruptedException {
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
        } else if (response.statusCode() == 401) {
            logger.error(API_KEY_ERROR_MESSAGE);
            throw new ApiKeyException(API_KEY_ERROR_MESSAGE);
        } else if (response.statusCode() == 429) {
            logger.error(RATE_LIMIT_ERROR_MESSAGE);
            throw new RateLimitException(RATE_LIMIT_ERROR_MESSAGE);
        } else {
            logger.error(String.format(OTHER_ERROR_MESSAGE, uri, response.statusCode(), response.body()));
            throw new RuntimeException(String.format(OTHER_ERROR_MESSAGE, uri, response.statusCode(), response.body()));
        }
    }
}
