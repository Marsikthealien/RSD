package com.ua.books.service;

import com.ua.books.domain.Book;
import com.ua.books.exception.BookNotFoundException;
import com.ua.books.repository.BookRepository;
import com.ua.books.service.mapper.BookMapper;
import com.ua.books.web.dto.BookCardDto;
import com.ua.books.web.dto.BookDetailDto;
import com.ua.books.web.dto.BookRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository repository;
    private final BookMapper mapper;

    @Override
    public List<BookCardDto> findAll() {
        return repository.findAll().stream().map(mapper::toCard).toList();
    }

    @Override
    public BookDetailDto findById(Long id) {
        return mapper.toDetail(repository.findById(id).orElseThrow(() -> new BookNotFoundException(id)));
    }

    @Override
    public BookDetailDto create(BookRequest request) {
        return mapper.toDetail(repository.save(mapper.toDomain(request)));
    }

    @Override
    public BookDetailDto update(Long id, BookRequest request) {
        if (repository.findById(id).isEmpty()) {
            throw new BookNotFoundException(id);
        }
        Book book = mapper.toDomain(request);
        book.setId(id);
        return mapper.toDetail(repository.save(book));
    }

    @Override
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new BookNotFoundException(id);
        }
    }
}
