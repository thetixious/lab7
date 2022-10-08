package commands;


import data.SpaceMarine;
import exeptions.IncorrectData;
import exeptions.EmptyElement;
import utility.CollectionManager;

import java.io.Serializable;

/**
 * Abstract class for all commands
 */
public abstract class Command implements Serializable {
    private String name;
    private String description;

    public abstract CommandResult run(CollectionManager collectionManager, Object data, SpaceMarine item) throws EmptyElement, IncorrectData;

    /**
     * @return set command name
     */
    public String getName() {
        return name;
    }

    /**
     * @return command description
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name + " --- " + description;


    }
}
