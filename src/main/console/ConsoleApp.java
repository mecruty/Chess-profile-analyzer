package main.console;

import java.util.List;
import java.util.Scanner;

import main.collection.CSVParser;
import main.collection.GamesCollector;

public class ConsoleApp {
    private String username;
    private GamesCollector gc;
    private CSVParser csvp;
    
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
            List<List<String>> csv = csvp.loadCSV();

        }
    }
}
