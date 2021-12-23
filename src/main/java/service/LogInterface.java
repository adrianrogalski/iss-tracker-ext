package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Event;
import model.EventDto;

import java.util.Scanner;

public interface LogInterface  {
    Event saveIntoDb(EventDto eventDto) throws JsonProcessingException;
    void readFromDatabase();
}
