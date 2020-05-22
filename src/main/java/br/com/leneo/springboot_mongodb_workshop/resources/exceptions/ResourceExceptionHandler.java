package br.com.leneo.springboot_mongodb_workshop.resources.exceptions;

import br.com.leneo.springboot_mongodb_workshop.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        String errorMsg = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error =
                new StandardError(Instant.now(), status.value(), errorMsg, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }
}
