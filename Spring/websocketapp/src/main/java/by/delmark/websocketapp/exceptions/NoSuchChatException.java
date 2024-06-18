package by.delmark.websocketapp.exceptions;

import org.springframework.http.HttpStatus;

public class NoSuchChatException extends ResponseException {
    public NoSuchChatException() {
        super("No such chat", HttpStatus.NOT_FOUND);
    }
}
