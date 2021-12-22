package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Event;
import model.EventDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.*;

public class LogInterfaceApi implements LogInterface {
    private final SessionFactory factory;
    private final Map<String, EventDto> events = new HashMap<>();

    public LogInterfaceApi(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public boolean saveIntoDb(Scanner scanner, ObjectMapper mapper) throws JsonProcessingException {
        Session session = factory.openSession();
        EventDto event;
        while(scanner.hasNextLine()) {
            String jsonStringEvent = scanner.nextLine();
            event = EventDto.of(jsonStringEvent, mapper);
            checkAndPrepareEntityObject(event, session);
        }
        session.close();
        return true;
    }

    private Event checkAndPrepareEntityObject(EventDto eventDto, Session session) {
        Event entity = new Event();
        entity.setEventId(eventDto.getId());
        if (!eventDto.getExtras().isEmpty()) {
            entity.setExtras(eventDto.getExtras());
        }
        flag(eventDto, entity, session);
        return entity;
    }

    private void flag(EventDto eventDto, Event entity, Session session) {
        if (events.containsKey(eventDto.getId())) {
            final EventDto event = events.get(eventDto.getId());
            final Long time1 = Long.valueOf(eventDto.getTimestamp());
            final Long time2 = Long.valueOf(event.getTimestamp());
            Long duration = time1 - time2;
            if (duration < 0) {
                duration = -duration;
            }
            if (duration > 4) {
                entity.setLongEvent(true);
            }
            entity.setDuration(duration);
            events.remove(eventDto.getId());
            save(entity, session);
        }
        else {
            events.put(eventDto.getId(), eventDto);
        }
    }

    private void save(Event event, Session session) {
        Transaction transaction = session.beginTransaction();
        session.save(event);
        transaction.commit();
    }

    public void readFromDatabase() {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        final Query<Event> from_event = session.createQuery("from Event", Event.class);
        final List<Event> resultList = from_event.getResultList();
        System.out.println("asadasd");
        resultList.forEach(System.out::println);
        transaction.commit();
        session.close();
    }
}
