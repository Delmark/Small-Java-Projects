package by.delmark.websocketapp.models.WebSocketEvents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebSocketEventResponse {
    EventType eventType;
    Object payload;
}
