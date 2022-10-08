package commands;

import exeptions.IncorrectData;
import data.SpaceMarine;
import exeptions.EmptyElement;
import utility.CollectionManager;

public class RemoveByIdCommand extends Command {

    @Override
    public CommandResult run(CollectionManager collectionManager, Object data, SpaceMarine item) throws EmptyElement, IncorrectData {
        try {
            String[] str = (String[]) data;
            Long id = Long.parseLong(str[0]);
            if (collectionManager.deleteElementById(id))
                return new CommandResult("remove_by_id", "Объект удален", true);
            else
                return new CommandResult("remove_by_id", "Объекта с id = " + id + " не существует", false);

        } catch (ClassCastException | ArrayIndexOutOfBoundsException  e) {
            return new CommandResult("error", null, null);
        }
    }


    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescription() {
        return "Удаляет элемент из коллекции по его id";
    }
}
