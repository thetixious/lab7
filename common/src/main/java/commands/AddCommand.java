package commands;

import data.SpaceMarine;
import exeptions.IncorrectData;
import exeptions.EmptyElement;
import utility.CollectionManager;

import java.util.Date;

/**
 * "add" command, add SpaceMarine instance in collection
 */
public class AddCommand extends Command {



    /**
     * execute command
     * @return
     */
    //объект создается
    public CommandResult run(CollectionManager collectionManager, Object data, SpaceMarine item) throws EmptyElement, IncorrectData {
            Date date = new Date();
            item.setCreationDate(date);
            if(collectionManager.addMarine(item)) {
                return new CommandResult("add", "Элемент добавлен", true);
            }
            else
                return new CommandResult("add", "Элемент не добавлен", false);
    }

    @Override
    public String getDescription() {
        return "Добавляет новый объект класса SpaceMarine в коллекцию";
    }

    public String getName() {
        return "add";
    }
}
