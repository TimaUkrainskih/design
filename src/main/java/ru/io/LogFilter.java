package ru.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {

    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> result = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
           result = input.lines().filter(line -> line.contains(" 404 ")).toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void saveTo(String out) {
        List<String> data = filter();
        try (PrintWriter output = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(out)
                ))) {
            for (String dt : data) {
                output.println(dt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }
}