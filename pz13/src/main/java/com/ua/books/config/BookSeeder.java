package com.ua.books.config;

import com.ua.books.domain.Book;
import com.ua.books.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookSeeder implements ApplicationRunner {
    private final BookRepository repository;

    @Override
    public void run(ApplicationArguments args) {
        repository.save(new Book(null, "Clean Code", "Robert Martin", 650, 2008, "Programming", "https://example.com/clean-code.jpg"));
        repository.save(new Book(null, "Dune", "Frank Herbert", 420, 1965, "Sci-Fi", "https://example.com/dune.jpg"));
        repository.save(new Book(null, "1984", "George Orwell", 300, 1949, "Dystopia", "https://example.com/1984.jpg"));
        repository.save(new Book(null, "The Hobbit", "J. R. R. Tolkien", 380, 1937, "Fantasy", "https://example.com/hobbit.jpg"));
        repository.save(new Book(null, "Sapiens", "Yuval Harari", 520, 2011, "History", "https://example.com/sapiens.jpg"));
    }
}
