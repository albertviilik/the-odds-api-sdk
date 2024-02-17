package com.albertarie.lib.util;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * This class contains unit tests for the {@link UrlUtils} class.
 */
public class UrlUtilsTest {

    /**
     * Test case for the {@link UrlUtils#buildUrl(String, String, Map)} method.
     * 
     * Verifies that the constructed URL is correct based on the provided base URL, endpoint, and parameters.
     */
    @Test
    public void testBuildUrl() {
        // Initialize necessary variables
        String baseUrl = "https://api.website.com/";
        String endpoint = "data";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("key", "value");
        parameters.put("key2", "value2");

        // call the buildUrl method
        String result = UrlUtils.buildUrl(baseUrl, endpoint, parameters);

        // The expected result
        String expected = "https://api.website.com/data?key2=value2&key=value";

        // Check that the constructed url is correct
        Assertions.assertEquals(expected, result);
    }

    /**
     * Test case for the {@link UrlUtils#formatDateTime(LocalDateTime)} method.
     * 
     * Verifies that the method correctly formats a LocalDateTime object into a string representation.
     */
    @Test
    public void testFormatDateTime() {
        // create a LocalDateTime object
        LocalDateTime dateTime = LocalDateTime.of(2022, 1, 1, 10, 15, 30);

        // call the formatDateTime method
        String result = UrlUtils.formatDateTime(dateTime);

        // The expected result
        String expected = "2022-01-01T10:15:30Z";

        // Check the formatted date time is correct
        Assertions.assertEquals(expected, result);
    }
}