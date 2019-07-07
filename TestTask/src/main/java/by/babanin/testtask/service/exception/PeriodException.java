package by.babanin.testtask.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class PeriodException extends RuntimeException {
    public PeriodException() {
        super();
    }

    public PeriodException(String message) {
        super(message);
    }
}
