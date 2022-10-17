package commands;

import data.Chapter;
import exeptions.IncorrectData;
import exeptions.EmptyElement;

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
     * @param environment
     */
    @Override
    public CommandResult run(CommandEnvironment environment) throws EmptyElement, IncorrectData {

        if (environment.getCollectionManager().getSize() == 0)
            return new CommandResult("print_chapter", "Коллекция пуста", false);

        for (Chapter chapter : environment.getCollectionManager().printChapterFields())
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
