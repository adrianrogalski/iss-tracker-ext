package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LargeFileGenerator {
    public static void main(String[] args) throws IOException {
        File file = new File("test.txt");
        String sampleText = "{\"id\":\"scsmbstgra\", \"state\":\"STARTED\", \"type\":\"APPLICATION_LOG\", \"host\":\"12345\", \"timestamp\":1491377495212}\n{\"id\":\"scsmbstgrb\", \"state\":\"STARTED\", \"timestamp\":1491377495213}\n";
        for (int i = 0; i < 22; i++) {
            FileWriter writer = new FileWriter(file);
            BufferedWriter writer1 = new BufferedWriter(writer);
            sampleText = sampleText.concat(sampleText);
            writer1.append(sampleText);
            writer1.close();
        }

    }
}