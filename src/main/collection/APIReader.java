package main.collection;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;

public class APIReader {
    private URL url;

    public APIReader(String dest) {
        try {
            url = new URI(dest).toURL();
        } catch (Exception e) {
            throw new RuntimeException("APIReader constructor failed");
        }
    }

    public JSONObject read() {
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // Getting the response code
            int responsecode = conn.getResponseCode();
            // Check if successful
            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            }

            // Collect lines with Scanner
            String inline = "";
            Scanner scanner = new Scanner(url.openStream());

            // Write all the JSON data into a string using Scanner
            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }

            // Close the scanner
            scanner.close();

            // Converts string to json
            JSONObject json = new JSONObject(inline);

            return json;
            
        } catch (Exception e) {
            throw new RuntimeException("APIReader read failed");
        }
    }
}
