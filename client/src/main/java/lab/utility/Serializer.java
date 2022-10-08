package lab.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.SpaceMarine;
import utility.IOManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

/**
 * Class serialize and deserialize instances
 */
public class Serializer {
    private final utility.IOManager ioManager;

    private File file;
    ObjectMapper mapper = new ObjectMapper();

    public Serializer(IOManager ioManager, File file) {
        this.ioManager = ioManager;
        this.file = file;
    }

    /**
     * Deserializer collection from file
     *
     * @return collection
     * @throws IOException
     */
    public Stack<SpaceMarine> collectionDeserializer() throws IOException {
        String input = ioManager.readFile(file);
        if (input.equals(null))
            return null;
        else

            return mapper.readValue(input, new TypeReference<Stack<SpaceMarine>>() {
            });
    }

    /**
     * Serialize collection for saving in file
     *
     * @param collection
     * @throws JsonProcessingException
     */
    public void collectionSerializer(Stack<SpaceMarine> collection) throws JsonProcessingException {
        if (collection.isEmpty()) {
            ioManager.println("Коллекция пуста");
        } else {

            String jsonCollection = mapper.writeValueAsString(collection);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(jsonCollection);
                ioManager.println("Коллекция сохранена");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
