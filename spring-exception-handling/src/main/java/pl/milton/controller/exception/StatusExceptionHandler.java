package pl.milton.controller.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.milton.controller.StatusController;
import pl.milton.model.Status;
import pl.milton.model.exception.ApiUnstableException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(assignableTypes = StatusController.class)
public class StatusExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleUnauthorized(IllegalStateException exception) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("You are unauthorized! " + exception.getMessage());
    }

    @ExceptionHandler(ApiUnstableException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public Status customExceptionHandler() {
        return Status.builder().status("Resource is not available at the moment!").build();
    }

}
