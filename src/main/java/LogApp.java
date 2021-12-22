import com.fasterxml.jackson.databind.ObjectMapper;
import model.EventDto;
import java.io.*;
import java.util.Scanner;

public class LogApp {

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        String path = "C:\\Users\\Adrian\\IdeaProjects\\log-task-ext\\test.txt";
        EventDto event;
        try (FileReader fileReader = new FileReader(path);
            Scanner scanner = new Scanner(fileReader)
        ){
            while (scanner.hasNextLine()) {
                String jsonStringEvent = scanner.nextLine();
                event = EventDto.of(jsonStringEvent, mapper);
                System.out.println(event);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
