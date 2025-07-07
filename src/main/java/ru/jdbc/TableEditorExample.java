package ru.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TableEditorExample {
    public static void main(String[] args) throws IOException {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("application.properties")) {
            config.load(in);
        }

        try (TableEditor editor = new TableEditor(config)) {
            String tableName = "example_table";
            editor.createTable(tableName);
            System.out.println(editor.getTableScheme(tableName));
            editor.addColumn(tableName, "id", "serial");
            System.out.println(editor.getTableScheme(tableName));
            editor.addColumn(tableName, "name", "varchar(25)");
            System.out.println(editor.getTableScheme(tableName));
            editor.renameColumn(tableName, "name", "full_name");
            System.out.println(editor.getTableScheme(tableName));
            editor.dropColumn(tableName, "full_name");
            System.out.println(editor.getTableScheme(tableName));
            editor.dropTable(tableName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
