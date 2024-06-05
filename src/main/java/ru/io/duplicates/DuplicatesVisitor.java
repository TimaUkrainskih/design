package ru.io.duplicates;

import java.io.File;
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
        File fileVisit = file.toFile();
        if (fileVisit.isFile()) {
            FileProperty property = new FileProperty(fileVisit.length(), fileVisit.getName());
            filesMap.computeIfAbsent(property, set -> new HashSet<>()).add(file);
        }
        return super.visitFile(file, attrs);
    }

    public void findDuplicates() {
        filesMap.entrySet().stream()
                .filter(pair -> pair.getValue().size() > 1)
                .forEach(pair -> {
                    FileProperty fileProperty = pair.getKey();
                    Set<Path> paths = pair.getValue();
                    System.out.println(fileProperty.getName() + " - " + fileProperty.getSize() + " bytes:");
                    paths.stream()
                            .map(Path::toAbsolutePath)
                            .forEach(path -> System.out.println("\t" + path));
                });
    }
}