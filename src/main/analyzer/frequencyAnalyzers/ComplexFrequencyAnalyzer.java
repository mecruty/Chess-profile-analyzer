package main.analyzer.frequencyAnalyzers;

import java.util.List;
import java.util.Map;

// Assuming a filter by this, what is the distribution of something else (pie chart)
public class ComplexFrequencyAnalyzer extends FrequencyAnalyzer {
    public ComplexFrequencyAnalyzer(List<List<String>> csv) {
        super(csv);
    }

    public Map<String, Map<String, Integer>> analyze(String filterKey, String filterValue, String dataKey) {
        List<String> filterData = getColumn(filterKey);
        List<String> data = getColumn(dataKey);

        for (int i = 0; i < filterData.size(); i++) {
            if (!filterData.get(i).equals(filterValue)) {
                filterData.remove(i);
                data.remove(i);
                i--;
            }
        }

        return Map.of(dataKey + "-FilteredBy-" + filterValue + "-For-" + filterKey, countFrequency(data));
    }
}
