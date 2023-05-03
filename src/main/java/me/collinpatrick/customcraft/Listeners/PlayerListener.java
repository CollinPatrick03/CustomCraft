package me.collinpatrick.customcraft.Listeners;

import me.collinpatrick.customcraft.RecipesContainer.RecipeContainer;
import me.collinpatrick.customcraft.SqlLogging.LoggingHandler;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {
    RecipeContainer customRecipesContainer;
    LoggingHandler loggingHandler;

    public PlayerListener(RecipeContainer r, LoggingHandler loggingHandler) {
        customRecipesContainer = r;
        this.loggingHandler = loggingHandler;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        p.discoverRecipes(this.customRecipesContainer.getKeys());
        loggingHandler.addPlayerToHashMap(event.getPlayer().getName());

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();

        loggingHandler.removePlayerFromHashMap(p.getName());
    }

    @EventHandler
    public void onEntityKill(EntityDeathEvent event) {
        System.out.println("Made it to the death event");
        LivingEntity entity = event.getEntity();
        try {
            Player p = entity.getKiller();
            loggingHandler.addKillForPlayer(p.getName());
        }
        catch(NullPointerException e) {
            e.printStackTrace();
        }


    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player p = (Player) event.getEntity();
        loggingHandler.addDeathForPlayer(p.getName());
    }
}
