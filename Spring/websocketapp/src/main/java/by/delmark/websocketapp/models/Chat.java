package by.delmark.websocketapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Chat {
    private Long chatId;
    private String chatName;
    private String chatDesc;
}
