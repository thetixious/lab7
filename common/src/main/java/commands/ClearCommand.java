package commands;

import exeptions.IncorrectData;
import exeptions.EmptyElement;

/**
 * "clear" command, clear whole collection
 */
public class ClearCommand extends Command {


    /**
     * execute command
     *
     * @return
     * @throws EmptyElement
     * @throws IncorrectData
     * @param environment
     */
    @Override
    public CommandResult run(CommandEnvironment environment) throws EmptyElement, IncorrectData {

        environment.getCollectionManager().clearCollection();
        environment.getCollectionManager().clearCollection();
        return new CommandResult("clear", "Коллекция пуста, в ней удалены все элементы", true);

    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "Удаляет все элементы коллекции";
    }
}
