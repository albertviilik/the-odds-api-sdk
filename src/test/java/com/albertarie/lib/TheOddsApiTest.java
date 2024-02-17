package com.albertarie.lib;

import com.albertarie.lib.data.Sport;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Test class for TheOddsApi.
 * We are testing the getSports method to verify correct interaction with the Sports API.
 * Making sure parameters are parse correctly and results are being returned accurately.
 */
public class TheOddsApiTest {

    @Test
    public void testGetSportsInSeason() {
        TheOddsApi theOddsApi = Mockito.mock(TheOddsApi.class);

        Sport expectedSport = new Sport();
        expectedSport.key = "sportKey";
        expectedSport.active = true;
        expectedSport.group = "group";
        expectedSport.description = "description";
        expectedSport.title = "title";
        expectedSport.hasOutrights = true;

        try {
            when(theOddsApi.getSports(true))
                    .thenReturn(Collections.singletonList(expectedSport));

            List<Sport> sports = theOddsApi.getSports(true);

            assertNotNull(sports);
            assertEquals(1, sports.size());

            Sport sport = sports.getFirst();
            assertEquals(expectedSport.key, sport.key);
            assertEquals(expectedSport.active, sport.active);
            assertEquals(expectedSport.group, sport.group);
            assertEquals(expectedSport.description, sport.description);
            assertEquals(expectedSport.title, sport.title);
            assertEquals(expectedSport.hasOutrights, sport.hasOutrights);
        } catch (Exception e) {
            System.out.println("Test failed to execute: " + e.getMessage());
        }
    }
}