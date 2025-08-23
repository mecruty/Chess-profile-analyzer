import java.util.Scanner;

import main.collection.GamesCollector;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Would you like to collect data?");
        if (sc.nextLine() == "y") {
            GamesCollector gc = new GamesCollector(sc.nextLine());
            gc.toCSV(gc.collectAll());
            System.out.println("Done");
        }
    }
}