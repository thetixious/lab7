package commands;

import exeptions.EmptyElement;
import exeptions.IncorrectData;

import java.sql.SQLException;

public class SignInCommand extends Command {



    @Override
    public CommandResult run(CommandEnvironment environment) throws EmptyElement, IncorrectData {
        String[] str = (String[]) environment.getData();
        try {
            if (!environment.getSqlUserManager().checkLogin(str[0])) {
                environment.getSqlUserManager().register(str[0], str[1]);
                return new CommandResult("sign_in", "Регистрация произошла успешно", str[0]);
            } else
                return new CommandResult("error", "Пользователь с таким логином уже зарегистрирован", false);
        } catch (SQLException e) {
            return  new CommandResult("error","Проблема с бд",false);
        }


    }

    @Override
    public String getName() {
        return "sign_in";
    }

    @Override
    public String getDescription() {
        return null;
    }
}
