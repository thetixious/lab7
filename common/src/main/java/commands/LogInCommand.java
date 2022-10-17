package commands;

import exeptions.EmptyElement;
import exeptions.IncorrectData;

public class LogInCommand extends Command {



    @Override
    public CommandResult run(CommandEnvironment environment) throws EmptyElement, IncorrectData {
        String[] str = (String[]) environment.getData();

        if (environment.getSqlUserManager().isItRegistrated(str[0],str[1]))

            return new CommandResult("log_in","Вы вошли в систему",str[0]);
        else
            return new CommandResult("error","Неверный логин или пароль",false);
    }

    @Override
    public String getName() {
        return "log_in";
    }

    @Override
    public String getDescription() {
        return "";
    }
}
