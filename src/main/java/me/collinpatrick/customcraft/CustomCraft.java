package me.collinpatrick.customcraft;
import me.collinpatrick.customcraft.Listeners.PlayerJoinListener;
import me.collinpatrick.customcraft.RecipesContainer.RecipeContainer;
import org.bukkit.command.CommandExecutor;
import org.json.simple.parser.JSONParser;
import org.json.simple.*;
import org.bukkit.plugin.java.JavaPlugin;
import me.collinpatrick.customcraft.Listeners.Commands.Commands;
import java.util.ArrayList;

public final class CustomCraft extends JavaPlugin {
    RecipeContainer playerRecipes = new RecipeContainer(this);


    @Override
    public void onEnable() {
        playerRecipes.initRecipes();
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(playerRecipes), this);
        getCommand("generateFirstStruct").setExecutor(new Commands(this));
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
