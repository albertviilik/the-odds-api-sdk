package com.albertarie.lib.core;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
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
 * This class contains unit tests for the {@link NetworkService} class.
 */
public class NetworkServiceTest {

    
    /**
        * Test case for the {@link NetworkService#get(URI, Map)} method with URI parameter.
        
        * Verifies that the correct URI is used in the request.
        *
        * @throws Exception if an error occurs during the test
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
     * Test case for the {@link NetworkService#get(URI, Map)} method with URI and headers.
     * 
     * Verifies that the correct URI and headers are used in the request.
     *
     * @throws Exception if an error occurs during the test
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