package me.collinpatrick.customcraft.Tasks;

import me.collinpatrick.customcraft.Models.FinderTarget;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import me.collinpatrick.customcraft.CustomCraft;
public class Tracker extends BukkitRunnable {
    private Plugin plugin;
    private Player finder;
    private Player target;

    public Tracker(Plugin plugin, FinderTarget finderTargetPair) {
        this.plugin = plugin;
        this.finder = finderTargetPair.getFinder();
        this.target = finderTargetPair.getTarget();
    }

    @Override
    public void run() {
        this.finder.setCompassTarget(this.target.getLocation());
    }
}
