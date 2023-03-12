package me.collinpatrick.customcraft;
import me.collinpatrick.customcraft.Listeners.PlayerJoinListener;
import me.collinpatrick.customcraft.RecipesContainer.RecipeContainer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class CustomCraft extends JavaPlugin {
    RecipeContainer playerRecipes = new RecipeContainer(this);
    @Override
    public void onEnable() {
        playerRecipes.initRecipes();
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(playerRecipes), this);
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("moo")) {
            for(Player p : getServer().getOnlinePlayers()) {
                p.sendMessage("MOOOOOOOOOO");
            }
        }
        return true;
    }

}
