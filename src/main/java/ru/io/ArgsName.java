package ru.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public static ArgsName of(String[] args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: '" + key + "' is missing");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String arg : args) {
            validateArguments(arg);
        }
    }

    private void validateArguments(String arg) {
        if (!arg.startsWith("-")) {
            throw new IllegalArgumentException("Error: This argument '" + arg + "' does not start with a '-' character");
        }
        if (!arg.contains("=")) {
            throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain an equal sign");
        }
        String[] parts = arg.substring(1).split("=", 2);
        if (parts.length != 2) {
            throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain a valid key-value pair");
        }
        String key = parts[0];
        String value = parts[1];
        if (key.isEmpty()) {
            throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain a key");
        }
        if (value.isEmpty()) {
            throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain a value");
        }
        values.put(key, value);
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}