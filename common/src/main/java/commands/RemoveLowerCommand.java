package commands;

import data.SpaceMarine;
import exeptions.IncorrectData;
import exeptions.EmptyElement;
import utility.CollectionManager;

/**
 * "remove_lower" command, remove elements which lower than current
 */
public class RemoveLowerCommand extends Command {




    @Override
    public CommandResult run(CollectionManager collectionManager, Object data, SpaceMarine item) throws EmptyElement, IncorrectData {

            if (collectionManager.removeLower(item))
                return new CommandResult("remove_lower","Объект удален",true);
            else
                return new CommandResult("remove_lower","Коллекция пуста",false);

    }
    @Override
    public String getName() {
        return "remove_lower";
    }

    @Override
    public String getDescription() {
        return "Удаляет все элементы коллекции меньше чем введенный";
    }
}
