package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Event;
import model.EventDto;

import java.util.Scanner;

public interface LogInterface  {
    boolean saveIntoDb(Scanner scanner, ObjectMapper mapper) throws JsonProcessingException;
    void readFromDatabase();
}
