package ru.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) {
        validateArguments(args);
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) {
        SearchFiles searcher = new SearchFiles(condition);
        try {
            Files.walkFileTree(root, searcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searcher.getPaths();
    }

    public static void validateArguments(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Invalid number of arguments.");
        }
        Path start = Paths.get(args[0]);
        if (!Files.exists(start) || !Files.isDirectory(start)) {
            throw new IllegalArgumentException("Invalid root folder.");
        }
    }
}