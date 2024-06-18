package by.delmark.websocketapp.service;

import by.delmark.websocketapp.exceptions.NoSuchMessageException;
import by.delmark.websocketapp.exceptions.ResponseException;
import by.delmark.websocketapp.models.Message;
import by.delmark.websocketapp.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public void createMessage(Long chatId, String message) {
        Message newMessage = new Message();
        newMessage.setChatId(chatId);
        newMessage.setMessageText(message);
        messageRepository.save(newMessage);
    }

    @Override
    public void deleteMessageById(Long messageId) {
        messageRepository.deleteById(messageId);
    }

    @Override
    public Message editMessage(Long messageId, String newMessage) {
        Message message = messageRepository.getById(messageId).orElseThrow(NoSuchMessageException::new);
        message.setMessageText(newMessage);
        messageRepository.update(message);
        return messageRepository.getById(messageId).orElseThrow(ResponseException::new);
    }

    @Override
    public Message getMessageById(Long messageId) {
        return messageRepository.getById(messageId).orElseThrow(NoSuchMessageException::new);
    }

    @Override
    public List<Message> getMessagesFromChat(Long chatId) {
        return messageRepository.getAllByChatId(chatId);
    }
}
