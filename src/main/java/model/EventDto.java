package model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class EventDto {
    @JsonProperty("id")
    private String id;
    @JsonProperty("state")
    private String state;
    @JsonProperty("timestamp")
    private String timestamp;
    private Map<String, String> extras = new HashMap<>();

    private EventDto() {
    }

    @JsonAnySetter
    public void setExtras(String type, String host) {
        this.extras.put(type, host);
    }
    public static EventDto of(String jsonStringEvent, ObjectMapper mapper) throws JsonProcessingException {
            return mapper.readValue(jsonStringEvent, EventDto.class);
    }
}

