package me.collinpatrick.customcraft.Listeners.Commands;

import me.collinpatrick.customcraft.Files.StructureReadWrite;
import me.collinpatrick.customcraft.Models.Coordinates;
import me.collinpatrick.customcraft.SqlLogging.LoggingHandler;
import me.collinpatrick.customcraft.TaskHandlers.TrackerHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.json.simple.JSONArray;
import me.collinpatrick.customcraft.Models.CommandLog;


public class Commands implements CommandExecutor {
    LoggingHandler loggingHandler;
    TrackerHandler trackerHandler = new TrackerHandler();


    public Commands(Plugin p, LoggingHandler loggingHandler) {
        this.loggingHandler = loggingHandler;
        trackerHandler.setPlugin(p);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        buildCommandLog(command.getName(), sender.getName(), args);
        if(command.getName().equalsIgnoreCase("CoordinateWriteOut")) {
            coordinateWriteBranch(sender, args);
        }
        else if(command.getName().equalsIgnoreCase("CoordinatesReadIn")) {
            coordinateReadBranch(sender, args);

        }
        else if(command.getName().equalsIgnoreCase("Track")) {
            trackerHandler.TrackerTaskBuilder(args, sender);

        }
        else if(command.getName().equalsIgnoreCase("Untrack")) {
            trackerHandler.CancelTrackingTask(sender);
        }
        else if(command.getName().equalsIgnoreCase("PlayerLog")) {
            playerLogRead(sender, args);
        }

        return true;
    }

    public void buildCommandLog(String commandName, String name, String[] args) {
        String commandString = commandName;
        for(String s : args) {
            commandString += " " + s;
        }
        CommandLog commandLog = new CommandLog(0, name, commandString);
        loggingHandler.writeCommandLog(commandLog);
    }

    public void coordinateWriteBranch(CommandSender sender,String[] args) {
        if(args.length != 4) {
            sender.sendMessage("Incorrect number of arguments");
            return;
        }
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        Coordinates c = new Coordinates(args[3], sender.getName(), Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]), sqlDate);
        loggingHandler.writeCoordinatesOut(c);
    }

    public void coordinateReadBranch(CommandSender sender, String[] args) {
        if(args.length > 1) {
            sender.sendMessage("Invalid number of arguments");
            return;
        }
        else if(args.length == 0) {
            loggingHandler.readAllCoordinatesToPlayer(sender);
            return;
        }
        else if(args.length == 1) {
            loggingHandler.readCoordinatesFromPlayerName(sender, args[0]);
            return;
        }

    }

    public void playerLogRead(CommandSender sender, String[] args) {
        if(args.length == 0) {
            sender.sendMessage("Must provide player name");
            return;
        }
        else if(args.length > 1) {
            sender.sendMessage("Can only provide one name at a time");
            return;
        }
        loggingHandler.readPlayerLogByName(sender, args[0]);
    }
}
