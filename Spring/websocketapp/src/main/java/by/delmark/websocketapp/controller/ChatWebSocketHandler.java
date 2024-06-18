package by.delmark.websocketapp.controller;

import by.delmark.websocketapp.exceptions.ErrorMessage;
import by.delmark.websocketapp.models.Message;
import by.delmark.websocketapp.service.ChatService;
import by.delmark.websocketapp.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
@Slf4j
public class ChatWebSocketHandler implements WebSocketHandler {

    private final ChatService chatService;
    private final MessageService messageService;
    private final ObjectMapper mapper = new ObjectMapper();
    private final Set<WebSocketSession> activeSessions;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Connection with id {} established", session.getId());
        activeSessions.add(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.info("Message received from session with id {}: {}", session.getId(), message.getPayload());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("Transport error in session with id {}: {}", session.getId(), exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.info("Connection with id {} closed", session.getId());
        activeSessions.remove(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
