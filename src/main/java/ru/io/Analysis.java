package ru.io;

import java.io.*;

public class Analysis {

    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new FileWriter(target))) {
            boolean serverUn = false;
            String startTime = "";
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String status = parts[0];
                if (status.equals("400") || status.equals("500")) {
                    if (!serverUn) {
                        serverUn = true;
                        startTime = parts[1];
                    }
                } else {
                    if (serverUn) {
                        serverUn = false;
                        writer.println(startTime + ";" + parts[1] + ";");
                    }
                }
            }
            if (serverUn) {
                writer.println(startTime + ";");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}


