package com.ua.books.service;

import com.ua.books.web.dto.BookCardDto;
import com.ua.books.web.dto.BookDetailDto;
import com.ua.books.web.dto.BookRequest;
import java.util.List;

public interface BookService {
    List<BookCardDto> findAll();
    BookDetailDto findById(Long id);
    BookDetailDto create(BookRequest request);
    BookDetailDto update(Long id, BookRequest request);
    void delete(Long id);
}
