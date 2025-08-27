package main.analyzer;

import java.util.Map;
import java.util.Map.Entry;

import org.jfree.data.general.DefaultPieDataset;

// Class for creating different kinds of charts
public class ChartVisualizer {
    public ChartVisualizer() {}

    public void createPieChart(Map<String, Integer> values) {
        DefaultPieDataset<String> data = new DefaultPieDataset<>();

        // Find total sum of values to calculate percentage
        double total = 0;
        for (int value : values.values()) {
            total += value;
        }

        for (Entry<String, Integer> value : values.entrySet()) {
            data.setValue(value.getKey(), value.getValue() / total);
        }
    }
}
