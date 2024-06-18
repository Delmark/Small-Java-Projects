package by.delmark.websocketapp.service;

import by.delmark.websocketapp.models.Chat;

import java.util.List;

public interface ChatService {
    void createChat(String chatName, String chatDescription);

    Chat editChat(Chat chat);

    Chat getChatById(Long chatId);

    List<Chat> getAllChats();

    Integer getChatCount();

    void deleteChatById(Long chatId);
}
