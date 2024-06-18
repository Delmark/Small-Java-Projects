package by.delmark.websocketapp.models;

import lombok.Data;

@Data
public class Message {
    private Long messageId;
    private Long chatId;
    private String messageText;
}
