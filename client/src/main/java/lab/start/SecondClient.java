import lab.start.Client;

public class SecondClient {
    public static void main(String[] args) {

        try {
            new Client().start();


        } catch (Throwable e) {
            e.printStackTrace(); // never throw
        }

    }
}