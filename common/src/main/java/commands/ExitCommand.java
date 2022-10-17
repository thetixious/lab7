package commands;

import exeptions.EmptyElement;
import exeptions.IncorrectData;

public class ExitCommand extends Command{
    @Override
    public CommandResult run(CommandEnvironment environment) throws EmptyElement, IncorrectData {
        return null;
    }

    @Override
    public String getName() {
        return "exit";
    }
    public String getDescription(){
        return "Завершает работу клиента";
    }
}
