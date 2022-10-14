package commands;

import data.SpaceMarine;
import exeptions.EmptyElement;
import exeptions.IncorrectData;
import utility.CollectionManager;

public class SignInCommand extends Command {
    @Override
    public CommandResult run(CollectionManager collectionManager, Object data, SpaceMarine item) throws EmptyElement, IncorrectData {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
