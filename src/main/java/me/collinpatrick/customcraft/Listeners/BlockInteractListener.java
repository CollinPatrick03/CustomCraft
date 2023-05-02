package me.collinpatrick.customcraft.Listeners;

import me.collinpatrick.customcraft.Models.BlockLog;
import me.collinpatrick.customcraft.SqlLogging.LoggingHandler;
import me.collinpatrick.customcraft.SqlLogging.SqlRepo;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockInteractListener implements Listener {

    LoggingHandler loggingHandler = new LoggingHandler();
    public BlockInteractListener() {
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        Block b = event.getBlock();
        Player p = event.getPlayer();
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        BlockLog blockLog = new BlockLog(0, b.getType().name(), p.getName(),true, sqlDate, b.getX(), b.getY(), b.getZ());
        loggingHandler.writeBlockEvent(blockLog);
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        Block b = event.getBlock();
        Player p = event.getPlayer();
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        BlockLog blockLog = new BlockLog(0, b.getType().name(), p.getName(),false, sqlDate, b.getX(), b.getY(), b.getZ());
        loggingHandler.writeBlockEvent(blockLog);
    }
}
