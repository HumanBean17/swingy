package com.swingy.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class GameDb {

    private static String url = "jdbc:postgresql://localhost/swingy";
    private static String user = "admin";
    private static String password = "admin";

    public static boolean connectToDb() {
        boolean result = true;
        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);
        try {
            Connection connection = DriverManager.getConnection(url, properties);
        } catch (SQLException ex) {
            ex.printStackTrace();
            result = false;
        }
        return result;
    }
}
