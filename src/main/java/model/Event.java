package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "events")
@Data
public class Event {
    @Id
    private UUID id;
    private String eventId;
    private Long duration;
    private Boolean longEvent;
    @ElementCollection // this is a collection of primitives
    @MapKeyColumn(name="key") // column name for map "key"
    @Column(name="value")
    Map<String, String> extras;

    public Event() {
        this.id = UUID.randomUUID();
    }
}
