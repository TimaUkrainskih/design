package ru.jdbc;

import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            Class.forName(properties.getProperty("datasource.driver-class-name"));
            connection = DriverManager.getConnection(
                    properties.getProperty("datasource.url"),
                    properties.getProperty("datasource.username"),
                    properties.getProperty("datasource.password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public void createTable(String tableName) {
        String sql = String.format("create table if not exists %s();", tableName);
        executeStatement(sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format("drop table if exists %s", tableName);
        executeStatement(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format("alter table %s add column %s %s;", tableName, columnName, type);
        executeStatement(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format("alter table %s drop column %s;", tableName, columnName);
        executeStatement(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format("alter table %s rename column %s to %s", tableName, columnName, newColumnName);
        executeStatement(sql);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    private void executeStatement(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
