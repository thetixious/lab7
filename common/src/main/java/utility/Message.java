package utility;

import commands.Command;
import data.SpaceMarine;
import exeptions.EmptyElement;
import exeptions.IncorrectData;

import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

public class Message implements Serializable {
    private Command command;
    private Object data;
    private SpaceMarine spaceMarine;
    private boolean flag = false;
    private String user;
    public Message() {
    }

    public boolean isFlag() {
        return flag;
    }

    public void loadPreMessage(Command command, SpaceMarineArgumentLoader argument, String user) throws EmptyElement, IncorrectData {
        this.command = command;
        data = argument.getStrArguments();
        this.user = user;
        if (command.getName().equals("add") || command.getName().equals("add_if_max") || command.getName().equals("update")) {
            spaceMarine = argument.loadSpaceMarin();
            spaceMarine.setMaster(user);
            flag = true;
        } else if (command.getName().equals("log_in")||command.getName().equals("sign_in")) {
           String login =  inputLogin();
           String password = inputPassword();
           String[] audlog =  new String[] {login, password};
           data = audlog;

        }

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Command getCommand() {
        return command;
    }

    public Object getData() {
        return data;
    }

    public SpaceMarine getSpaceMarine() {
        return spaceMarine;
    }

    private String inputLogin(){
        System.out.println("Введите логин");
        String login =  new Scanner(System.in).nextLine();
        if (Objects.equals(login,null)){
            System.out.println("Введите логин");
            inputLogin();
        }
        return login;
    }
    private String inputPassword(){
        System.out.println("Введите пароль");
        String password = new Scanner(System.in).nextLine();
        if (Objects.equals(password,null)){
            System.out.println("Введите пароль");
            inputPassword();
        }
        return password;
    }
}
