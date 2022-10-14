package commands;

import data.SpaceMarine;
import exeptions.EmptyElement;
import exeptions.IncorrectData;
import utility.CollectionManager;
import utility.IOManager;

public class LogInCommand extends Command{

    @Override
    public CommandResult run(CollectionManager collectionManager, Object data, SpaceMarine item) throws EmptyElement, IncorrectData {
return null;
    }

    @Override
    public String getName() {
        return "log_in";
    }

    @Override
    public String getDescription() {
        return "";
    }
}
