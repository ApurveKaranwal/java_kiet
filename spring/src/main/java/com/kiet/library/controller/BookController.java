package com.kiet.library.controller;

import com.kiet.library.entity.Book;
import com.kiet.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST Controller exposing client-server HTTP endpoints for Book Management.
 * Handles JSON request bodies, HTTP verbs (POST, GET, PUT, DELETE), and returns standard HTTP status codes.
 */
@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*") // Allows CORS for front-end integration
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Operation 1: Addition - Add a new book to the library.
     * HTTP Method: POST
     * URL: /api/books
     * Accepts: JSON Request Body
     * Status: 201 Created
     */
    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdBook.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdBook);
    }

    /**
     * Operation 2a: Retrieval - Retrieve all books from the library.
     * HTTP Method: GET
     * URL: /api/books
     * Status: 200 OK
     */
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    /**
     * Operation 2b: Retrieval - Retrieve a specific book using its ID.
     * HTTP Method: GET
     * URL: /api/books/{id}
     * Status: 200 OK (or 404 Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    /**
     * Operation 3: Search - Search for books by title, author, or ISBN.
     * HTTP Method: GET
     * URL: /api/books/search?title=...&author=...&isbn=...&keyword=...
     * Query Parameters:
     *   - title (optional)
     *   - author (optional)
     *   - isbn (optional)
     *   - keyword (optional, searches across title, author, isbn)
     * Status: 200 OK
     */
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "isbn", required = false) String isbn,
            @RequestParam(value = "keyword", required = false) String keyword) {
        List<Book> results = bookService.searchBooks(title, author, isbn, keyword);
        return ResponseEntity.ok(results);
    }

    /**
     * Operation 4: Deletion - Delete a book record using its ID.
     * HTTP Method: DELETE
     * URL: /api/books/{id}
     * Status: 200 OK
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Book with ID " + id + " has been successfully deleted.");
        response.put("deletedId", id);
        return ResponseEntity.ok(response);
    }

    /**
     * Operation 5: Update - Update the details of an existing book.
     * HTTP Method: PUT
     * URL: /api/books/{id}
     * Accepts: JSON Request Body
     * Status: 200 OK (or 404 Not Found)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable("id") Long id,
            @Valid @RequestBody Book bookDetails) {
        Book updatedBook = bookService.updateBook(id, bookDetails);
        return ResponseEntity.ok(updatedBook);
    }
}
