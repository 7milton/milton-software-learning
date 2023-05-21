package pl.milton.controller.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApplicationDefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> defaultExceptionHandler(RuntimeException exception) {
        return ResponseEntity.internalServerError()
                .body("Caught by DefaultExceptionHandler: " + exception.getMessage());
    }
}
