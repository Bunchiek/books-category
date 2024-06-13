package com.example.books_categories.service;

import com.example.books_categories.entity.Book;
import com.example.books_categories.web.filter.BookFilter;

import java.util.List;

public interface BookService {
    List<Book> findAllByCategoryName(BookFilter filter);
    Book filterByNameAndAuthor(BookFilter filter);
    List<Book> findAll();
    Book findById(Long id);
    Book save(Book book);
    Book update(Book book);
    void deleteById(Book book);
}
