package me.collinpatrick.customcraft.SqlLogging;
import me.collinpatrick.customcraft.Models.PlayerLog;

import java.sql.*;

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
            String commandLogsCreation = ("CREATE TABLE IF NOT EXISTS commandLogs(commandLogId int NOT NULL AUTO_INCREMENT PRIMARY KEY,name varchar(60) SECONDARY KEY,command varchar(100)");
            String tradeLogsCreation = ("CREATE TABLE IF NOT EXISTS tradeLogs(tradeId int NOT NULL AUTO_INCREMENT PRIMARY KEY,nameOfTrader1 varchar(60), nameOfTrader2 varchar(60), trader1Item varchar(30), trader2Item varchar(30), trader1ItemAmount int, trader2ItemAmount int, dayOfTrade DATE");
            String blockLogsCreation = ("CREATE TABLE IF NOT EXISTS blockLogs(breakId int NOT NULL AUTO_INCREMENT PRIMARY KEY, nameOfBlock varchar(45) SECONDARY KEY, playerInvolved varchar(60) SECONDARY KEY, date DATE");

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

    public PlayerLog getPlayerLogByName(String name) throws SQLException{
        String query = "SELECT * FROM playerLogs p WHERE P.name = " + name;
        ResultSet resultSet = this.databaseStatement.executeQuery(query);
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



}
