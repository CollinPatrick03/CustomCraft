package me.collinpatrick.customcraft.Listeners;

import me.collinpatrick.customcraft.RecipesContainer.RecipeContainer;
import me.collinpatrick.customcraft.SqlLogging.LoggingHandler;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {
    RecipeContainer customRecipesContainer;
    LoggingHandler loggingHandler = new LoggingHandler();

    public PlayerListener(RecipeContainer r) {
        customRecipesContainer = r;
        System.out.println("MADE IT TO THE LISTENER?");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        System.out.println("MADE IT TO THE PJE");
        Player p = event.getPlayer();

        p.discoverRecipes(this.customRecipesContainer.getKeys());
        loggingHandler.addPlayerToHashMap(event.getPlayer().getName());

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        loggingHandler.removePlayerFromHashMap(event.getPlayer().getName());
    }

    @EventHandler
    public void onEntityKill(EntityDeathEvent event) {
        Entity dead = event.getEntity();
        if(dead.getLastDamageCause() instanceof Player) {
            Player p = (Player) dead.getLastDamageCause();
            loggingHandler.addKillForPlayer(p.getName());
        }

    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player p = (Player) event.getEntity();
        loggingHandler.addDeathForPlayer(p.getName());
    }
}
