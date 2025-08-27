package main.analyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Essentially the things that could be measured with pie charts
public class SimpleFrequencyAnalyzer {
    private List<List<String>> csv;

    public SimpleFrequencyAnalyzer(List<List<String>> csv) {
        this.csv = csv;
    }

    public Map<String, Map<String, Integer>> analyzeAll() {
        Map<String, Map<String, Integer>> analysis = new HashMap<>();

        analysis.put("rulesFrequency", analyzeRules());
        analysis.put("resultFrequency", analyzeResult());
        analysis.put("resultDetailedFrequency", analyzeResultDetailed());
        analysis.put("ecoFrequency", analyzeEco());
        analysis.put("colourFrequency", analyzeColour());
        analysis.put("timeControlFrequency", analyzeTimeControl());
        analysis.put("timeClassFrequency", analyzeTimeClass());

        return analysis;
    }

    public Map<String, Integer> analyzeRules() {
        // Collect values
        int index = csv.get(0).indexOf("rules");
        List<String> values = new ArrayList<>();
        // Has to be index + 1 as 0 is the column names
        csv.forEach((game) -> values.add(game.get(index + 1)));
        
        return countFrequency(values);
    }

    public Map<String, Integer> analyzeResult() {
        // Collect values
        int index = csv.get(0).indexOf("result");
        // Has to be index + 1 as 0 is the column names
        List<String> values = csv.get(index + 1);
        
        return countFrequency(values);
    }

    public Map<String, Integer> analyzeResultDetailed() {
        // Collect values
        int index = csv.get(0).indexOf("result");
        // Has to be index + 1 as 0 is the column names
        List<String> values = csv.get(index + 1);

        // Values for the opponent
        index = csv.get(0).indexOf("opponent_result");
        List<String> opponentValues = csv.get(index + 1);

        // Adds the manner in which the opponent lost (opponent_result) if result is win
        for (int i = 0; i < values.size(); i++) {
            String value = values.get(i);

            if (value.equals("win")) {
                value += "(" + opponentValues.get(i) + ")";
            }
        }
        
        return countFrequency(values);
    }

    public Map<String, Integer> analyzeEco() {
        // Collect values
        int index = csv.get(0).indexOf("eco");
        // Has to be index + 1 as 0 is the column names
        List<String> values = csv.get(index + 1);

        // Removes the "https://www.chess.com/openings/" from each opening
        // Unless there was no opening
        int indexToRemove = "https://www.chess.com/openings/".length();
        for (String value : values) {
            if (!value.equals("none")) {
                value.substring(indexToRemove);
            }
        }
        
        return countFrequency(values);
    }

    public Map<String, Integer> analyzeColour() {
        // Collect values
        int index = csv.get(0).indexOf("colour");
        // Has to be index + 1 as 0 is the column names
        List<String> values = csv.get(index + 1);
        
        return countFrequency(values);
    }

    public Map<String, Integer> analyzeTimeControl() {
        // Collect values
        int index = csv.get(0).indexOf("time_control");
        // Has to be index + 1 as 0 is the column names
        List<String> values = csv.get(index + 1);
        
        return countFrequency(values);
    }

    public Map<String, Integer> analyzeTimeClass() {
        // Collect values
        int index = csv.get(0).indexOf("time_class");
        // Has to be index + 1 as 0 is the column names
        List<String> values = csv.get(index + 1);
        
        return countFrequency(values);
    }

    

    private Map<String, Integer> countFrequency(List<String> values) {
        Map<String, Integer> analysis = new HashMap<String,Integer>();
        for (String value : values) {
            analysis.putIfAbsent(value, 0);
            analysis.compute(value, (k, v) -> v++);
        }

        return analysis;
    }
}
