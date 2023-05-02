package me.collinpatrick.customcraft.Models;

import org.bukkit.entity.Player;

public class FinderTarget {
    private Player finder;
    private Player target;
    public FinderTarget(Player finder, Player target) {
        this.finder = finder;
        this.target = target;
    }

    public Player getFinder() {
        return this.finder;
    }

    public Player getTarget() {
        return this.target;
    }

    public String getFinderName() {
        return this.finder.getName();
    }

    public String getTargetName() {
        return this.target.getName();
    }
}
