package com.ua.books.web.dto;

public record BookRequest(String title, String author, double price, int year, String genre, String coverUrl) {}
