import com.fasterxml.jackson.databind.ObjectMapper;
import model.Event;
import model.EventDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.LogInterface;
import service.LogInterfaceApi;
import util.HibernateUtil;

import java.io.*;
import java.util.Scanner;

public class LogApp {

    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        ObjectMapper mapper = new ObjectMapper();
        String path = args[0];
        LogInterface logInterface = new LogInterfaceApi(factory);
        try (FileReader fileReader = new FileReader(path);
            Scanner scanner = new Scanner(fileReader)
        ){
            logInterface.saveIntoDb(scanner, mapper);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logInterface.readFromDatabase();
    }
}
