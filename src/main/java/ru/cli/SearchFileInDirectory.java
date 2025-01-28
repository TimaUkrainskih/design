package ru.cli;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.util.*;

public class SearchFileInDirectory {

    public static List<File> findFiles(File dir, String pattern, boolean isRegex) {
        PathMatcher matcher = null;
        if (!isRegex) {
            matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
        }
        List<File> result = new ArrayList<>();
        File[] dirlist = dir.listFiles();
        if (dirlist == null) {
            return result;
        }
        for (File file : dirlist) {
            if (file.isDirectory()) {
                result.addAll(findFiles(file, pattern, isRegex));
                continue;
            }
            if (isRegex) {
                if (file.getName().matches(pattern)) {
                    result.add(file);
                }
            } else if (matcher != null && matcher.matches(file.toPath().getFileName())) {
                result.add(file);
            }
        }
        return result;
    }

    public static void writeToFile(List<File> files, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (File file : files) {
                writer.write(file.getName());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<String, String> parseArguments(String[] args) {
        Map<String, String> params = new HashMap<>();
        for (String arg : args) {
            if (arg.startsWith("-")) {
                String[] parts = arg.split("=");
                if (parts.length == 2) {
                    params.put(parts[0], parts[1]);
                } else {
                    throw new IllegalArgumentException(
                            "Некорректный формат аргумента: " + arg + ". Ожидается формат -ключ=значение.");
                }
            } else {
                throw new IllegalArgumentException("Аргумент должен начинаться с '-' (ключ: " + arg + ")");
            }
        }
        return params;
    }

    private static boolean validateParams(Map<String, String> params) {
        if (!params.containsKey("-d")
                || !params.containsKey("-n")
                || !params.containsKey("-t")
                || !params.containsKey("-o")) {
            return false;
        }
        File dir = new File(params.get("-d"));
        if (!dir.exists() || !dir.isDirectory()) {
            System.err.println("Указанная директория не существует: " + params.get("-d"));
            return false;
        }
        String type = params.get("-t");
        if (!"name".equals(type) && !"mask".equals(type) && !"regex".equals(type)) {
            System.err.println("Некорректный тип поиска: " + type);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        Map<String, String> params = parseArguments(args);
        if (!validateParams(params)) return;
        String directory = params.get("-d");
        String pattern = params.get("-n");
        String type = params.get("-t");
        String outputFile = params.get("-o");
        List<File> result = findFiles(new File(directory), pattern, type.equals("regex"));
        writeToFile(result, outputFile);
    }
}
