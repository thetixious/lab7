package commands;

import data.SpaceMarine;
import exeptions.IncorrectData;
import exeptions.EmptyElement;
import utility.CollectionManager;

/**
 * "average_health" command, print average health value in collection
 */
public class AverageOfHealthCommand extends Command {


    @Override
    public CommandResult run(CollectionManager collectionManager, Object data, SpaceMarine item) throws EmptyElement, IncorrectData {
        try {
            return new CommandResult("average_health",collectionManager.getAverageHealth(),true);
            //ioManager.println(collectionManager.getAverageHealth());

        } catch (IllegalArgumentException e) {
            return new CommandResult("average_health", "Не удалось получить результат, повторите", false);
            //ioManager.printerr("Неверное количество аргументов");
        }

    }

    @Override
    public String getName() {
        return "average_health";
    }

    @Override
    public String getDescription() {
        return "Выводит среднее значение поля health у объектов";
    }
}
