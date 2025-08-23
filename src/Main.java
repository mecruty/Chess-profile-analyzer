import main.collection.GamesCollector;

public class Main {
    public static void main(String[] args) throws Exception {
        GamesCollector gc = new GamesCollector("mecruty");
        System.out.println(gc.collectAll().toString());
    }
}
