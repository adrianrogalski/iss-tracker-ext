package model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
    @JsonProperty("id")
    private String id;
    @JsonProperty("state")
    private String state;
    @JsonProperty("timestamp")
    private String timestamp;
    private Map<String, String> extras = new HashMap<>();

    @JsonAnySetter
    public void setExtras(String type, String host) {
        this.extras.put(type, host);
    }
}
