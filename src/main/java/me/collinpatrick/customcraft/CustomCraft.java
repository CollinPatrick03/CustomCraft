package me.collinpatrick.customcraft;
import me.collinpatrick.customcraft.Listeners.PlayerJoinListener;
import me.collinpatrick.customcraft.RecipesContainer.RecipeContainer;
import org.bukkit.plugin.java.JavaPlugin;
import me.collinpatrick.customcraft.Listeners.Commands.Commands;
import me.collinpatrick.customcraft.SqlLogging.SqlSetup;
import java.sql.Connection;
import java.sql.DriverManager;

import java.io.File;
import java.sql.SQLException;
import java.sql.Statement;

public final class CustomCraft extends JavaPlugin {
    RecipeContainer playerRecipes = new RecipeContainer(this);
    SqlSetup sqlSetup = new SqlSetup();

    @Override
    public void onEnable() {
        //Make sure the folder for the plugin is there
        File dirFolder = new File(this.getDataFolder().getAbsolutePath());
        Commands commandExecuter = new Commands(this);
        dirFolder.mkdir();
        //Init the sql
        sqlSetup.initialSetup();

        //Initializing recipes
        playerRecipes.initRecipes();


        //Initializing commands
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(playerRecipes), this);
        this.getCommand("CoordinateWriteOut").setExecutor(commandExecuter);
        this.getCommand("CoordinatesReadIn").setExecutor(commandExecuter);
        this.getCommand("Track").setExecutor(commandExecuter);
        this.getCommand("Untrack").setExecutor(commandExecuter);

    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
