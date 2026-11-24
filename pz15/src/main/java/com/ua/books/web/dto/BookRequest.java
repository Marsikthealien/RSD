package com.ua.books.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record BookRequest(
        @NotBlank String title,
        @NotBlank String author,
        @Positive double price,
        @Positive int year,
        String genre,
        String coverUrl) {}
