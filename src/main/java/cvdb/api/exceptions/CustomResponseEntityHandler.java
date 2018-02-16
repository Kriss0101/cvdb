package cvdb.api.exceptions;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomResponseEntityHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleAllExceptions(Exception e, WebRequest request) {
        return  new ExceptionResponse(new Date(), e.getMessage(), request.getDescription(true));

    }
    
    @Getter
    @ToString
    private  class ExceptionResponse {
        private final Date date;
        private final String message;
        private final String description;

        public ExceptionResponse(Date date, String message, String description) {
            this.date = date;
            this.message = message;
            this.description = description;
        }
    }

}
