package commands;

import exeptions.IncorrectData;
import exeptions.EmptyElement;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * "help" command, print list of available command and description
 */
public class HelpCommand extends Command implements Serializable {
    private CommandResult commandResult;
    private StringBuffer buf = new StringBuffer();



    /**
     * execute command
     * @throws EmptyElement
     * @throws IncorrectData
     * @return
     * @param environment
     */
    @Override
    public CommandResult run(CommandEnvironment environment) throws EmptyElement, IncorrectData {

            HashMap<String, Command> pool =environment.getCollectionManager().getCommands();
            for (Map.Entry<String, Command> entry : pool.entrySet()) {
                if(entry.getValue().getName().equals("sign_in") || entry.getValue().getName().equals("log_in"))
                    continue;
                buf.append(entry.getValue().getName() + "----" + entry.getValue().getDescription() + "\n");
            }
            return new CommandResult("help",buf, true);

    }

    public String getDescription() {
        return "Выводит список и функционал всех команд";
    }

    public String getName() {
        return "help";
    }
}
