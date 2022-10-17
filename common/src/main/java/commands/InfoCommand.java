package commands;


import exeptions.IncorrectData;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * "info" command, print info about command
 */
public class InfoCommand extends Command {



    /**
     * execute command
     * @throws IncorrectData
     * @return
     * @param environment
     */
    @Override
    public CommandResult run(CommandEnvironment environment) throws IncorrectData {
        if (Objects.equals(environment.getCollectionManager().getCollection(),null))
            return  new CommandResult("info","Коллекция пуста",false);
        String result = environment.getCollectionManager().getLocalDateTime().format(DateTimeFormatter.ofPattern("MMMM, dd, yyyy HH:mm:ss")) +
                "\n" + "Размер коллекции " + environment.getCollectionManager().getSize() +
                "\n" + "Тип коллекции " + environment.getCollectionManager().getCollection().getClass();
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
