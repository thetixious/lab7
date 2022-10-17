package commands;


import exeptions.IncorrectData;
import exeptions.EmptyElement;

import java.io.Serializable;

/**
 * Abstract class for all commands
 */
public abstract class Command implements Serializable {
    private String name;
    private String description;

    public abstract CommandResult run(CommandEnvironment environment) throws EmptyElement, IncorrectData;

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
