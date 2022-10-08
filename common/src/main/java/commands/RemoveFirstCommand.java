package commands;

import exeptions.IncorrectData;
import data.SpaceMarine;
import exeptions.EmptyElement;
import utility.CollectionManager;

/**
 * "remove_first" command, remove first element in collection
 */
public class RemoveFirstCommand extends Command {

    /**
     * execute command
     * @throws EmptyElement
     * @throws IncorrectData
     * @return
     */

    @Override
    public CommandResult run(CollectionManager collectionManager, Object data, SpaceMarine item) throws EmptyElement, IncorrectData {
            if (collectionManager.removeFirstElement())
                return new CommandResult("remove_first","Объект удален",true);
            else {
                return new CommandResult("remove_first","Объект не удалось удалить",false);
            }



    }

    @Override
    public String getName() {
        return "remove_first";
    }

    @Override
    public String getDescription() {
        return "Удаляет первый элемент коллекции";
    }
}
