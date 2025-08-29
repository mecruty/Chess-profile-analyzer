package main.analyzer;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.jfree.chart.JFreeChart;

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

    public Map<String, Map<String, Integer>> analyzeAll() {
        SimpleFrequencyAnalyzer sfa = new SimpleFrequencyAnalyzer(csv);

        Map<String, Map<String, Integer>> result = sfa.analyzeAll();
        createAllCharts(result);

        System.out.println("Data analyzed!");

        return result;
    }

    private void createAllCharts(Map<String, Map<String, Integer>> result) {
        for (String key : result.keySet()) {
            JFreeChart chart = vis.createPieChart(key, result.get(key));
            vis.saveChart(key, chart);
            vis.displayChart(chart);
        }
    }
}
