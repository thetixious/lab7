package commands;

import exeptions.IncorrectData;
import exeptions.EmptyElement;
import data.SpaceMarine;
import utility.CollectionManager;

/**
 * "add_if_max" command, add item in collection if item bigger than max element in collection
 */
public class AddIfMaxCommand extends Command {
    /**
     * execute command
     * @param arguments
     * @throws IncorrectData
     * @throws EmptyElement
     * @return
     */
    @Override
    public CommandResult run(CollectionManager collectionManager, Object data, SpaceMarine candidate) throws IncorrectData, EmptyElement {


            if (collectionManager.AllowAddIfMax(candidate))
                return new CommandResult("add_if_max", "Объект добавлен в коллекцию", true);
            else
                return new CommandResult("add_if_max", "Объект не добавлен в коллекцию", false);

    }

    @Override
    public String getDescription() {
        return "Добавляет объект в коллекцию если он больше максимального";
    }

    @Override
    public String getName() {
        return "add_if_max";
    }
}
