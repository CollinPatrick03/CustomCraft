package me.collinpatrick.customcraft.SqlLogging;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import me.collinpatrick.customcraft.Models.BlockLog;
import me.collinpatrick.customcraft.Models.CommandLog;
import me.collinpatrick.customcraft.Models.Coordinates;
import me.collinpatrick.customcraft.Models.PlayerLog;
import org.bukkit.command.CommandSender;
import org.checkerframework.checker.units.qual.C;

public class LoggingHandler {
    SqlRepo sqlRepo = new SqlRepo();
    HashMap<String, PlayerLog> playerLogHashMap = new HashMap<String, PlayerLog>();
    public LoggingHandler() {

    }

    public void testAllPlayerLogs() {
        playerLogHashMap = sqlRepo.getAllPlayerLogs();
        System.out.println(playerLogHashMap.get("DoctorParadox").getKills());;
    }

    public boolean writeBlockEvent(BlockLog b) {
        return sqlRepo.writeBlockEvent(b);
    }
    public boolean writeCommandLog(CommandLog c) {
        return sqlRepo.writeCommandLog(c);
    }

    public void addPlayerToHashMap(String playerName) {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        try {
            PlayerLog playerToAdd = sqlRepo.getPlayerLogByName(playerName);
            playerToAdd.setLastLogin(sqlDate);
            playerLogHashMap.put(playerToAdd.getName(), playerToAdd);
        }
        catch(NullPointerException e) {
            PlayerLog newPlayerLog = new PlayerLog(playerName, 0, 0, 0, sqlDate);
            playerLogHashMap.put(newPlayerLog.getName(), newPlayerLog);
        }
        return;
    }

    public void readPlayerLogByName(CommandSender sender, String playerName) {
        try {
            if(playerLogHashMap.containsKey(playerName)) {
                sender.sendMessage(playerLogHashMap.get(playerName).toString());
                return;
            }
            PlayerLog playerLog = sqlRepo.getPlayerLogByName(playerName);
            sender.sendMessage(playerLog.toString());
        }
        catch(NullPointerException e) {
            sender.sendMessage("No player log found for this name");
        }
    }

    public void removePlayerFromHashMap(String playerName) {
        PlayerLog playerLog = playerLogHashMap.get(playerName);
        sqlRepo.writePlayerLog(playerLog);
        playerLogHashMap.remove(playerName);
    }

    public void addKillForPlayer(String playerName) {
        playerLogHashMap.get(playerName).addKill();
    }

    public void addDeathForPlayer(String playerName) {
        playerLogHashMap.get(playerName).addDeath();
    }

    public void addBlockBrokenForPlayer(String playerName) {
        playerLogHashMap.get(playerName).addBlockBroken();
    }

    public boolean writeCoordinatesOut(Coordinates c) {
        return sqlRepo.writeCoordinatesOut(c);
    }

    //TODO: Add caching for these coordinates
    public void readAllCoordinatesToPlayer(CommandSender sender) {
        try {
            ArrayList<Coordinates> coordinatesList = sqlRepo.getAllCoordinates();
            String messageOutToSender = "The saved coordinates for the server are: \n";
            for(Coordinates c : coordinatesList) {
                messageOutToSender = messageOutToSender + c.toString() + "\n";
            }
            sender.sendMessage(messageOutToSender);
            return;
        }
        catch(NullPointerException e) {
            sender.sendMessage("There were no coordinates found for this server");
            e.printStackTrace();
            return;
        }


    }

    public void readCoordinatesFromPlayerName(CommandSender sender, String name) {
        try {
            ArrayList<Coordinates> coordinatesList = sqlRepo.getCoordinateByPlayerName(name);
            String messageOutToSender = "The saved coordinates for this player are: \n";
            for(Coordinates c : coordinatesList) {
                messageOutToSender = messageOutToSender + c.toString() + "\n";
            }
            sender.sendMessage(messageOutToSender);
            return;
        }
        catch(NullPointerException e) {
            sender.sendMessage("There were no coordinates found for this player name");
            e.printStackTrace();
            return;
        }
    }
}
