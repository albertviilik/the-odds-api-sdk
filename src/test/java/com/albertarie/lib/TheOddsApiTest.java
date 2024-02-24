package com.albertarie.lib;

import com.albertarie.lib.core.NetworkService;
import com.albertarie.lib.data.Game;
import com.albertarie.lib.data.Sport;
import com.albertarie.lib.url.*;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static com.albertarie.lib.url.Sport.AMERICANFOOTBALL_NFL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * This class contains unit tests for the {@link TheOddsApi} class.
 */
public class TheOddsApiTest {

    private final Gson gson = new Gson();
    @Mock
    private NetworkService mockNetworkService;
    private TheOddsApi theOddsApi;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        String mockApiKey = "test_api_key";
        theOddsApi = new TheOddsApi(mockApiKey, mockNetworkService, gson);
    }

    /**
     * Test case for the {@link TheOddsApi#getSports(Boolean)} method.
     * 
     * Verifies that the method returns the expected list of sports in the current season.
     */
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

    /**
     * Test case for the {@link TheOddsApi#getOdds(com.albertarie.lib.url.Sport, List, List, DateFormat, OddsFormat, List, List, LocalDateTime, LocalDateTime)} method.
     * 
     * Verifies that the method returns the expected list of odds based on the provided parameters.
     * 
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testGetOdds() throws Exception {
        // Define test parameters
        List<Region> regions = List.of(Region.US);
        List<Market> markets = List.of(Market.H2H);
        DateFormat dateFormat = DateFormat.ISO;
        OddsFormat oddsFormat = OddsFormat.DECIMAL;
        List<String> eventIds = List.of("e912304de2b2ce35b473ce2ecd3d1502");
        List<BookMaker> bookmakers = List.of(BookMaker.DRAFTKINGS);
        LocalDateTime commenceTimeFrom = LocalDateTime.parse("2023-10-11T23:10:00");
        LocalDateTime commenceTimeTo = LocalDateTime.parse("2023-10-12T23:10:00");

        // Mock behavior of network service
        String mockResponse = "[{\"id\":\"e912304de2b2ce35b473ce2ecd3d1502\",\"sport_key\":\"americanfootball_nfl\",\"sport_title\":\"NFL\",\"commence_time\":\"2023-10-11T23:10:00Z\",\"home_team\":\"Houston Texans\",\"away_team\":\"Kansas City Chiefs\",\"bookmakers\":[{\"key\":\"draftkings\",\"title\":\"DraftKings\",\"last_update\":\"2023-10-10T12:10:29Z\",\"markets\":[{\"key\":\"h2h\",\"last_update\":\"2023-10-10T12:10:29Z\",\"outcomes\":[{\"name\":\"Houston Texans\",\"price\":2.23},{\"name\":\"Kansas City Chiefs\",\"price\":1.45}]}]}]}]";
        when(mockNetworkService.get(any(), any())).thenReturn(mockResponse);

        // Call the method under test
        List<Game> results = theOddsApi.getOdds(AMERICANFOOTBALL_NFL, regions, markets, dateFormat, oddsFormat, eventIds, bookmakers, commenceTimeFrom, commenceTimeTo);

        // Assert results
        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals("e912304de2b2ce35b473ce2ecd3d1502", results.getFirst().id);
        assertEquals("americanfootball_nfl", results.getFirst().sport_key);
        assertEquals("NFL", results.getFirst().sport_title);
        assertEquals("2023-10-11T23:10:00Z", results.getFirst().commence_time);
        assertEquals("Houston Texans", results.getFirst().home_team);
        assertEquals("Kansas City Chiefs", results.getFirst().away_team);
        assertEquals("draftkings", results.getFirst().bookmakers.getFirst().key);
        assertEquals("DraftKings", results.getFirst().bookmakers.getFirst().title);
        assertEquals("2023-10-10T12:10:29Z", results.getFirst().bookmakers.getFirst().last_update);
        assertEquals("h2h", results.getFirst().bookmakers.getFirst().markets.getFirst().key);
        assertEquals("Houston Texans", results.getFirst().bookmakers.getFirst().markets.getFirst().outcomes.getFirst().name);
        assertEquals(2.23, results.getFirst().bookmakers.getFirst().markets.getFirst().outcomes.getFirst().price);
        assertEquals("Kansas City Chiefs", results.getFirst().bookmakers.getFirst().markets.getFirst().outcomes.get(1).name);
        assertEquals(1.45, results.getFirst().bookmakers.getFirst().markets.getFirst().outcomes.get(1).price);

        // Verify interactions
        verify(mockNetworkService).get(any(), any());
    }
}