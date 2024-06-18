package by.delmark.websocketapp.service;

import by.delmark.websocketapp.models.Message;

import java.util.List;

public interface MessageService {
    void createMessage(Long chatId, String message);
    void deleteMessageById(Long messageId);
    Message editMessage(Long messageId, String newMessage);
    Message getMessageById(Long messageId);
    List<Message> getMessagesFromChat(Long chatId);
}
