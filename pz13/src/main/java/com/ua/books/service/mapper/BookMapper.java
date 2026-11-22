package com.ua.books.service.mapper;

import com.ua.books.domain.Book;
import com.ua.books.web.dto.BookCardDto;
import com.ua.books.web.dto.BookDetailDto;
import com.ua.books.web.dto.BookRequest;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public BookCardDto toCard(Book b) {
        return new BookCardDto(b.getId(), b.getTitle(), b.getAuthor(), b.getPrice());
    }

    public BookDetailDto toDetail(Book b) {
        return new BookDetailDto(b.getId(), b.getTitle(), b.getAuthor(), b.getPrice(), b.getYear(), b.getGenre(), b.getCoverUrl());
    }

    public Book toDomain(BookRequest r) {
        return new Book(null, r.title(), r.author(), r.price(), r.year(), r.genre(), r.coverUrl());
    }
}
