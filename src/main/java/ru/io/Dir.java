package ru.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("/Users/timofejnikitan/IdeaProjects/design");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("size : %s", file.getTotalSpace()));
        for (File subfile : file.listFiles()) {
            System.out.println("File name: " + subfile.getName() + " Size: " + subfile.length());
        }
    }
}