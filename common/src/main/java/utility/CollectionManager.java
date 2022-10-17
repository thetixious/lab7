package utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import exeptions.IncorrectData;
import commands.Command;
import exeptions.EmptyElement;
import data.Chapter;
import data.SpaceMarine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Class for work with collection
 */
public class CollectionManager {

    private static Stack<SpaceMarine> collection;
    private final LocalDateTime localDateTime;
    private final TreeSet<Long> id;
    private final HashSet<Double> healthCollection = new HashSet<>();
    private CommandPool commandPool = null;
    private CollectionSerializer serializer;
    SQLCollectionManager sqlCollectionManager;
    Connection connection;




/*
    public CollectionManager(CommandPool commandPool, CollectionSerializer serializer, Stack<SpaceMarine> collection) {
        this.serializer = serializer;
        this.collection = collection;
        localDateTime = LocalDateTime.now();
        id = new TreeSet<>();
        this.commandPool = commandPool;
    }
*/
    public CollectionManager(CommandPool commandPool) {
        this.commandPool = commandPool;
        collection = new Stack<>();
        localDateTime = LocalDateTime.now();
        id = new TreeSet<>();
    }

    public CollectionManager() {
        collection = new Stack<>();
        localDateTime = LocalDateTime.now();
        id = new TreeSet<>();
    }



    /**
     * Load collection
     *
     * @param collection
     */

    public void loadCollection(Stack<SpaceMarine> collection) {
        this.collection = collection;
    }

    /**
     * Add collection instance to collection
     *
     * @return
     * @throws IncorrectData
     */
    public HashMap<String, Command> getCommands() {
        return commandPool.getCommands();

    }

    public boolean addMarine(SpaceMarine spaceMarine) throws IncorrectData {
        if (Objects.equals(spaceMarine.getId(), null)) {
            if (id.isEmpty()) {
                spaceMarine.setId(1L);
                id.add(1l);
            } else {
                spaceMarine.setId(id.last() + 1);
                id.add(id.last() + 1);
            }
        } else if (id.contains(spaceMarine.getId())) {
            spaceMarine.setId(id.last() + 1);
            id.add(id.last() + 1);
        } else {
            id.add(spaceMarine.getId());
        }
        collection.push(spaceMarine);
        sortCollection();
        return true;
    }

    /**
     * @return localDataTime
     */
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    /**
     * @return Sorted collection
     */
    private Stack<SpaceMarine> sortCollection() {
        Collections.sort(collection);
        return collection;
    }

    /**
     * Return collection size
     *
     * @return Integer
     */
    public Integer getSize() {
        return collection.size();
    }

    /**
     * @return collection
     */
    public Stack<SpaceMarine> getCollection() {
        return collection;
    }

    /**
     * Add candidate to collection if it is bigger than max element in collection
     *
     * @param candidate
     * @throws IncorrectData
     */
    public boolean AllowAddIfMax(SpaceMarine candidate) throws IncorrectData {
        if (collection.isEmpty()) {
            addMarine(candidate);
            return true;
        }
        SpaceMarine maxMarine = collection.stream().sorted().findFirst().get();
        if (maxMarine.compareTo(candidate) < 0) {
            addMarine(candidate);
            return true;
        } else
            return false;

    }

    /**
     * Calculation average health point in collection
     * return double
     */
    public double getAverageHealth() {
        if (collection.isEmpty())
            return 0;

        return collection.stream().mapToDouble(s -> s.getHealth()).average().getAsDouble();
    }

    /**
     * Clear whole collection
     */
    public void clearCollection() {
        id.clear();
        collection.clear();
    }

    /**
     * Delete element with the give id
     *
     * @param id
     */
    public boolean deleteElementById(Long id) {

        Iterator<SpaceMarine> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Long curId = iterator.next().getId();
            if (curId == id) {
                this.id.remove(id);
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Remove first element in collection
     */
    public boolean removeFirstElement() {
        if (collection.isEmpty())
            return false;
        SpaceMarine firstMarine = collection.stream().sorted().findFirst().get();
        collection.remove(firstMarine);
        id.remove(firstMarine.getId());
        return true;
    }

    /**
     * Find uniq health value
     *
     * @return Set of health value which exist in collection
     * @throws IncorrectData
     */
    public Set<Double> getUniqueHealth() {

        return collection.stream().map(s -> s.getHealth()).collect(Collectors.toSet());

    }

    /**
     * Remove lower element in collection
     *
     * @throws IncorrectData
     * @throws EmptyElement
     */
    public boolean removeLower(SpaceMarine spaceMarine) throws IncorrectData, EmptyElement {

        addMarine(spaceMarine);

        if (collection.isEmpty())
            return false;
        else {
            Double reference = spaceMarine.getHealth();
            Iterator<SpaceMarine> iterator = collection.iterator();
            while (iterator.hasNext()) {
                SpaceMarine item = iterator.next();
                if (item.getHealth() < reference) {
                    iterator.remove();
                    id.remove(item.getId());

                }
            }
            return true;
        }

    }

    /**
     * @return Map with health value and Chapter instance
     * @throws EmptyElement
     */
    public List<Chapter> printChapterFields() throws EmptyElement {
        if (collection.isEmpty()) throw new EmptyElement();

        return collection.stream().sorted((Comparator.reverseOrder())).map(s -> s.getChapter()).collect(Collectors.toList());


    }

    /**
     * Find element by id
     *
     * @param id
     * @return
     */
    public SpaceMarine findElementById(Long id) {
        return collection.stream().filter((spMar) -> id.equals(spMar.getId())).findFirst().orElse(null);
    }


    /**
     * Load set id in collection which load from file
     */
    public void startSetId() {
        if (!(Objects.equals(collection, null))) {


            for (SpaceMarine spaceMarine : collection) {
                id.add(spaceMarine.getId());
            }
        }
    }

    public void saveCollection() throws JsonProcessingException {
        serializer.collectionSerializer(collection);
    }


}
