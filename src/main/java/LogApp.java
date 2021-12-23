import com.fasterxml.jackson.databind.ObjectMapper;
import model.EventDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import service.LogInterface;
import service.LogInterfaceApi;
import util.HibernateUtil;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LogApp {

    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        ObjectMapper mapper = new ObjectMapper();
        final Map<String, EventDto> events = new HashMap<>();
        String path = args[0];
        LogInterface logInterface = new LogInterfaceApi(factory, events);
        Session session;
        try (FileReader fileReader = new FileReader(path);
            Scanner scanner = new Scanner(fileReader)
        ){
            session = factory.openSession();
            EventDto eventDto;
            while(scanner.hasNextLine()) {
                String jsonStringEvent = scanner.nextLine();
                eventDto = EventDto.of(jsonStringEvent, mapper);
                logInterface.saveIntoDb(eventDto);
            }
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logInterface.readFromDatabase();
    }
}
