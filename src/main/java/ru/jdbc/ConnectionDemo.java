package ru.jdbc;

import ru.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Config config = new Config("src/main/resources/application.properties");
        config.load();
        Class.forName(config.value("datasource.driver-class-name"));
        String url = config.value("datasource.url");
        String login = config.value("datasource.username");
        String password = config.value("datasource.password");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
