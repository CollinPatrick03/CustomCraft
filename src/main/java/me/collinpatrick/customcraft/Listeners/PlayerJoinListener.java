package me.collinpatrick.customcraft.Listeners;

import me.collinpatrick.customcraft.RecipesContainer.RecipeContainer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinListener implements Listener {
    RecipeContainer customRecipesContainer;

    public PlayerJoinListener(RecipeContainer r) {
        customRecipesContainer = r;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        p.discoverRecipes(this.customRecipesContainer.getKeys());

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

    }
}
