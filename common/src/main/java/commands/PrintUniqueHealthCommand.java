package commands;

import data.SpaceMarine;
import exeptions.IncorrectData;
import exeptions.EmptyElement;
import utility.CollectionManager;

/**
 * "print_unique_health" command, print uniq health values
 */
public class PrintUniqueHealthCommand extends Command {
    private StringBuffer buf = new StringBuffer();
    /**
     * execute command
     * @throws EmptyElement
     * @throws IncorrectData
     * @return
     */
    @Override
    public CommandResult run(CollectionManager collectionManager, Object data, SpaceMarine item) throws EmptyElement, IncorrectData {
            if (collectionManager.getUniqueHealth().isEmpty())
                return new CommandResult("print_unique_health","Коллекция пока пуста",false);
            for (Double health : collectionManager.getUniqueHealth())
                    buf.append(health + "\n");
            return new CommandResult("print_unique_health",buf,true);

    }

    @Override
    public String getName() {
        return "print_unique_health";
    }

    @Override
    public String getDescription() {
        return "Выводит уникальные значение поля health";
    }
}
