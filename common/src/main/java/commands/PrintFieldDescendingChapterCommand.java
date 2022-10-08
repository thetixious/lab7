package commands;

import data.Chapter;
import data.SpaceMarine;
import exeptions.IncorrectData;
import exeptions.EmptyElement;
import utility.CollectionManager;

/**
 * "print_chapter" command,  Print descending chapter's fields
 */
public class PrintFieldDescendingChapterCommand extends Command {
    private StringBuffer buf = new StringBuffer();

    /**
     * execute command
     *
     * @return
     * @throws EmptyElement
     * @throws IncorrectData
     */
    @Override
    public CommandResult run(CollectionManager collectionManager, Object data, SpaceMarine item) throws EmptyElement, IncorrectData {

        if (collectionManager.getSize() == 0)
            return new CommandResult("print_chapter", "Коллекция пуста", false);

        for (Chapter chapter : collectionManager.printChapterFields())
            buf.append(chapter + "\n");
        return new CommandResult("print_chapter", buf, true);
    }

    @Override
    public String getName() {
        return "print_chapter";
    }

    @Override
    public String getDescription() {
        return "Выводит поля Chapter всех элементов коллекции в порядке убывания";
    }
}
