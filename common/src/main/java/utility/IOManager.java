package utility;

import java.io.*;


/**
 * Class for work with input and output
 */
public class IOManager {
    private PrintWriter writer;


    public IOManager(PrintWriter writer) {
        this.writer = writer;
    }

    public IOManager() {

    }


    /**
     * Print regular messages to console
     * @param o
     */
    public void println(Object o) {
        writer.println(o);
    }

    /**
     * Print error's messages to console
     * @param o
     */
    public void printerr(Object o) {
        writer.println("ERROR! " + o);
    }

    /**
     * Read file
     * @param file
     * @return file content in string format
     * @throws IOException
     */
    public String readFile(File file) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line;
        if (!file.exists()) {
            throw new FileNotFoundException();
        } else {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        }
    }
}
