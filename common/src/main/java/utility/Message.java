package utility;

import commands.Command;
import data.SpaceMarine;
import exeptions.EmptyElement;
import exeptions.IncorrectData;

import java.io.Serializable;

public class Message implements Serializable {
    private Command command;
   // private Object data;
    private Object data;
    private SpaceMarine spaceMarine;

    public void loadPreMessage(Command command, SpaceMarineArgumentLoader argument) throws EmptyElement, IncorrectData {
        this.command = command;
        data = argument.getStrArguments();
        if (command.getName().equals("add") || command.getName().equals("add_if_max") || command.getName().equals("update")) {
            spaceMarine = argument.loadSpaceMarin();
        }

    }

    public Command getCommand() {
        return command;
    }

    public Object getData() {
        return data;
    }

    public SpaceMarine getSpaceMarine() {
        return spaceMarine;
    }
}
