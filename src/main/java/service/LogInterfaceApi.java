package service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Event;
import model.EventDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.*;

public class LogInterfaceApi implements LogInterface {
    private final SessionFactory factory;
    private final Map<String, EventDto> events = new HashMap<>();
    private Session session;
    public LogInterfaceApi(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public boolean saveIntoDb(Scanner scanner, ObjectMapper mapper) throws JsonProcessingException {
        session = factory.openSession();
        EventDto eventDto;
        while(scanner.hasNextLine()) {
            String jsonStringEvent = scanner.nextLine();
            eventDto = EventDto.of(jsonStringEvent, mapper);
            createEventEntityFrom(eventDto);
        }
        session.close();
        return true;
    }

    @Override
    public void readFromDatabase() {
        session = factory.openSession();
        session.createQuery("from Event", Event.class).getResultList().forEach(System.out::println);
        session.close();
    }

    private Event createEventEntityFrom(EventDto eventDto) {
        Event event = new Event();
        event.setEventId(eventDto.getId());
        if (!eventDto.getExtras().isEmpty()) {
            event.setExtras(eventDto.getExtras());
        }
        calculateDurationAndSave(eventDto, event);
        return event;
    }

    private boolean calculateDurationAndSave(EventDto eventDto, Event event) {
        if (events.containsKey(eventDto.getId())) {
            final EventDto foundEventDto = events.get(eventDto.getId());
            final Long time1 = Long.valueOf(eventDto.getTimestamp());
            final Long time2 = Long.valueOf(foundEventDto.getTimestamp());
            Long duration = time1 - time2;
            if (duration < 0) {
                duration = -duration;
            }
            if (duration > 4) {
                event.setLongEvent(true);
            }
            event.setDuration(duration);
            events.remove(eventDto.getId());
            Transaction transaction = session.beginTransaction();
            session.save(event);
            transaction.commit();
            return true;
        }
        else {
            events.put(eventDto.getId(), eventDto);
            return false;
        }
    }
}

