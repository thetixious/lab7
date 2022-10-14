package lab.start;

import java.sql.Connection;
import java.sql.DriverManager;

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

