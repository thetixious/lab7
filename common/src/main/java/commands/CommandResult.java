package commands;

import java.io.Serializable;

public class CommandResult implements Serializable {
    private  String command;
    private  Serializable result;
    private  Boolean status;
    private String user;

    public CommandResult(String command, Serializable result, Boolean status) {
        this.command = command;
        this.result = result;
        this.status = status;
    }
    public CommandResult(String command,Serializable result,String user){
        this.command = command;
        this.result = result;
        this.user = user;
    }

    public Serializable getResult() {
        return result;
    }

    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "CommandResult{" +
                "command='" + command + '\'' +
                ", result=" + result +
                ", status=" + status +
                '}';
    }

    public String getCommand() {
        return command;
    }
}
