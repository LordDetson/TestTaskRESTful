package by.babanin.testtask.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class PriceException extends RuntimeException {
    public PriceException() {
        super();
    }

    public PriceException(String message) {
        super(message);
    }
}
