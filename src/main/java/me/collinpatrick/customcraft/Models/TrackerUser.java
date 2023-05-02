package me.collinpatrick.customcraft.Models;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class TrackerUser {
    private Player finder;
    private BukkitTask trackerTask;

    public TrackerUser(Player finder, BukkitTask trackerTask) {
        this.finder = finder;
        this.trackerTask = trackerTask;
    }

    public Player getFinder() {
        return this.finder;
    }

    public BukkitTask getTrackerTask() {
        return this.trackerTask;
    }
}
