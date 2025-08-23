package main.collection;

import org.json.JSONArray;
import org.json.JSONObject;

// IMPORTANT: DOES NOT COLLECT PGNs OR TCNs
public class GamesCollector {
    private final String username;
    private final APIReader monthApiReader;

    public GamesCollector(String username) {
        this.username = username;
        monthApiReader = new APIReader("https://api.chess.com/pub/player/" + username + "/games/archives");
    }

    public JSONObject collectAll() {
        // Gets all months
        JSONArray months = monthApiReader.read().getJSONArray("archives");

        JSONObject json = new JSONObject();
        json.put("games", new JSONArray());
        JSONArray games = json.getJSONArray("games");
        int gameCounter = 0;
        int monthCounter = 0;

        // Collect all data from months
        for (Object month : months) {
            String url = (String) month;
            JSONArray gamesMonth = new APIReader(url).read().getJSONArray("games");
            gameCounter += gamesMonth.length();
            monthCounter++;

            // Removes pgn and adds to json
            for (Object game : gamesMonth) {
                JSONObject gameJson = (JSONObject) game;
                gameJson.remove("pgn");
                gameJson.remove("tcn");
                gameJson.remove("fen");
                gameJson.remove("uuid");

                // Separates the white and black JSONObjects to individual tabs
                JSONObject white = gameJson.getJSONObject("white");
                JSONObject black = gameJson.getJSONObject("black");
                gameJson.remove("white");
                gameJson.remove("black");
                if (white.getString("username").equals(username)) {
                    addResultJSON(gameJson, white, black);
                    gameJson.put("colour", "white");
                } else {
                    addResultJSON(gameJson, black, white);
                    gameJson.put("colour", "black");
                }

                games.put(gameJson);
            }

            System.out.print("Loaded: " + gameCounter + " games.");
            System.out.println(" (" + monthCounter + "/" + months.length() + " months)");
        }

        return json;
    }

    private void addResultJSON(JSONObject gameJson, JSONObject own, JSONObject opp) {
        gameJson.put("result", own.getString("result"));
        gameJson.put("opponent_result", opp.getString("result"));
        gameJson.put("rating", own.getInt("rating"));
        gameJson.put("opponent_rating", opp.getInt("rating"));
        gameJson.put("opponent_username", opp.getString("username"));
    }

    public String getUsername() {
        return username;
    }
}