package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CreateLargeFile {
    public static void main(String[] args) {
        String str = "sample text\n";
        String fileName = "test.txt";
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(fileName, true));
            for (int i = 0; i < 100000; i++) {
                final String concat = str.concat(str);
                str = concat;
                writer.append(concat);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
