package com.kiet.library.config;

import com.kiet.library.entity.Book;
import com.kiet.library.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Initializes the database with sample book records upon application startup.
 */
@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initDatabase(BookRepository bookRepository) {
        return args -> {
            if (bookRepository.count() == 0) {
                Book b1 = new Book(
                        "Clean Code: A Handbook of Agile Software Craftsmanship",
                        "Robert C. Martin",
                        "978-0132350884",
                        "Software Engineering",
                        2008,
                        42.99,
                        10,
                        8
                );

                Book b2 = new Book(
                        "Effective Java",
                        "Joshua Bloch",
                        "978-0134685991",
                        "Programming",
                        2018,
                        47.50,
                        15,
                        12
                );

                Book b3 = new Book(
                        "Design Patterns: Elements of Reusable Object-Oriented Software",
                        "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides",
                        "978-0201633610",
                        "Software Architecture",
                        1994,
                        54.99,
                        7,
                        5
                );

                Book b4 = new Book(
                        "Spring in Action",
                        "Craig Walls",
                        "978-1617297571",
                        "Frameworks",
                        2022,
                        49.99,
                        12,
                        9
                );

                Book b5 = new Book(
                        "Introduction to Algorithms",
                        "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, Clifford Stein",
                        "978-0262046305",
                        "Computer Science",
                        2022,
                        89.00,
                        8,
                        6
                );

                bookRepository.saveAll(Arrays.asList(b1, b2, b3, b4, b5));
                System.out.println(">>> Sample library books preloaded into relational database.");
            }
        };
    }
}
