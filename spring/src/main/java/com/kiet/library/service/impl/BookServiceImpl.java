package com.kiet.library.service.impl;

import com.kiet.library.entity.Book;
import com.kiet.library.exception.DuplicateResourceException;
import com.kiet.library.exception.ResourceNotFoundException;
import com.kiet.library.repository.BookRepository;
import com.kiet.library.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of BookService providing business operations with database transactions.
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(Book book) {
        // Enforce unique ISBN check
        if (book.getIsbn() != null && bookRepository.existsByIsbn(book.getIsbn().trim())) {
            throw new DuplicateResourceException("A book with ISBN '" + book.getIsbn() + "' already exists.");
        }

        // Clean trimmed fields
        book.setTitle(book.getTitle().trim());
        book.setAuthor(book.getAuthor().trim());
        book.setIsbn(book.getIsbn().trim());

        return bookRepository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> searchBooks(String title, String author, String isbn, String keyword) {
        // If keyword is specified, perform multi-field search across title, author, and ISBN
        if (keyword != null && !keyword.trim().isEmpty()) {
            return bookRepository.searchByKeyword(keyword.trim());
        }

        boolean hasTitle = title != null && !title.trim().isEmpty();
        boolean hasAuthor = author != null && !author.trim().isEmpty();
        boolean hasIsbn = isbn != null && !isbn.trim().isEmpty();

        // Exact/partial individual search optimization
        if (hasTitle && !hasAuthor && !hasIsbn) {
            return bookRepository.findByTitleContainingIgnoreCase(title.trim());
        } else if (!hasTitle && hasAuthor && !hasIsbn) {
            return bookRepository.findByAuthorContainingIgnoreCase(author.trim());
        } else if (!hasTitle && !hasAuthor && hasIsbn) {
            return bookRepository.findByIsbnContainingIgnoreCase(isbn.trim());
        } else if (hasTitle || hasAuthor || hasIsbn) {
            return bookRepository.searchBooks(
                    hasTitle ? title.trim() : null,
                    hasAuthor ? author.trim() : null,
                    hasIsbn ? isbn.trim() : null
            );
        }

        // Default to returning all books if no search criteria was supplied
        return bookRepository.findAll();
    }

    @Override
    public Book updateBook(Long id, Book bookDetails) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot update. Book not found with ID: " + id));

        // Check if updating ISBN conflicts with an existing different book
        if (bookDetails.getIsbn() != null &&
                bookRepository.existsByIsbnAndIdNot(bookDetails.getIsbn().trim(), id)) {
            throw new DuplicateResourceException(
                    "Cannot update. Another book already exists with ISBN: " + bookDetails.getIsbn());
        }

        // Update book fields
        existingBook.setTitle(bookDetails.getTitle().trim());
        existingBook.setAuthor(bookDetails.getAuthor().trim());
        existingBook.setIsbn(bookDetails.getIsbn().trim());
        existingBook.setGenre(bookDetails.getGenre());
        existingBook.setPublicationYear(bookDetails.getPublicationYear());
        existingBook.setPrice(bookDetails.getPrice());
        existingBook.setTotalCopies(bookDetails.getTotalCopies());
        existingBook.setAvailableCopies(bookDetails.getAvailableCopies());

        return bookRepository.save(existingBook);
    }

    @Override
    public void deleteBook(Long id) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot delete. Book not found with ID: " + id));
        bookRepository.delete(existingBook);
    }
}
