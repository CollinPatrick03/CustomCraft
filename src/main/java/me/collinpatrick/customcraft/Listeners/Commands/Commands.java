package me.collinpatrick.customcraft.Listeners.Commands;

import me.collinpatrick.customcraft.Files.CoordinatesReadWrite;
import me.collinpatrick.customcraft.Files.StructureReadWrite;
import me.collinpatrick.customcraft.TaskHandlers.TrackerHandler;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.Console;
import java.io.File;
import java.io.IOException;

public class Commands implements CommandExecutor {
    JSONArray writeList = new JSONArray();
    StructureReadWrite sRW = new StructureReadWrite();
    CoordinatesReadWrite cRW = new CoordinatesReadWrite();

    TrackerHandler trackerHandler = new TrackerHandler();
    String absolutePath;
    public Commands(Plugin p) {
        absolutePath = p.getDataFolder().getAbsolutePath();
        trackerHandler.setPlugin(p);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


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
}
