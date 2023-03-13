package me.collinpatrick.customcraft.Listeners.Commands;

import me.collinpatrick.customcraft.Files.StructureReadWrite;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Commands implements CommandExecutor {
    JSONArray writeList = new JSONArray();
    StructureReadWrite sRW = new StructureReadWrite();
    String absolutePath;
    public Commands(Plugin p) {
        absolutePath = p.getDataFolder().getAbsolutePath();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            if(command.getName().equalsIgnoreCase("testWrite")) {
                JSONObject testObj = new JSONObject();
                testObj.put("DidThisWork", "Yes");
                JSONArray jA = new JSONArray();
                jA.add(testObj);
                sRW.structureWriteOut(absolutePath, "testWrite.json",jA);

            }
        }
        return true;
    }
}
