# TheOddsApi SDK

TheOddsApi SDK is a Java library that allows developers to interact with The Odds API offered on [TheOddsApi](https://the-odds-api.com). It provides convenient methods to retrieve sports odds data for various events.

## Installation

To use TheOddsApi SDK in your Java project, you can add the following dependency to your `pom.xml` file:

```xml
<dependency>
  <groupId>com.albertarie.lib</groupId>
  <artifactId>the-odds-api-sdk</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

## Usage

To use TheOddsApi SDK, you need to have an API key from [TheOddsApi](https://the-odds-api.com). You can sign up for a free account to get an API key.

Here's an example of how to use TheOddsApi SDK to retrieve available sports:

```java
package com.example;

import com.albertarie.lib.TheOddsApi;
import com.albertarie.lib.data.Sport;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TheOddsApi theOddsApi = new TheOddsApi("d00d974e5c129ded425d81328aab35c1");

        List<Sport> sports = new ArrayList<>();

        try {
            sports = theOddsApi.getSports(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Sport sport : sports) {
            System.out.println(sport.key + " - " + sport.title + " - " + sport.description);
        }
    }
}
```

Here's an example of how to use TheOddsApi SDK to retrieve available odds for a specific sport:

```java
List<Game> games = new ArrayList<>();

try {
    games = theOddsApi.getOdds(
            SOCCER_EPL,
            List.of(UK),
            List.of(H2H),
            null,
            null,
            null,
            null,
            null,
            null
    );
} catch (Exception e) {
    e.printStackTrace();
}

for (Game game : games) {
    System.out.println(game.id);
    for (Bookmaker bookmaker : game.bookmakers) {
        System.out.println(bookmaker.key);
        for (com.albertarie.lib.data.Market market : bookmaker.markets) {
            System.out.println(market.key);
            for (com.albertarie.lib.data.Outcome outcome : market.outcomes) {
                System.out.println(outcome.name);
                System.out.println(outcome.price);
            }
        }
    }
}
```

## License

[The Odds Api SDK © 2024 by Albert Viilik, Arie Arya is licensed under CC BY-NC-SA 4.0](https://creativecommons.org/licenses/by-nc-sa/4.0/)
