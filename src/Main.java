import main.collection.GamesCollector;

public class Main {
    public static void main(String[] args) {
        GamesCollector gc = new GamesCollector("mecruty");
        gc.toCSV(gc.collectAll());
        System.out.println("Done");
    }
}
