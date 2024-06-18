package by.delmark.websocketapp.controller;

import by.delmark.websocketapp.models.Message;
import by.delmark.websocketapp.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/message")
public class MessageController {
    private final MessageService messageService;


    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(
            @PathVariable("id") Long id
    ){
        return ResponseEntity.ok(messageService.getMessageById(id));
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<Message>> getMessagesFromChat(
            @PathVariable("chatId") Long chatId
    ) {
        return ResponseEntity.ok(messageService.getMessagesFromChat(chatId));
    }

    @PostMapping
    public ResponseEntity<Void> createMessage(
            @RequestBody Message message
    ) {
        messageService.createMessage(message.getChatId(), message.getMessageText());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(
            @RequestBody Message message
    ) {
        return ResponseEntity.ok(messageService.editMessage(message.getMessageId(), message.getMessageText()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessageById(
            @PathVariable("id") Long id
    ) {
        messageService.deleteMessageById(id);
        return ResponseEntity.ok().build();
    }
}
