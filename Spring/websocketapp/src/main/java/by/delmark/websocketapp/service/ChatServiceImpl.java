package by.delmark.websocketapp.service;

import by.delmark.websocketapp.exceptions.NoSuchChatException;
import by.delmark.websocketapp.models.Chat;
import by.delmark.websocketapp.repository.ChatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    @Override
    public void createChat(String chatName, String chatDescription) {
        if (chatRepository.existsByChatName(chatName)) {
            throw new IllegalArgumentException("Chat with name " + chatName + " already exists");
        }

        Chat chat = new Chat();
        chat.setChatName(chatName);
        chat.setChatDesc(chatDescription);
        chatRepository.save(chat);
    }

    @Override
    public Chat editChat(Chat chat) {
        chatRepository.update(chat);
        return chatRepository.getChatById(chat.getChatId()).orElseThrow(NoSuchChatException::new);
    }

    @Override
    public Chat getChatById(Long chatId) {
        return chatRepository.getChatById(chatId).orElseThrow(NoSuchChatException::new);
    }

    @Override
    public List<Chat> getAllChats() {
        return chatRepository.getAll();
    }

    @Override
    public Integer getChatCount() {
        return chatRepository.getChatCount();
    }

    @Override
    public void deleteChatById(Long chatId) {
        chatRepository.deleteById(chatId);
    }
}
