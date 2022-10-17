package commands;

import exeptions.IncorrectData;
import exeptions.EmptyElement;

import java.time.LocalDateTime;

/**
 * "add" command, add SpaceMarine instance in collection
 */
public class AddCommand extends Command {

    /**
     * execute command
     *
     * @param environment
     * @return
     */
    //объект создается
    public CommandResult run(CommandEnvironment environment) throws EmptyElement, IncorrectData {

        LocalDateTime date = LocalDateTime.now();
        environment.getSpaceMarine().setCreationDate(date);
        if (environment.getCollectionManager().addMarine(environment.getSpaceMarine())) {
            environment.getSqlCollectionManager().addToBD(environment.getSpaceMarine());
            return new CommandResult("add", "Элемент добавлен", true);
        } else
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
