package model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EventDtoTest {
    ObjectMapper mapper;

    @BeforeAll
    void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    public void shouldReturnStandardEventObject() throws JsonProcessingException {
        // given
        String jsonStringObject = "{\"id\":\"scsmbstgrb\", \"state\":\"STARTED\", \"timestamp\":1491377495213}";
        // when
        final EventDto eventDto = mapper.readValue(jsonStringObject, EventDto.class);
        // then
        Assertions.assertEquals("scsmbstgrb", eventDto.getId());
        Assertions.assertEquals("STARTED", eventDto.getState());
        Assertions.assertEquals("1491377495213", eventDto.getTimestamp());
        Assertions.assertEquals(Collections.emptyMap(), eventDto.getExtras());
    }

    @Test
    public void shouldAddObjectsIntoTheExtrasField() throws JsonProcessingException {
        // given
        String jsonStringObject = "{\"id\":\"scsmbstgra\", \"state\":\"STARTED\", \"type\":\"APPLICATION_LOG\", \"host\":\"12345\", \"timestamp\":1491377495212}";
        String type = "APPLICATION_LOG";
        String host = "12345";
        Map<String, String> extras = new HashMap<>();
        extras.put("type", type);
        extras.put("host", host);
        final int mapSize = 2;
        // when
        final EventDto eventDto = mapper.readValue(jsonStringObject, EventDto.class);
        // then
        Assertions.assertEquals(mapSize, eventDto.getExtras().size());
    }

    @Test
    public void shouldHaveOneElementInMapGivenOneExtraElementInJsonFile() throws JsonProcessingException {
        String jsonStringObject = "{\"id\":\"scsmbstgra\", \"state\":\"STARTED\", \"type\":\"APPLICATION_LOG\", \"timestamp\":1491377495212}";
        final int mapSize = 1;
        // when
        final EventDto eventDto = mapper.readValue(jsonStringObject, EventDto.class);
        // then
        Assertions.assertEquals(mapSize, eventDto.getExtras().size());
    }
}