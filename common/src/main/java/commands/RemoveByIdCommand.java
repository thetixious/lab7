package commands;

import exeptions.IncorrectData;
import exeptions.EmptyElement;

public class RemoveByIdCommand extends Command {

    @Override
    public CommandResult run(CommandEnvironment environment) throws EmptyElement, IncorrectData {
        try {
            String[] str = (String[]) environment.getData();
            Long id = Long.parseLong(str[0]);
            if (environment.getCollectionManager().deleteElementById(id))
                return new CommandResult("remove_by_id", "Объект удален", true);
            else
                return new CommandResult("remove_by_id", "Объекта с id = " + id + " не существует", false);

        } catch (ClassCastException | ArrayIndexOutOfBoundsException  e) {
            return new CommandResult("error", null, false);
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
