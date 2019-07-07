package by.babanin.testtask.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class WorkScheduleException extends RuntimeException {
    public WorkScheduleException() {
        super();
    }

    public WorkScheduleException(String message) {
        super(message);
    }
}
