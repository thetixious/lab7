package commands;

import data.SpaceMarine;
import exeptions.EmptyElement;
import exeptions.IncorrectData;

import java.util.Objects;

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
     * @param environment
     */

    @Override
    public CommandResult run(CommandEnvironment environment) throws EmptyElement, IncorrectData {

        if (Objects.equals(environment.getCollectionManager().getCollection(),null))
            return new CommandResult("show","Коллекция пуста",false);
            for (SpaceMarine spaceMarine :environment.getCollectionManager().getCollection())
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
