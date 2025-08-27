package main.console;

import java.util.Scanner;

import org.json.JSONObject;

import main.analyzer.GameAnalyzer;
import main.collection.CSVParser;
import main.collection.GamesCollector;

public class ConsoleApp {
    private String username;
    private GamesCollector gc;
    private CSVParser csvp;
    private GameAnalyzer ga;
    
    static Scanner sc = new Scanner(System.in);

    public ConsoleApp() {
        run();
    }

    private void run() {
        System.out.println("Enter username:");
        username = sc.nextLine();
        gc = new GamesCollector(username);
        csvp = new CSVParser(username);

        System.out.println("Would you like to collect data?");
        if (sc.nextLine().equals("y")) {
            csvp.saveJSONToCSV(gc.collectAll());
            System.out.println("Done");
        }

        System.out.println("Would you like to load data?");
        if (sc.nextLine().equals("y")) {
            // TODO temporary
            ga = new GameAnalyzer(csvp.loadCSV());
            csvp = new CSVParser("test save data");
            csvp.saveJSONToCSV(new JSONObject(ga.analyzeAll()));;
        }
    }
}
