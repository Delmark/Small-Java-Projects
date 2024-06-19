package by.delmark.websocketapp.models.WebSocketEvents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebSocketEvent {
    EventType eventType;
    HashMap<String, String> payload;
}
