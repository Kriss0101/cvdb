package cvdb.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UpdateResourceException extends RuntimeException {
    public UpdateResourceException(String message) {
        super(message);
    }
}
