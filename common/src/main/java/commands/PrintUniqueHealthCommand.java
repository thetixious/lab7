package commands;

import exeptions.IncorrectData;
import exeptions.EmptyElement;

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
     * @param environment
     */
    @Override
    public CommandResult run(CommandEnvironment environment) throws EmptyElement, IncorrectData {
            if (environment.getCollectionManager().getUniqueHealth().isEmpty())
                return new CommandResult("print_unique_health","Коллекция пока пуста",false);
            for (Double health : environment.getCollectionManager().getUniqueHealth())
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
