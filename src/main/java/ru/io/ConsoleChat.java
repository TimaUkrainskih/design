package ru.io;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<String> responseBotAndUser = new ArrayList<>();
            List<String> botAnswers = readPhrases();
            boolean chatting = true;
            boolean isStopped = false;
            while (chatting) {
                String userInput = reader.readLine().trim();
                String botResponse = "";
                if (userInput.equalsIgnoreCase(OUT)) {
                    chatting = false;
                    botResponse = "Чат закрыт";
                } else if (userInput.equalsIgnoreCase(STOP)) {
                    isStopped = true;
                    botResponse = "Работа чата приостановлен";
                } else if (userInput.equalsIgnoreCase(CONTINUE)) {
                    isStopped = false;
                    botResponse = "Работа часа возобленовлена";
                } else if (!isStopped) {
                    botResponse = getRandomBotResponse(botAnswers);
                    System.out.println(botResponse);
                }
                String timestamp = LocalDateTime.now().toString();
                String logEntry = String.format("[%s] User: %s%n[%s] Bot: %s%n", timestamp, userInput, timestamp, botResponse);
                responseBotAndUser.add(logEntry);
            }
            saveLog(responseBotAndUser);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getRandomBotResponse(List<String> botAnswers) {
        Random random = new Random();
        int index = random.nextInt(botAnswers.size());
        return botAnswers.get(index);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            String line;
            while ((line = reader.readLine()) != null) {
                phrases.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (String logEntry : log) {
                writer.write(logEntry);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("./data/answer.txt", "./data/inputBot.txt");
        consoleChat.run();
    }
}