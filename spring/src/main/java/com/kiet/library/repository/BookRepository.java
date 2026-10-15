package com.kiet.library.repository;

import com.kiet.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for Book entities.
 * Provides CRUD operations and custom search query methods.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Find books where title contains given string (case-insensitive)
    List<Book> findByTitleContainingIgnoreCase(String title);

    // Find books where author name contains given string (case-insensitive)
    List<Book> findByAuthorContainingIgnoreCase(String author);

    // Find a book by exact ISBN
    Optional<Book> findByIsbn(String isbn);

    // Find books where ISBN contains given string
    List<Book> findByIsbnContainingIgnoreCase(String isbn);

    // Check if a book with given ISBN already exists (useful for duplicate check during create/update)
    boolean existsByIsbn(String isbn);

    // Check if another book already has the same ISBN (excluding current book ID)
    boolean existsByIsbnAndIdNot(String isbn, Long id);

    // Comprehensive search across title, author, or ISBN
    @Query("SELECT b FROM Book b WHERE " +
           "(:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
           "(:author IS NULL OR LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%'))) AND " +
           "(:isbn IS NULL OR LOWER(b.isbn) LIKE LOWER(CONCAT('%', :isbn, '%')))")
    List<Book> searchBooks(@Param("title") String title,
                           @Param("author") String author,
                           @Param("isbn") String isbn);

    // Free-text query searching across title, author, or isbn
    @Query("SELECT b FROM Book b WHERE " +
           "LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(b.author) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(b.isbn) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Book> searchByKeyword(@Param("keyword") String keyword);
}
