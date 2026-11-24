package com.ua.books.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ua.books.exception.BookNotFoundException;
import com.ua.books.service.BookService;
import com.ua.books.web.dto.BookCardDto;
import com.ua.books.web.dto.BookDetailDto;
import com.ua.books.web.dto.BookRequest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BookController.class)
class BookControllerTest {
    private static final String VALID = """
            {"title":"Dune","author":"Frank Herbert","price":420,"year":1965,"genre":"Sci-Fi","coverUrl":"u"}""";
    private static final String INVALID = """
            {"title":"","author":"Frank Herbert","price":-1,"year":1965}""";

    private final BookDetailDto detail = new BookDetailDto(1L, "Dune", "Frank Herbert", 420, 1965, "Sci-Fi", "u");

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService service;

    @Test
    void listReturns200() throws Exception {
        when(service.findAll()).thenReturn(List.of(new BookCardDto(1L, "Dune", "Frank Herbert", 420)));

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Dune"));
    }

    @Test
    void getByIdReturns200() throws Exception {
        when(service.findById(1L)).thenReturn(detail);

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.genre").value("Sci-Fi"));
    }

    @Test
    void getMissingReturns404WithBody() throws Exception {
        when(service.findById(99L)).thenThrow(new BookNotFoundException(99L));

        mockMvc.perform(get("/api/books/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Book not found: 99"));
    }

    @Test
    void createReturns201() throws Exception {
        when(service.create(any(BookRequest.class))).thenReturn(detail);

        mockMvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON).content(VALID))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void createInvalidReturns400WithDetails() throws Exception {
        mockMvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON).content(INVALID))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.details").isArray())
                .andExpect(jsonPath("$.details.length()").value(2));
    }

    @Test
    void updateReturns200() throws Exception {
        when(service.update(eq(1L), any(BookRequest.class))).thenReturn(detail);

        mockMvc.perform(put("/api/books/1").contentType(MediaType.APPLICATION_JSON).content(VALID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Dune"));
    }

    @Test
    void updateMissingReturns404() throws Exception {
        when(service.update(eq(99L), any(BookRequest.class))).thenThrow(new BookNotFoundException(99L));

        mockMvc.perform(put("/api/books/99").contentType(MediaType.APPLICATION_JSON).content(VALID))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteReturns204() throws Exception {
        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteMissingReturns404() throws Exception {
        doThrow(new BookNotFoundException(99L)).when(service).delete(99L);

        mockMvc.perform(delete("/api/books/99"))
                .andExpect(status().isNotFound());
    }
}
