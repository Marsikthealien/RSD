package com.ua.books.web.dto;

public record BookDetailDto(Long id, String title, String author, double price, int year, String genre, String coverUrl) {}
