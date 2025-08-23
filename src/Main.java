import java.util.List;
import java.util.Scanner;

import main.collection.CSVParser;
import main.collection.GamesCollector;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String username;
        GamesCollector gc;
        CSVParser csvp;

        System.out.println("Enter username:");
        username = sc.nextLine();
        gc = new GamesCollector(username);
        csvp = new CSVParser(username);

        System.out.println("Would you like to collect data?");
        if (sc.nextLine().equals("y")) {
            csvp.toCSV(gc.collectAll());
            System.out.println("Done");
        }

        System.out.println("Would you like to load data?");
        if (sc.nextLine().equals("y")) {
            List<List<String>> temp = csvp.loadCSV();
            System.out.println("hi");
        }
    }
}