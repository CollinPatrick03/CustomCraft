package me.collinpatrick.customcraft.SqlLogging;
import me.collinpatrick.customcraft.Models.BlockLog;
import me.collinpatrick.customcraft.Models.CommandLog;
import me.collinpatrick.customcraft.Models.PlayerLog;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.HashMap;

public class SqlRepo {
    String serverURL = "jdbc:mysql://mysql.apexhosting.gdn:3306/apexMC1715516";
    String username = "apexMC1715516";
    String password = "5jerLGLYDzIo$#rq1JwuMUnm";

    Connection databaseConnection;
    Statement databaseStatement;
    public SqlRepo() {

    }


    public void initialSetup() {
        try {
            databaseConnection = DriverManager.getConnection(serverURL, username, password);
            databaseStatement = databaseConnection.createStatement();
            System.out.println("Database connection and database statement both worked");
            String playerLogsTableCreation = ("CREATE TABLE IF NOT EXISTS playerLogs(name varchar(60) PRIMARY KEY, deaths int, kills int, blocksBroken long, lastLogin DATE)");
            String commandLogsCreation = ("CREATE TABLE IF NOT EXISTS commandLogs(commandLogId int NOT NULL AUTO_INCREMENT PRIMARY KEY,nameOfCaller varchar(60),command varchar(100))");
            String tradeLogsCreation = ("CREATE TABLE IF NOT EXISTS tradeLogs(tradeId int NOT NULL AUTO_INCREMENT PRIMARY KEY,nameOfTrader1 varchar(60), nameOfTrader2 varchar(60), trader1Item varchar(30), trader2Item varchar(30), trader1ItemAmount int, trader2ItemAmount int, dayOfTrade DATE)");
            String blockLogsCreation = ("CREATE TABLE IF NOT EXISTS blockLogs(blockLogId int NOT NULL AUTO_INCREMENT PRIMARY KEY, nameOfBlock varchar(45), playerInvolved varchar(60), placeOrBreak bit,date DATE, xPos double, yPos double, zPos double)");

            databaseStatement.execute(playerLogsTableCreation);
            databaseStatement.execute(commandLogsCreation);
            databaseStatement.execute(tradeLogsCreation);
            databaseStatement.execute(blockLogsCreation);
            databaseStatement.close();
            databaseConnection.close();
        }
        catch(SQLException s) {
            s.printStackTrace();
        }
    }
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(serverURL, username, password);
        }
        catch(SQLException e) {
            return null;
        }
    }

    public PlayerLog getPlayerLogByName(String name) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM playerLogs where name = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                String userName = resultSet.getString("name");
                int deaths = resultSet.getInt("deaths");
                int kills = resultSet.getInt("kills");
                long blocksBroken = resultSet.getLong("blocksBroken");
                Date lastLogin = resultSet.getDate("lastLogin");
                PlayerLog playerLog = new PlayerLog(userName, deaths, kills, blocksBroken, lastLogin);
                this.databaseStatement.close();
                return playerLog;
            }
            else {
                return null;
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public HashMap<String, PlayerLog> getAllPlayerLogs() {
        HashMap<String, PlayerLog> playerLogHashMap = new HashMap<String,PlayerLog>();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM playerLogs");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                playerLogHashMap.put(resultSet.getString("name"), new PlayerLog(resultSet.getString("name"), resultSet.getInt("deaths"), resultSet.getInt(3), resultSet.getLong(4), resultSet.getDate(5)));
            }
            return playerLogHashMap;
        }
        catch(SQLException e) {
            return null;
        }

    }

    public boolean writePlayerLog(PlayerLog player) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("REPLACE INTO playerLogs(name, deaths, kills, blocksBroken, lastLogin) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, player.getName());
            preparedStatement.setInt(2, player.getDeaths());
            preparedStatement.setInt(3, player.getKills());
            preparedStatement.setLong(4, player.getBlocksBroken());
            preparedStatement.setDate(5, player.getLastLogin());
            preparedStatement.executeUpdate();
            return true;
        }
        catch(SQLException e) {
            System.out.println("Player did not get wrote to server");
            e.printStackTrace();
            return false;
        }
    }

    public boolean writeBlockEvent(BlockLog b) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO blockLogs(nameOfBlock, playerInvolved, placeOrBreak, date, xPos, yPos, zPos) VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setString(1, b.getNameOfBlockInvolved());
            preparedStatement.setString(2, b.getNameOfPlayerInvolved());
            preparedStatement.setBoolean(3, b.getPlaceOrBreak());
            preparedStatement.setDate(4, b.getDate());
            preparedStatement.setDouble(5, b.getxPos());
            preparedStatement.setDouble(6,b.getyPos());
            preparedStatement.setDouble(7, b.getzPos());
            preparedStatement.executeUpdate();
            return true;
        }
        catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean writeCommandLog(CommandLog c) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO commandLogs(nameOfCaller, command) VALUES (?,?)");
            preparedStatement.setString(1, c.getNameOfCaller());
            preparedStatement.setString(2, c.getCommand());
            preparedStatement.executeUpdate();
            return true;
        }
        catch(SQLException e) {
            e.printStackTrace();
            return false;

        }
    }



}