package ru.io;

import java.io.FileOutputStream;
import java.util.Arrays;

public class ResultFile {

    public static int[][] multiplicationTable() {
        int[][] matrix = new int[9][9];
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                matrix[i - 1][j - 1] = i * j;
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        try (FileOutputStream output = new FileOutputStream("data/dataresult.txt")) {
            int[][] matrix = multiplicationTable();
            for (int i = 0; i < matrix.length; i++) {
                output.write(Arrays.toString(matrix[i]).getBytes());
                output.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}