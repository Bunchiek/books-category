package com.example.books_categories.mapper;

import com.example.books_categories.entity.Book;
import com.example.books_categories.entity.Category;
import com.example.books_categories.service.BookService;
import com.example.books_categories.service.CategoryService;
import com.example.books_categories.web.model.BookResponse;
import com.example.books_categories.web.model.UpsertBookRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class BookMapperDelegate implements BookMapper{
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;

    @Override
    public Book requestToBook(UpsertBookRequest request) {
        Book book = new Book();
        Category category = categoryService.findByCategoryName(request.getCategoryName());
        if (category == null) {
            category = new Category();
            category.setCategoryName(request.getCategoryName());
            categoryService.save(category);
        }
        book.setBookName(request.getBookName());
        book.setAuthor(request.getAuthor());
        book.setCategory(category);
        return book;
    }

    @Override
    public Book requestToBook(Long bookId, UpsertBookRequest request) {
        Book book = new Book();
        book.setBookName(request.getBookName());
        book.setAuthor(request.getAuthor());
        book.setCategory(bookService.findById(bookId).getCategory());
        book.setId(bookId);
        return book;
    }

    @Override
    public BookResponse BookToResponse(Book book) {
        BookResponse response = new BookResponse();
        response.setBookName(book.getBookName());
        response.setId(book.getId());
        response.setAuthor(book.getAuthor());
        response.setCategoryName(book.getCategory().getCategoryName());
        return response;
    }

    @Override
    public Book requestIdToBook(Long id) {
        return bookService.findById(id);
    }
}
