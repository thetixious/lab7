package commands;

import data.SpaceMarine;
import exeptions.IncorrectData;
import exeptions.EmptyElement;

/**
 * "update" command, update collection item by id
 */
public class UpdateIdCommand extends Command {

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

        try {
            String[] str = (String[]) environment.getData();
            Long id = Long.parseLong(str[0]);
            if (environment.getCollectionManager().getSize() == 0)
                return new CommandResult("update", "коллекция пуста", false);
            else {
                SpaceMarine findMarine = environment.getCollectionManager().findElementById(id);
                if (findMarine == null)
                    return new CommandResult("update", "в коллекции нет элемента с данным id", false);
                else {
                    environment.getCollectionManager().addMarine(environment.getSpaceMarine());
                    return new CommandResult("update", "____Элемент обновлен____", true);
                }
            }
        }
        catch (IllegalArgumentException e) {
            return new CommandResult("update", "Неверный формат id", false);
        }
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getDescription() {
        return "Обновляет элемент по введенному id";
    }
}
