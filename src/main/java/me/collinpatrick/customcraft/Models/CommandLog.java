package me.collinpatrick.customcraft.Models;

public class CommandLog {
    int commandLogId;
    String nameOfCaller;
    String command;

    public CommandLog(int commandLogId, String nameOfCaller, String command) {
        this.commandLogId = commandLogId;
        this.nameOfCaller = nameOfCaller;
        this.command = command;
    }

    public int getCommandLogId() {
        return commandLogId;
    }

    public void setCommandLogId(int commandLogId) {
        this.commandLogId = commandLogId;
    }

    public String getNameOfCaller() {
        return nameOfCaller;
    }

    public void setNameOfCaller(String nameOfCaller) {
        this.nameOfCaller = nameOfCaller;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
