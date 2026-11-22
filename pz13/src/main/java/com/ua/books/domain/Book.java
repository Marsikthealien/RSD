package com.ua.books.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Book {
    private Long id;
    private String title;
    private String author;
    private double price;
    private int year;
    private String genre;
    private String coverUrl;
}
