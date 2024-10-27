package org.example.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionConfig {
    private Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = ConnectionConfig.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return null;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Properties properties = loadProperties();
        if (properties == null) {
            throw new SQLException("Could not load properties file.");
        }
        String url = properties.getProperty("db.url");
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");
        String driverClassName = properties.getProperty("db.driver");
        Class.forName(driverClassName);
        return DriverManager.getConnection(url, username, password);
    }
}