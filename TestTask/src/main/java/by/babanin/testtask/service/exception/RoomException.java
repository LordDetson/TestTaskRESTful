package by.babanin.testtask.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class RoomException extends RuntimeException {
    public RoomException() {
        super();
    }

    public RoomException(String message) {
        super(message);
    }
}
