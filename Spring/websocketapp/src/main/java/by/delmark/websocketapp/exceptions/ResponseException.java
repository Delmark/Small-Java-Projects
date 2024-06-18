package by.delmark.websocketapp.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResponseException extends RuntimeException{
    String message = "Internal server error";
    HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
}
