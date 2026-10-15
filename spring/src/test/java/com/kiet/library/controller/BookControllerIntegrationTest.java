package com.kiet.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiet.library.entity.Book;
import com.kiet.library.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * End-to-end integration tests for BookController REST APIs.
 * Verifies all 5 core operations: Addition, Retrieval, Search, Deletion, and Update.
 */
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookRepository bookRepository;

    private Book testBook;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();

        testBook = new Book(
                "Clean Code",
                "Robert C. Martin",
                "978-0132350884",
                "Software Engineering",
                2008,
                45.0,
                10,
                8
        );
        testBook = bookRepository.save(testBook);
    }

    // =========================================================================
    // 1. ADDITION TESTS
    // =========================================================================

    @Test
    @DisplayName("Operation 1: Should add a new book and return 201 Created")
    void testAddBook_Success() throws Exception {
        Book newBook = new Book(
                "Effective Java 3rd Edition",
                "Joshua Bloch",
                "978-0134685991",
                "Programming",
                2018,
                48.0,
                15,
                15
        );

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newBook)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.title").value("Effective Java 3rd Edition"))
                .andExpect(jsonPath("$.author").value("Joshua Bloch"))
                .andExpect(jsonPath("$.isbn").value("978-0134685991"));

        assertEquals(2, bookRepository.count());
    }

    @Test
    @DisplayName("Operation 1 Validation: Should return 400 Bad Request when adding book with invalid/missing fields")
    void testAddBook_ValidationError() throws Exception {
        Book invalidBook = new Book(); // missing title, author, isbn, price, etc.

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidBook)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Validation Failed"))
                .andExpect(jsonPath("$.details").isArray());
    }

    @Test
    @DisplayName("Operation 1 Duplicate Check: Should return 409 Conflict when adding book with duplicate ISBN")
    void testAddBook_DuplicateIsbn() throws Exception {
        Book duplicateBook = new Book(
                "Another Clean Code",
                "Uncle Bob",
                "978-0132350884", // Same ISBN as testBook
                "Software Engineering",
                2009,
                40.0,
                5,
                5
        );

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(duplicateBook)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value(containsString("already exists")));
    }

    // =========================================================================
    // 2. RETRIEVAL TESTS
    // =========================================================================

    @Test
    @DisplayName("Operation 2a: Should retrieve all books with 200 OK")
    void testGetAllBooks() throws Exception {
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title").value("Clean Code"));
    }

    @Test
    @DisplayName("Operation 2b: Should retrieve specific book by ID with 200 OK")
    void testGetBookById_Success() throws Exception {
        mockMvc.perform(get("/api/books/{id}", testBook.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testBook.getId()))
                .andExpect(jsonPath("$.title").value("Clean Code"))
                .andExpect(jsonPath("$.author").value("Robert C. Martin"));
    }

    @Test
    @DisplayName("Operation 2b: Should return 404 Not Found when retrieving non-existent book ID")
    void testGetBookById_NotFound() throws Exception {
        mockMvc.perform(get("/api/books/99999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message").value(containsString("Book not found with ID: 99999")));
    }

    // =========================================================================
    // 3. SEARCH TESTS
    // =========================================================================

    @Test
    @DisplayName("Operation 3: Should search books by title")
    void testSearchBooks_ByTitle() throws Exception {
        mockMvc.perform(get("/api/books/search")
                        .param("title", "clean"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].isbn").value("978-0132350884"));
    }

    @Test
    @DisplayName("Operation 3: Should search books by author")
    void testSearchBooks_ByAuthor() throws Exception {
        mockMvc.perform(get("/api/books/search")
                        .param("author", "martin"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].author").value("Robert C. Martin"));
    }

    @Test
    @DisplayName("Operation 3: Should search books by ISBN")
    void testSearchBooks_ByIsbn() throws Exception {
        mockMvc.perform(get("/api/books/search")
                        .param("isbn", "978-0132350884"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title").value("Clean Code"));
    }

    @Test
    @DisplayName("Operation 3: Should search books by multi-field keyword")
    void testSearchBooks_ByKeyword() throws Exception {
        mockMvc.perform(get("/api/books/search")
                        .param("keyword", "Robert"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].author").value("Robert C. Martin"));
    }

    // =========================================================================
    // 4. UPDATE TESTS
    // =========================================================================

    @Test
    @DisplayName("Operation 5: Should update book details successfully and return 200 OK")
    void testUpdateBook_Success() throws Exception {
        Book updatePayload = new Book(
                "Clean Code (Revised Edition)",
                "Robert C. Martin",
                "978-0132350884",
                "Software Craftsmanship",
                2010,
                55.0,
                20,
                18
        );

        mockMvc.perform(put("/api/books/{id}", testBook.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatePayload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testBook.getId()))
                .andExpect(jsonPath("$.title").value("Clean Code (Revised Edition)"))
                .andExpect(jsonPath("$.price").value(55.0))
                .andExpect(jsonPath("$.availableCopies").value(18));

        Book updated = bookRepository.findById(testBook.getId()).orElseThrow();
        assertEquals("Clean Code (Revised Edition)", updated.getTitle());
    }

    @Test
    @DisplayName("Operation 5: Should return 404 Not Found when updating non-existent book ID")
    void testUpdateBook_NotFound() throws Exception {
        Book updatePayload = new Book(
                "Non-existent Book",
                "Unknown Author",
                "978-9999999999",
                "General",
                2020,
                19.99,
                5,
                5
        );

        mockMvc.perform(put("/api/books/99999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatePayload)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Not Found"));
    }

    // =========================================================================
    // 5. DELETION TESTS
    // =========================================================================

    @Test
    @DisplayName("Operation 4: Should delete book record by ID and return 200 OK")
    void testDeleteBook_Success() throws Exception {
        mockMvc.perform(delete("/api/books/{id}", testBook.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.deletedId").value(testBook.getId()));

        assertFalse(bookRepository.existsById(testBook.getId()));
    }

    @Test
    @DisplayName("Operation 4: Should return 404 Not Found when deleting non-existent book ID")
    void testDeleteBook_NotFound() throws Exception {
        mockMvc.perform(delete("/api/books/99999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Not Found"));
    }
}
