package main.analyzer;

import java.util.List;
import java.util.Map;

public class GameAnalyzer {
    private List<List<String>> csv;
    private ChartVisualizer vis;

    public GameAnalyzer(List<List<String>> csv) {
        this.csv = csv;
        vis = new ChartVisualizer();
    }

    public Map<String, Map<String, Integer>> analyzeAll() {
        SimpleFrequencyAnalyzer sfa = new SimpleFrequencyAnalyzer(csv);

        Map<String, Map<String, Integer>> result = sfa.analyzeAll();
        createCharts(result);

        return result;
    }

    private void createCharts(Map<String, Map<String, Integer>> result) {
        vis.createPieChart(result.get("rulesFrequency"));
    }
}
