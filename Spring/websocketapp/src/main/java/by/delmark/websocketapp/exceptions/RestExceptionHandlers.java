package by.delmark.websocketapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandlers {

    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<ErrorMessage> handleResponseException(ResponseException e) {
        return ResponseEntity.status(e.httpStatus).body(
                new ErrorMessage(
                        e.getMessage(),
                        e.getHttpStatus().value()
                )
        );
    }
}
