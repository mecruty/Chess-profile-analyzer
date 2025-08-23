package main.collection;

import org.json.JSONArray;
import org.json.JSONObject;

// IMPORTANT: DOES NOT COLLECT PGNs
public class GamesCollector {
    String username;
    APIReader apiReader;

    public GamesCollector(String username) {
        this.username = username;
        apiReader = new APIReader("https://api.chess.com/pub/player/" + username + "/games/archives");
    }

    public JSONObject collectAll() {
        // Gets all months
        JSONArray months = apiReader.read().getJSONArray("archives");
        
        JSONObject json = new JSONObject();
        json.put("games", new JSONArray());
        JSONArray games = json.getJSONArray("games");
        
        // Collect all data from months
        for (Object month : months) {
            String url = (String) month;
            JSONArray gamesMonth = new APIReader(url).read().getJSONArray("games");

            // Removes pgn and adds to json
            for (Object game : gamesMonth) {
                JSONObject gameJson = (JSONObject) game;
                gameJson.remove("pgn");
                games.put(gameJson);
            }
        }

        return json;
    }
}