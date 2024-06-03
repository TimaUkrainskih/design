package ru.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, Set<Path>> filesMap;

    public DuplicatesVisitor() {
        filesMap = new HashMap<>();
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty property = new FileProperty(file.toFile().length(), file.getFileName().toString());
        filesMap.computeIfAbsent(property, set -> new HashSet<>()).add(file);
        return FileVisitResult.CONTINUE;
    }

    public void findDuplicates() {
        for (Map.Entry<FileProperty, Set<Path>> entry : filesMap.entrySet()) {
            Set<Path> paths = entry.getValue();
            if (paths.size() > 1) {
                System.out.println(entry.getKey().getName() + " - " + entry.getKey().getSize() + " bytes:");
                for (Path path : paths) {
                    System.out.println("\t" + path.toAbsolutePath());
                }
            }
        }
    }
}