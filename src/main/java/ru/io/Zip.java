package ru.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path src : sources) {
                zip.putNextEntry(new ZipEntry(String.valueOf(src)));
                try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(src.toFile()))) {
                    zip.write(input.readAllBytes());
                }
                zip.closeEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validateArguments(ArgsName argsName) {
        String directory = argsName.get("d");
        String exclude = argsName.get("e");
        String output = argsName.get("o");
        if (directory == null || exclude == null || output == null) {
            throw new IllegalArgumentException("Аргументы -d, -e, и -o не переданы");
        }
        Path dirPath = Paths.get(directory);
        if (!Files.exists(dirPath) || !Files.isDirectory(dirPath)) {
            throw new IllegalArgumentException("Переданный путь не существует или не является директорией: " + directory);
        }
        if (!exclude.startsWith(".")) {
            throw new IllegalArgumentException("Расширение исключаемых файлов должно начинаться с \".\": " + exclude);
        }
        if (!output.endsWith(".zip")) {
            throw new IllegalArgumentException("Выходной файл должен иметь расширение \".zip\": " + output);
        }
    }

    public static void main(String[] args) {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        ArgsName argsName = ArgsName.of(args);
        validateArguments(argsName);
        String directory = argsName.get("d");
        String exclude = argsName.get("e");
        String output = argsName.get("o");
        Path path = Paths.get(directory);
        List<Path> list = Search.search(path, p -> !p.toString().endsWith(exclude));
        zip.packFiles(list, new File(output));
    }
}