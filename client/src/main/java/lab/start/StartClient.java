package lab.start;

/**
 * Start class
 */

public class StartClient {
    public static void main(String[] args) {

        try {
            new Client().start();


        } catch (Throwable e) {
            e.printStackTrace(); // never throw
        }

    }
}

