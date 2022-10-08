package commands;

import data.SpaceMarine;
import exeptions.IncorrectData;
import exeptions.EmptyElement;
import utility.CollectionManager;

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
     */
    @Override
    public CommandResult run(CollectionManager collectionManager, Object data, SpaceMarine newSpaceMarine) throws EmptyElement, IncorrectData {

        try {
            String[] str = (String[]) data;
            Long id = Long.parseLong(str[0]);
            if (collectionManager.getSize() == 0)
                return new CommandResult("update", "коллекция пуста", false);
            else {
                SpaceMarine findMarine = collectionManager.findElementById(id);
                if (findMarine == null)
                    return new CommandResult("update", "в коллекции нет элемента с данным id", false);
                else {
                    collectionManager.addMarine(newSpaceMarine);
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
