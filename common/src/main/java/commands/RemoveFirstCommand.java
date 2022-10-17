package commands;

import exeptions.IncorrectData;
import exeptions.EmptyElement;

/**
 * "remove_first" command, remove first element in collection
 */
public class RemoveFirstCommand extends Command {

    /**
     * execute command
     * @throws EmptyElement
     * @throws IncorrectData
     * @return
     * @param environment
     */

    @Override
    public CommandResult run(CommandEnvironment environment) throws EmptyElement, IncorrectData {
            if (environment.getCollectionManager().removeFirstElement())
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
