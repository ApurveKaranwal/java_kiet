package com.kiet.library.service;

import com.kiet.library.entity.Book;

import java.util.List;

/**
 * Service interface defining business logic operations for Book management.
 */
public interface BookService {

    /**
     * Operation 1: Addition - Add a new book to the library.
     * @param book book details
     * @return saved book entity with generated ID
     */
    Book createBook(Book book);

    /**
     * Operation 2: Retrieval - Retrieve all books from the library.
     * @return list of all books
     */
    List<Book> getAllBooks();

    /**
     * Operation 2: Retrieval - Retrieve a specific book by its ID.
     * @param id book ID
     * @return book entity
     */
    Book getBookById(Long id);

    /**
     * Operation 3: Search - Search for books by title, author, ISBN, or a general keyword.
     * @param title partial or complete title
     * @param author partial or complete author name
     * @param isbn partial or complete ISBN
     * @param keyword search keyword across title, author, and ISBN
     * @return list of matching books
     */
    List<Book> searchBooks(String title, String author, String isbn, String keyword);

    /**
     * Operation 4: Deletion - Delete a book record using its ID.
     * @param id book ID to delete
     */
    void deleteBook(Long id);

    /**
     * Operation 5: Update - Update the details of an existing book.
     * @param id ID of book to update
     * @param bookDetails new book details
     * @return updated book entity
     */
    Book updateBook(Long id, Book bookDetails);
}
