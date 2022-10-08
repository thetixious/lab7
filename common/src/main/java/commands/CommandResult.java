package commands;

import java.io.Serializable;

public class CommandResult implements Serializable {
    private  String command;
    private  Serializable result;
    private  Boolean status;

    public CommandResult(String command, Serializable result, Boolean status) {
        this.command = command;
        this.result = result;
        this.status = status;
    }

    public Serializable getResult() {
        return result;
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
