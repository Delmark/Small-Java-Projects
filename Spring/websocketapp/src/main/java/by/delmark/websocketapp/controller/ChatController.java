package by.delmark.websocketapp.controller;

import by.delmark.websocketapp.models.Chat;
import by.delmark.websocketapp.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/chat")
public class ChatController {
    private final ChatService chatService;

    @GetMapping("/{id}")
    public ResponseEntity<Chat> getChatById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(chatService.getChatById(id));
    }

    @GetMapping
    public ResponseEntity<List<Chat>> getAllChats() {
        return ResponseEntity.ok(chatService.getAllChats());
    }

    @PostMapping
    public ResponseEntity<Void> createChat(
            @RequestBody Chat chat
    ) {
        chatService.createChat(chat.getChatName(), chat.getChatDesc());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Chat> updateChat(
            @PathVariable("id") Long chatId,
            @RequestBody Chat chat
    ) {
        return ResponseEntity.ok(chatService.editChat(chat));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAllChats(
            @PathVariable("id") Long id
    ) {
        chatService.deleteChatById(id);
        return ResponseEntity.ok().build();
    }
}
