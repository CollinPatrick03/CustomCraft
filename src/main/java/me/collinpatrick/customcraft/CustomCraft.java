package me.collinpatrick.customcraft;
import me.collinpatrick.customcraft.Listeners.BlockInteractListener;
import me.collinpatrick.customcraft.Listeners.Commands.ChatCommandListener;
import me.collinpatrick.customcraft.Listeners.PlayerListener;
import me.collinpatrick.customcraft.RecipesContainer.RecipeContainer;
import me.collinpatrick.customcraft.SqlLogging.LoggingHandler;
import org.bukkit.plugin.java.JavaPlugin;
import me.collinpatrick.customcraft.Listeners.Commands.Commands;
import me.collinpatrick.customcraft.SqlLogging.SqlRepo;

import java.io.File;

public final class CustomCraft extends JavaPlugin {
    RecipeContainer playerRecipes = new RecipeContainer(this);
    SqlRepo sqlSetup = new SqlRepo();
    LoggingHandler loggingHandler = new LoggingHandler();

    @Override
    public void onEnable() {
        //Make sure the folder for the plugin is there
        File dirFolder = new File(this.getDataFolder().getAbsolutePath());
        Commands commandExecuter = new Commands(this, loggingHandler);
        dirFolder.mkdir();
        //Init the sql
        sqlSetup.initialSetup();
        //Initializing recipes
        playerRecipes.initRecipes();

        //Initializing the listeners
        getServer().getPluginManager().registerEvents(new PlayerListener(playerRecipes,loggingHandler), this);
        getServer().getPluginManager().registerEvents(new BlockInteractListener(loggingHandler), this);
        getServer().getPluginManager().registerEvents(new ChatCommandListener(), this);

        //Initializing commands
        this.getCommand("CoordinateWriteOut").setExecutor(commandExecuter);
        this.getCommand("CoordinatesReadIn").setExecutor(commandExecuter);
        this.getCommand("Track").setExecutor(commandExecuter);
        this.getCommand("Untrack").setExecutor(commandExecuter);
        this.getCommand("PlayerLog").setExecutor(commandExecuter);


    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
