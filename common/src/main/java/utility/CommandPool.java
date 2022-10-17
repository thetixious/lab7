package utility;

import commands.*;

import java.util.HashMap;

/**
 * Pool of commands
 */
public class CommandPool {
    SQLUserManager sqlUserManager;




    public CommandPool() {

        upload(new AddCommand());
        upload(new HelpCommand());
        upload(new InfoCommand());
        upload(new ShowCommand());
        upload(new AddIfMaxCommand());
        upload(new AverageOfHealthCommand());
        upload(new ClearCommand());
        upload(new RemoveByIdCommand());
        upload(new RemoveFirstCommand());
        upload(new PrintUniqueHealthCommand());
        upload(new RemoveLowerCommand());
        upload(new PrintFieldDescendingChapterCommand());
        upload(new ExitCommand());
        upload(new UpdateIdCommand());
        upload(new SignInCommand());
        upload(new LogInCommand());
    }

    private final HashMap<String, Command> commands = new HashMap<>();

    public Command get(String key) {
        if (!commands.containsKey(key))
            throw new IllegalArgumentException();

        return commands.get(key);
    }

    public void upload(Command command) {
        commands.put(command.getName(), command);
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }

}
