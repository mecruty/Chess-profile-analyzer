package main.collection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;

// IMPORTANT: DOES NOT COLLECT PGNs OR TCNs
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
                gameJson.remove("tcn")
                games.put(gameJson);
            }

            System.out.print("Loaded: " + gameCounter + " games.");
            System.out.println(" (" + monthCounter + "/" + months.length() + " months)");
        }

        return json;
    }

    public void toCSV(JSONObject json) {
        JSONArray docs = json.getJSONArray("games");

        File file = new File("./data/" + username + ".csv");
        String csv = CDL.toString(docs);

        try {
            PrintWriter writer = new PrintWriter(file);
            writer.print(csv);
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Writing to csv failed");
        }
    }
}