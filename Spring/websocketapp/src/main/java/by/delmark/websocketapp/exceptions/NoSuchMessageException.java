package by.delmark.websocketapp.exceptions;

import org.springframework.http.HttpStatus;

public class NoSuchMessageException extends ResponseException {
    public NoSuchMessageException() {
        super("No such message", HttpStatus.NOT_FOUND);
    }
}
