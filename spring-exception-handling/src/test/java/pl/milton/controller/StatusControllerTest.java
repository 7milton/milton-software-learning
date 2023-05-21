package pl.milton.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.milton.model.exception.ApiUnstableException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StatusController.class)
class StatusControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void givenEndpoint_whenRequest_thenReturnOk() throws Exception {
        mvc.perform(get("/ok"))
                .andExpect(status().isOk());
    }

    @Test
    void givenEndpoint_whenRequest_thenReturnNotFound() throws Exception {
        mvc.perform(get("/notFound"))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenEndpoint_whenRequest_thenReturnNotFoundUnauthorized() throws Exception {
        mvc.perform(get("/unauthorized"))
                .andExpect(status().isUnauthorized())
                .andExpect(result -> assertEquals("You are unauthorized! Go away!", result.getResponse().getContentAsString()))
                .andExpect(result -> assertEquals("Go away!", result.getResolvedException().getMessage()));
    }

    @Test
    void givenEndpoint_whenRequest_thenReturnCustomException() throws Exception {
        mvc.perform(get("/customException"))
                .andExpect(status().isConflict())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("Resource is not available at the moment!"))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ApiUnstableException))
                .andExpect(result -> assertEquals("Custom exception information", result.getResolvedException().getMessage()));
    }

    @Test
    void givenEndpoint_whenRequest_thenReturnDefaultExceptionHandlerMessage() throws Exception {
        mvc.perform(get("/unhandledException"))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> assertEquals("Caught by DefaultExceptionHandler: Given format is wrong!", result.getResponse().getContentAsString()));
    }

}