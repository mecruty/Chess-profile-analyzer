package main.analyzer;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.jfree.chart.JFreeChart;

import main.analyzer.frequencyAnalyzers.ComplexFrequencyAnalyzer;
import main.analyzer.frequencyAnalyzers.FrequencyAnalyzer;
import main.analyzer.frequencyAnalyzers.SimpleFrequencyAnalyzer;

public class GameAnalyzer {
    private String username;
    private List<List<String>> csv;
    private ChartVisualizer vis;

    public GameAnalyzer(String username, List<List<String>> csv) {
        this.csv = csv;
        this.username = username;
        vis = new ChartVisualizer(this.username);

        // Creates the directory for the general visualization
        File dir = new File("./data/" + username + "/visualization");
        if (dir != null && !dir.exists()) {
            dir.mkdir();
        }
    }

    public void analyzeAll() {
        analyzeAllSimpleFrequency();

        System.out.println("Data analyzed!");
    }

    private void createFrequencyCharts(Map<String, Map<String, Integer>> result) {
        for (String key : result.keySet()) {
            JFreeChart chart = vis.createPieChart(key, result.get(key));
            vis.saveChart(key, chart);
            vis.displayChart(chart);
        }
    }

    public void analyzeAllSimpleFrequency() {
        SimpleFrequencyAnalyzer sfa = new SimpleFrequencyAnalyzer(csv);

        Map<String, Map<String, Integer>> result = sfa.analyzeAll();

        // Removing the functionality for opening as too complicated.
        result.remove("eco");

        // Creating all charts
        createFrequencyCharts(result);
    }

    public void analyzeComplexFrequency(String filter, String data) {
        ComplexFrequencyAnalyzer cfa = new ComplexFrequencyAnalyzer(csv);
        cfa.analyze(filter, data);
    }
}
