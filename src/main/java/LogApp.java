import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.FileDTO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class LogApp {

    public static void main(String[] args) throws FileNotFoundException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String path = "C:\\Users\\Adrian\\IdeaProjects\\log-task-ext\\src\\logfile.txt";
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        List<String> jsonStringEvents = new LinkedList<>();
        List<FileDTO> events = new LinkedList<>();
        while(scanner.hasNextLine()) {
            final String jsonStringEvent = scanner.nextLine();
            jsonStringEvents.add(jsonStringEvent);
            final FileDTO fileDTO = mapper.readValue(jsonStringEvent, FileDTO.class);
            events.add(fileDTO);
        }
        jsonStringEvents.forEach(System.out::println);
        events.forEach(System.out::println);
    }
}
