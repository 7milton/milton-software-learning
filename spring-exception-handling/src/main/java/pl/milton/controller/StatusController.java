package pl.milton.controller;

import org.springframework.expression.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.milton.model.Status;
import pl.milton.model.exception.ApiUnstableException;

@RestController("/status")
public class StatusController {

    @GetMapping("/ok")
    public ResponseEntity<Status> okStatus() {
        return ResponseEntity.ok(Status.builder().status("OK").build());
    }

    @GetMapping("/notFound")
    public ResponseEntity<Object> notFoundStatus() {
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/unauthorized")
    public ResponseEntity<Object> unauthorizedException() {
        throw new IllegalStateException("Go away!");
    }

    @GetMapping("/customException")
    public ResponseEntity<Object> customException() {
        throw new ApiUnstableException("Custom exception information");
    }

    @GetMapping("/unhandledException")
    public ResponseEntity<Object> unhandledException() {
        throw new ParseException(0, "Given format is wrong!");
    }

}
