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
    public abstract String getName();

    /**
     * @return command description
     */
    public abstract String getDescription();

    @Override
    public String toString() {
        return name + " --- " + description;


    }
}
