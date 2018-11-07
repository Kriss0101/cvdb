package cvdb.api.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.Date;

@ControllerAdvice
public class CustomResponseEntityHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllExceptions(Exception e) {
        HttpStatus status  = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(new Date(), e.getMessage(), status), status);

    }

    @Getter
    private class ExceptionResponse {

        private final HttpStatus httpStatus;
        private final String message;
        private final Date date;

        public ExceptionResponse(Date date, String message, HttpStatus status) {
            this.date = date;
            this.message = message;
            this.httpStatus = status;

        }

    }

}
