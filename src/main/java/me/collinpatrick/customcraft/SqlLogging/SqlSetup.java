package me.collinpatrick.customcraft.SqlLogging;
import java.sql.Connection;
import java.sql.DriverManager;

import java.io.File;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlSetup {
    String serverURL = "jdbc:mysql://mysql.apexhosting.gdn:3306/apexMC1715516";
    String username = "apexMC1715516";
    String password = "5jerLGLYDzIo$#rq1JwuMUnm";

    public SqlSetup() {

    }

    public void initialSetup() {
        try {
            Connection databaseConnection = DriverManager.getConnection(serverURL, username, password);
            Statement databaseStatement = databaseConnection.createStatement();
            System.out.println("Database connection and database statement both worked");
            String databaseCreation = ("CREATE TABLE IF NOT EXISTS playerLogs(name varchar(60), deaths int, kills int, blocksBroken long, lastLogin DATE)");
            databaseStatement.execute(databaseCreation);
            databaseStatement.close();
            databaseConnection.close();
        }
        catch(SQLException s) {
            s.printStackTrace();
        }
    }

}
