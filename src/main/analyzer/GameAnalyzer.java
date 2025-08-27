package main.analyzer;

import java.util.List;

public class GameAnalyzer {
    List<List<String>> csv;

    public GameAnalyzer(List<List<String>> csv) {
        this.csv = csv;
    }

    public void analyzeAll() {
        SimpleFrequencyAnalyzer sfa = new SimpleFrequencyAnalyzer(csv);
        sfa.analyzeAll();
    }
}
