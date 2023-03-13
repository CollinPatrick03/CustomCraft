package me.collinpatrick.customcraft;
import me.collinpatrick.customcraft.Listeners.PlayerJoinListener;
import me.collinpatrick.customcraft.RecipesContainer.RecipeContainer;
import org.json.simple.parser.JSONParser;
import org.json.simple.*;
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

}
