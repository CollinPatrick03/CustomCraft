package me.collinpatrick.customcraft.TaskHandlers;

import me.collinpatrick.customcraft.Tasks.Tracker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import me.collinpatrick.customcraft.Models.FinderTarget;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import me.collinpatrick.customcraft.CustomCraft;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import me.collinpatrick.customcraft.Models.TrackerUser;
import java.util.HashMap;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class TrackerHandler{
    private Plugin plugin;
    //private ArrayList<FinderTarget> trackersList = new ArrayList<FinderTarget>();
    private HashMap<String, FinderTarget> trackersList = new HashMap<String, FinderTarget>();
    private ArrayList<TrackerUser> trackerTasksList = new ArrayList<TrackerUser>();

    private ItemStack compassReference = new ItemStack(Material.COMPASS);

    public TrackerHandler() {

    }
    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }

    public void TrackerTaskBuilder(String[] args, CommandSender Sender) {
        //Make sure there is at least a target
        if(args.length == 0) {
            Sender.sendMessage("You must select a player to track");
            return;
        }
        //Make sure there is no more than one target
        if(args.length > 1) {
            Sender.sendMessage("You cannot select more than one player to track");
            return;
        }
        //Make sure the person isn't trying to target themself
        if(args[0].equalsIgnoreCase(Sender.getName())) {
            Sender.sendMessage("You can't track yourself silly");
            return;
        }
        //Actually build the tracker task
        else {
            Player p = (Player) Sender;
            try {
                if(p.getInventory().getItemInMainHand().isSimilar(compassReference)) {
                    if(TrackerListChecker(p)) {
                        Sender.sendMessage("You are already tracking a player, please cancel this track first");
                        return;
                    }
                    Player target = (Bukkit.getPlayer(args[0]));
                    //Add the tracker pair to the Hashmap

                    trackersList.put(p.getName(),new FinderTarget(p, target));
                    ItemMeta compassMeta = p.getInventory().getItemInMainHand().getItemMeta();
                    compassMeta.setDisplayName(ChatColor.DARK_AQUA + "Tracking: " + target.getName());
                    p.getInventory().getItemInMainHand().setItemMeta(compassMeta);
                    BukkitTask trackerTask = new Tracker(this.plugin, GetLastTrackersListItem(p.getName())).runTaskTimer(this.plugin, 0L, 5L);
                    trackerTasksList.add(new TrackerUser(p,trackerTask));

                }
                else {
                    Sender.sendMessage("You must hold a compass to use this command");
                    return;
                }
            }
            catch(NullPointerException e) {
                Sender.sendMessage("You must hold the compass in your hand to use this");
            }
        }


    }

    public boolean TrackerListChecker(Player player) {
        return trackersList.containsKey(player.getName());
    }

    public FinderTarget GetLastTrackersListItem(String name) {
        return this.trackersList.get(name);
    }

    public void CancelTrackingTask(CommandSender sender) {
        Player finder = (Player) sender;

        //Check to see if this player is actually a current "finder"
        if(IsPlayerTracking(finder)) {
            //Get the task from the list
            BukkitTask trackingTask = ReturnTrackingTaskOfFinder(finder);

            //Cancel the task using the Id
            Bukkit.getScheduler().cancelTask(trackingTask.getTaskId());
            //Reset the compass of the finder
            List<World> worldList = Bukkit.getServer().getWorlds();
            System.out.println(worldList.get(0).getName());
            finder.setCompassTarget(worldList.get(0).getSpawnLocation());
            //Reset the meta data of the finders compass
            if(finder.getInventory().contains(compassReference)) {
                System.out.println("Thing is going true");
                int compassInvIndex = finder.getInventory().first(compassReference);
                finder.getInventory().getItem(compassInvIndex).setItemMeta(compassReference.getItemMeta());
                RemoveFinderFromTrackersList(finder.getName());
                RemoveTrackingTaskFromList(finder);
            }
        }
    }

    public boolean IsPlayerTracking(Player finder) {
        for(TrackerUser tU : trackerTasksList) {
            if(tU.getFinder().equals(finder)) {
                return true;
            }
        }
        return false;
    }

    //TODO: This method is super inefficient, will not scale well
    public BukkitTask ReturnTrackingTaskOfFinder(Player finder) {
        for(TrackerUser tU : trackerTasksList) {
            if(tU.getFinder().equals(finder)) {
                return tU.getTrackerTask();
            }
        }
        return null;
    }

    public void RemoveFinderFromTrackersList(String finderName) {
        trackersList.remove(finderName);
    }

    public void RemoveTrackingTaskFromList(Player finder) {
        for(TrackerUser tU : trackerTasksList) {
            if(tU.getFinder().equals(finder)) {
                trackerTasksList.remove(tU);
                return;
            }
        }
    }

}
