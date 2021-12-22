package model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EventDTOTest {
    ObjectMapper mapper;

    @BeforeAll
    void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    public void shouldReturnStandardEventObject() throws JsonProcessingException {
        // given
        String jsonStringObject = "{\"id\":\"scsmbstgrb\", \"state\":\"STARTED\", \"timestamp\":1491377495213}";
        String id = "scsmbstgrb";
        String state = "STARTED";
        String timestamp = "1491377495213";
        EventDTO sampleEventDTO = new EventDTO(id, state, timestamp, Collections.emptyMap());
        // when
        final EventDTO eventDTO = mapper.readValue(jsonStringObject, EventDTO.class);
        // then
        Assertions.assertEquals(sampleEventDTO, eventDTO);
    }

    @Test
    public void shouldHaveValidEqualsHashocodeImplementation() throws JsonProcessingException {
        // given
        String jsonStringObject = "{\"id\":\"scsmbstgrb\", \"state\":\"STA\", \"timestamp\":1491377213}";
        String id = "scsmbstgrb";
        String state = "STARTED";
        String timestamp = "1491377495213";
        EventDTO sampleEventDTO = new EventDTO(id, state, timestamp, Collections.emptyMap());
        // when
        final EventDTO eventDTO = mapper.readValue(jsonStringObject, EventDTO.class);
        // then
        Assertions.assertNotEquals(sampleEventDTO, eventDTO);
    }

    @Test
    public void shouldHaveAnEmptyMapIfThereAreNoExtraFields() throws JsonProcessingException {
        // given
        String jsonStringObject = "{\"id\":\"scsmbstgrb\", \"state\":\"STARTED\", \"timestamp\":1491377495213}";
        String id = "scsmbstgrb";
        String state = "STARTED";
        String timestamp = "1491377495213";
        EventDTO sampleEventDTO = new EventDTO(id, state, timestamp, Collections.emptyMap());
        // when
        final EventDTO eventDTO = mapper.readValue(jsonStringObject, EventDTO.class);
        // then
        Assertions.assertEquals(sampleEventDTO.getExtras(), eventDTO.getExtras());
    }

    @Test
    public void shouldHaveValidJsonAnySetterImplementation() throws JsonProcessingException {
        // given
        String jsonStringObject = "{\"id\":\"scsmbstgra\", \"state\":\"STARTED\", \"type\":\"APPLICATION_LOG\", \"host\":\"12345\", \"timestamp\":1491377495212}";
        String id = "scsmbstgra";
        String state = "STARTED";
        String timestamp = "1491377495213";
        String type = "APPLICATION_LOG";
        String host = "12345";
        Map<String, String> extras = new HashMap<>();
        extras.put("type", type);
        extras.put("host", host);
        EventDTO sampleEventDTO = new EventDTO(id, state, timestamp, extras);
        // when
        final EventDTO eventDTO = mapper.readValue(jsonStringObject, EventDTO.class);
        // then
        Assertions.assertEquals(sampleEventDTO.getExtras(), eventDTO.getExtras());
    }

    @Test
    public void shouldHaveOneElementInMapGivenOneExtraElementInJsonFile() throws JsonProcessingException {
        String jsonStringObject = "{\"id\":\"scsmbstgra\", \"state\":\"STARTED\", \"type\":\"APPLICATION_LOG\", \"timestamp\":1491377495212}";
        final int mapSize = 1;
        // when
        final EventDTO eventDTO = mapper.readValue(jsonStringObject, EventDTO.class);
        // then
        Assertions.assertEquals(mapSize, eventDTO.getExtras().size());
    }
}