package me.collinpatrick.customcraft.SqlLogging;
import java.sql.Date;
import java.util.HashMap;
import me.collinpatrick.customcraft.Models.BlockLog;
import me.collinpatrick.customcraft.Models.CommandLog;
import me.collinpatrick.customcraft.Models.PlayerLog;

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
        try {
            PlayerLog playerToAdd = sqlRepo.getPlayerLogByName(playerName);
            playerLogHashMap.put(playerToAdd.getName(), playerToAdd);
        }
        catch(NullPointerException e) {
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            PlayerLog newPlayerLog = new PlayerLog(playerName, 0, 0, 0, sqlDate);
            playerLogHashMap.put(newPlayerLog.getName(), newPlayerLog);
        }
        return;
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
}
