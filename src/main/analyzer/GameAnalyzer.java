package main.analyzer;

import java.util.List;
import java.util.Map;

public class GameAnalyzer {
    private List<List<String>> csv;

    public GameAnalyzer(List<List<String>> csv) {
        this.csv = csv;
    }

    public Map<String, Map<String, Integer>> analyzeAll() {
        SimpleFrequencyAnalyzer sfa = new SimpleFrequencyAnalyzer(csv);
        return sfa.analyzeAll();
    }
}
