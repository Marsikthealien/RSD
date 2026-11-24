package com.ua.books.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ua.books.domain.Book;
import com.ua.books.exception.BookNotFoundException;
import com.ua.books.repository.BookRepository;
import com.ua.books.service.mapper.BookMapper;
import com.ua.books.web.dto.BookCardDto;
import com.ua.books.web.dto.BookDetailDto;
import com.ua.books.web.dto.BookRequest;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class BookServiceImplTest {
    private final BookRepository repository = mock(BookRepository.class);
    private final BookMapper mapper = mock(BookMapper.class);
    private final BookService service = new BookServiceImpl(repository, mapper);

    private final Book book = new Book(1L, "Dune", "Frank Herbert", 420, 1965, "Sci-Fi", "u");
    private final BookDetailDto detail = new BookDetailDto(1L, "Dune", "Frank Herbert", 420, 1965, "Sci-Fi", "u");

    @Test
    void findAllReturnsCards() {
        BookCardDto card = new BookCardDto(1L, "Dune", "Frank Herbert", 420);
        when(repository.findAll()).thenReturn(List.of(book));
        when(mapper.toCard(book)).thenReturn(card);

        assertThat(service.findAll()).containsExactly(card);
    }

    @Test
    void findByIdReturnsDetail() {
        when(repository.findById(1L)).thenReturn(Optional.of(book));
        when(mapper.toDetail(book)).thenReturn(detail);

        assertThat(service.findById(1L)).isEqualTo(detail);
    }

    @Test
    void findByIdThrowsWhenMissing() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findById(99L)).isInstanceOf(BookNotFoundException.class);
    }

    @Test
    void createSavesAndReturnsWithId() {
        BookRequest request = new BookRequest("Dune", "Frank Herbert", 420, 1965, "Sci-Fi", "u");
        Book unsaved = new Book(null, "Dune", "Frank Herbert", 420, 1965, "Sci-Fi", "u");
        when(mapper.toDomain(request)).thenReturn(unsaved);
        when(repository.save(unsaved)).thenReturn(book);
        when(mapper.toDetail(book)).thenReturn(detail);

        assertThat(service.create(request).id()).isEqualTo(1L);
        verify(repository).save(unsaved);
    }
}
