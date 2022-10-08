package commands;

import data.SpaceMarine;
import exeptions.EmptyElement;
import exeptions.IncorrectData;
import utility.CollectionManager;

/**
 * "show" command, show whole elements from collection
 */
public class ShowCommand extends Command {

    private StringBuffer buf = new StringBuffer();

    /**
     * execute command
     * @throws EmptyElement
     * @throws IncorrectData
     * @return
     */

    @Override
    public CommandResult run(CollectionManager collectionManager, Object data, SpaceMarine item) throws EmptyElement, IncorrectData {

        if (collectionManager.getSize() == 0)
            return new CommandResult("show","Коллекция пуста",false);
            for (SpaceMarine spaceMarine : collectionManager.getCollection())
                buf.append(spaceMarine + "\n");
            return new CommandResult("show", buf,true);
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "Выводит информацию о коллекции";
    }
}
