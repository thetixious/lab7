package lab.start;

import exeptions.EmptyElement;
import exeptions.IncorrectData;

/**
 * Abstract form for application's behavior
 */
public abstract class AbstractClient {
    private static boolean state = false;

    public abstract void run() throws EmptyElement, IncorrectData;

    public void onStart() {

    }

    public final void start() throws EmptyElement, IncorrectData {
        state = true;
        onStart();
        while (state) {
            run();
        }

    }

    public static void onStop() {
        state = false;

    }


}
