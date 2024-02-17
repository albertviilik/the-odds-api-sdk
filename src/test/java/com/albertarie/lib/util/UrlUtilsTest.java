package com.albertarie.lib.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class UrlUtilsTest {
    /**
     * This class is for testing the UrlUtils class and its method buildUrl.
     * The buildUrl method is used for building a URL by appending the endpoint and parameters to the base URL.
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
}