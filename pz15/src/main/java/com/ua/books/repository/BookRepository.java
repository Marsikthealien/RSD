package com.ua.books.repository;

import com.ua.books.domain.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Book save(Book entity);
    boolean deleteById(Long id);
}
