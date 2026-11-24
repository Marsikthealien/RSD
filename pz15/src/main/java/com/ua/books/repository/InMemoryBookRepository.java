package com.ua.books.repository;

import com.ua.books.domain.Book;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryBookRepository implements BookRepository {
    private final ConcurrentHashMap<Long, Book> storage = new ConcurrentHashMap<>();
    private final AtomicLong ids = new AtomicLong();

    @Override
    public List<Book> findAll() {
        return storage.values().stream().sorted((a, b) -> a.getId().compareTo(b.getId())).toList();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public Book save(Book entity) {
        if (entity.getId() == null) {
            entity.setId(ids.incrementAndGet());
        }
        storage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public boolean deleteById(Long id) {
        return storage.remove(id) != null;
    }
}
