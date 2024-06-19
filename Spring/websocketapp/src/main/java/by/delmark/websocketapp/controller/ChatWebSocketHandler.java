package by.delmark.websocketapp.controller;

import by.delmark.websocketapp.exceptions.ErrorMessage;
import by.delmark.websocketapp.models.Chat;
import by.delmark.websocketapp.models.Message;
import by.delmark.websocketapp.models.WebSocketEvents.EventType;
import by.delmark.websocketapp.models.WebSocketEvents.WebSocketEvent;
import by.delmark.websocketapp.models.WebSocketEvents.WebSocketEventResponse;
import by.delmark.websocketapp.service.ChatService;
import by.delmark.websocketapp.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
@Slf4j
public class ChatWebSocketHandler implements WebSocketHandler {

    private final ChatService chatService;
    private final MessageService messageService;
    private final ObjectMapper mapper = new ObjectMapper();
    private final Map<String, Long> sessionCurrentChat = new HashMap<>();
    private final Map<String, WebSocketSession> listeners = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Connection with {} established", session.getId());
        listeners.put(session.getId(), session);
        sessionCurrentChat.put(session.getId(), null);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        WebSocketEvent webSocketEvent;

        try {
            webSocketEvent = mapper.readValue(message.getPayload().toString(), WebSocketEvent.class);
        }
        catch (Exception e) {
            log.error("Incorrect message format", e);
            log.error("Message: {}", message.getPayload().toString());
            session.sendMessage(new TextMessage(mapper.writeValueAsString(new WebSocketEventResponse(EventType.ERROR_RESPONSE, "Incorrect message format"))));
            return;
        }

        switch (webSocketEvent.getEventType()) {
            case MESSAGE_POST -> {
                try {
                    messagePostHandler(session, webSocketEvent);
                }
                catch (Exception e) {
                    log.error("Exception while processing message", e);
                    session.sendMessage(new TextMessage(mapper.writeValueAsString(new WebSocketEventResponse(EventType.ERROR_RESPONSE, "Exception while processing message"))));
                }
            }
            case CHAT_LIST_REQUEST -> {
                List<Chat> chats = chatService.getAllChats();
                session.sendMessage(new TextMessage(mapper.writeValueAsString(
                        new WebSocketEventResponse(
                                EventType.CHAT_LIST_RESPONSE,
                                chats)
                        )));
            }
            case CHAT_CONNECTION_REQUEST -> {
                try {
                    chatConnectionRequestHandler(session, webSocketEvent);
                } catch (Exception e) {
                    log.error("Exception while processing message", e);
                    session.sendMessage(new TextMessage(mapper.writeValueAsString(new WebSocketEventResponse(EventType.ERROR_RESPONSE, "Exception while processing message"))));
                }
            }
            case null, default -> {
                session.sendMessage(new TextMessage(mapper.writeValueAsString(new WebSocketEventResponse(EventType.ERROR_RESPONSE, "Unknown event type"))));
            }
        }
    }

    private void chatConnectionRequestHandler(WebSocketSession session, WebSocketEvent webSocketEvent) throws IOException {
        Long chatId = Long.parseLong(webSocketEvent.getPayload().get("chatId"));

        sessionCurrentChat.put(session.getId(), chatId);

        List<Message> chatMessages = messageService.getMessagesFromChat(chatId);


        session.sendMessage(new TextMessage(mapper.writeValueAsString(
                new WebSocketEventResponse(
                        EventType.CHAT_CONNECTION_RESPONSE,
                        chatMessages)
        )));
    }

    public void messagePostHandler(WebSocketSession session, WebSocketEvent event) throws IOException {
        if (sessionCurrentChat.get(session.getId()) == null) {
            session.sendMessage(new TextMessage(mapper.writeValueAsString(new WebSocketEventResponse(EventType.ERROR_RESPONSE, "No chat selected"))));
            return;
        }

        HashMap<String, String> messageEventPayload = event.getPayload();
        Long chatId = sessionCurrentChat.get(session.getId());
        messageService.createMessage(chatId, messageEventPayload.get("message"));

        for (String sessionId : sessionCurrentChat.keySet()) {
            if (sessionCurrentChat.get(sessionId).equals(chatId)) {
                listeners.get(sessionId).sendMessage(new TextMessage(mapper.writeValueAsString(
                        new WebSocketEventResponse(EventType.MESSAGE_LIST_UPDATE_RESPONSE, messageService.getMessagesFromChat(chatId))
                )));
            }
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("Session {} error: {}", session.getId(), exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.info("Connection with {} closed with {} status", session.getId(), closeStatus);
        sessionCurrentChat.remove(session.getId());
        listeners.remove(session.getId());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
