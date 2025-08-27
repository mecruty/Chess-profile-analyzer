package main.analyzer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Essentially the things that could be measured with pie charts
public class SimpleFrequencyAnalyzer {
    List<List<String>> csv;

    public SimpleFrequencyAnalyzer(List<List<String>> csv) {
        this.csv = csv;
    }

    public Map<String, List<Integer>> analyzeAll() {
        Map<String, List<Integer>> analysis = new HashMap<>();
        
        analysis.put("rules", analyzeRules());
        analysis.put("result", analyzeResult());
        analysis.put("result_detailed", analyzeResultDetailed());
        analysis.put("eco", analyzeEco());
        analysis.put("colour", analyzeColour());
        analysis.put("time_control", analyzeTimeControl());
        analysis.put("time_class", analyzeTimeClass());

        return analysis;
    }

    private List<Integer> analyzeRules() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'analyzeRules'");
    }

    private List<Integer> analyzeResult() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'analyzeResult'");
    }

    private List<Integer> analyzeResultDetailed() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'analyzeResultDetailed'");
    }

    private List<Integer> analyzeEco() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'analyzeOpening'");
    }

    private List<Integer> analyzeColour() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'analyzeColour'");
    }

    private List<Integer> analyzeTimeControl() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'analyzeTimeControl'");
    }

    private List<Integer> analyzeTimeClass() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'analyzeTimeClass'");
    }
}
