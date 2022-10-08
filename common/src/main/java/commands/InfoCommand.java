package commands;


import data.SpaceMarine;
import exeptions.IncorrectData;
import utility.CollectionManager;

import java.time.format.DateTimeFormatter;

/**
 * "info" command, print info about command
 */
public class InfoCommand extends Command {



    /**
     * execute command
     * @throws IncorrectData
     * @return
     */
    @Override
    public CommandResult run(CollectionManager collectionManager, Object data, SpaceMarine item) throws IncorrectData {
        if (collectionManager.getSize()==0)
            return  new CommandResult("info","Коллекция пуста",false);
        String result = collectionManager.getLocalDateTime().format(DateTimeFormatter.ofPattern("MMMM, dd, yyyy HH:mm:ss")) +
                "\n" + "Размер коллекции " + collectionManager.getSize() +
                "\n" + "Тип коллекции " + collectionManager.getCollection().getClass();
        return new CommandResult("info",result,true);

    }
    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "Вывод информации о коллекции";
    }

}
