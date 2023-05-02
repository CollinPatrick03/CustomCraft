package me.collinpatrick.customcraft.Listeners.Commands;

import me.collinpatrick.customcraft.Models.CommandLog;
import me.collinpatrick.customcraft.RecipesContainer.RecipeContainer;
import me.collinpatrick.customcraft.SqlLogging.LoggingHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ChatCommandListener implements Listener {

    LoggingHandler loggingHandler = new LoggingHandler();
    public ChatCommandListener() {
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e){
        CommandLog commandLog = new CommandLog(0, e.getPlayer().getName(), e.getMessage().substring(1));
        loggingHandler.writeCommandLog(commandLog);
    }

}



