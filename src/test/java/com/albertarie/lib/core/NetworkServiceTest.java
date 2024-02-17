package com.albertarie.lib.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * The NetworkServiceTest class contains tests for the NetworkService class.
 */
public class NetworkServiceTest {

    /**
     * This test validates that the NetworkService#GET method sends a request to the correct URI.
     */
    @Test
    public void testNetworkServiceGet_URI() throws Exception {
        // Arrange
        URI uri = new URI("https://test.com");
        Map<String, String> headers = new HashMap<>();

        HttpClient mockClient = Mockito.mock(HttpClient.class);
        HttpResponse<String> httpResponse = Mockito.mock(HttpResponse.class);

        Mockito.when(mockClient.send(Mockito.any(HttpRequest.class), Mockito.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
        Mockito.when(httpResponse.statusCode()).thenReturn(200);
        Mockito.when(httpResponse.body()).thenReturn("");

        Supplier<HttpClient> clientSupplier = () -> mockClient;
        NetworkService ns = new NetworkService(clientSupplier);

        ArgumentCaptor<HttpRequest> requestCaptor = ArgumentCaptor.forClass(HttpRequest.class);

        // Act
        ns.get(uri, headers);

        // Assert
        Mockito.verify(mockClient).send(requestCaptor.capture(), Mockito.<HttpResponse.BodyHandler<String>>any());
        HttpRequest actualRequest = requestCaptor.getValue();
        Assertions.assertEquals(uri, actualRequest.uri());
    }

    /**
     * This test validates that the NetworkService#GET method sends a request to the correct URI with the correct headers.
     */
    @Test
    public void testNetworkServiceGet_URI_WithHeaders() throws Exception {
        // Arrange
        URI uri = new URI("https://test.com");
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");

        HttpClient mockClient = Mockito.mock(HttpClient.class);
        HttpResponse<String> httpResponse = Mockito.mock(HttpResponse.class);

        Mockito.when(mockClient.send(Mockito.any(HttpRequest.class), Mockito.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
        Mockito.when(httpResponse.statusCode()).thenReturn(200);
        Mockito.when(httpResponse.body()).thenReturn("");

        Supplier<HttpClient> clientSupplier = () -> mockClient;
        NetworkService ns = new NetworkService(clientSupplier);

        ArgumentCaptor<HttpRequest> requestCaptor = ArgumentCaptor.forClass(HttpRequest.class);

        // Act
        ns.get(uri, headers);

        // Assert
        Mockito.verify(mockClient).send(requestCaptor.capture(), Mockito.<HttpResponse.BodyHandler<String>>any());
        HttpRequest actualRequest = requestCaptor.getValue();
        Assertions.assertTrue(actualRequest.headers().map().containsKey("Accept"));
        Assertions.assertTrue(actualRequest.headers().map().get("Accept").contains("application/json"));
    }
}