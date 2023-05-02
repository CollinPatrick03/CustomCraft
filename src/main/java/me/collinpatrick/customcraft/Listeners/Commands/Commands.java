package me.collinpatrick.customcraft.Listeners.Commands;

import me.collinpatrick.customcraft.Files.CoordinatesReadWrite;
import me.collinpatrick.customcraft.Files.StructureReadWrite;
import me.collinpatrick.customcraft.SqlLogging.LoggingHandler;
import me.collinpatrick.customcraft.TaskHandlers.TrackerHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.checkerframework.checker.units.qual.C;
import org.json.simple.JSONArray;
import me.collinpatrick.customcraft.Models.CommandLog;


public class Commands implements CommandExecutor {
    JSONArray writeList = new JSONArray();
    StructureReadWrite sRW = new StructureReadWrite();
    CoordinatesReadWrite cRW = new CoordinatesReadWrite();

    LoggingHandler loggingHandler = new LoggingHandler();

    TrackerHandler trackerHandler = new TrackerHandler();


    String absolutePath;
    public Commands(Plugin p) {
        absolutePath = p.getDataFolder().getAbsolutePath();
        trackerHandler.setPlugin(p);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        System.out.println(command.toString());

        buildCommandLog(command.getName(), sender.getName(), args);

        if(command.getName().equalsIgnoreCase("CoordinateWriteOut")) {
            cRW.CoordinatesWriteOut(this.absolutePath, args, sender);

        }
        else if(command.getName().equalsIgnoreCase("CoordinatesReadIn")) {
            cRW.CoordinateReadIn(this.absolutePath, args, sender);

        }
        else if(command.getName().equalsIgnoreCase("Track")) {
            trackerHandler.TrackerTaskBuilder(args, sender);

        }
        else if(command.getName().equalsIgnoreCase("Untrack")) {
            trackerHandler.CancelTrackingTask(sender);
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
}
