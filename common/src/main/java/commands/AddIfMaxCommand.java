package commands;

import exeptions.IncorrectData;
import exeptions.EmptyElement;

/**
 * "add_if_max" command, add item in collection if item bigger than max element in collection
 */
public class AddIfMaxCommand extends Command {
    /**
     * execute command
     * @param environment
     * @throws IncorrectData
     * @throws EmptyElement
     * @return
     */
    @Override
    public CommandResult run(CommandEnvironment environment) throws IncorrectData, EmptyElement {


            if (environment.getCollectionManager().AllowAddIfMax(environment.getSpaceMarine()))
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
