package com.example.books_categories.web.controller;

import com.example.books_categories.entity.Book;
import com.example.books_categories.mapper.BookMapper;
import com.example.books_categories.service.BookService;
import com.example.books_categories.web.filter.BookFilter;
import com.example.books_categories.web.model.BookResponse;
import com.example.books_categories.web.model.BooksListResponse;
import com.example.books_categories.web.model.UpsertBookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/book")
public class BookController {


    private final BookMapper bookMapper;
    private final BookService bookService;

    @GetMapping("/filter")
    public ResponseEntity<BookResponse> filterBy(BookFilter filter) {
        return ResponseEntity.ok(
                bookMapper.BookToResponse(bookService.filterByNameAndAuthor(filter))
        );
    }
    @GetMapping("/filter/category")
    public ResponseEntity<BooksListResponse> filterByCategory(BookFilter filter) {
        return ResponseEntity.ok(
                bookMapper.BooksListToBooksListResponse(bookService.findAllByCategoryName(filter))
        );
    }


    @GetMapping
    public ResponseEntity<BooksListResponse> findAll(){
        return ResponseEntity.ok(
                bookMapper.BooksListToBooksListResponse(bookService.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(bookMapper.BookToResponse(bookService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<BookResponse> create(@RequestBody UpsertBookRequest request) {
        Book newBook = bookService.save(bookMapper.requestToBook(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(bookMapper.BookToResponse(newBook));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> update(@PathVariable("id") Long bookId, @RequestBody UpsertBookRequest request) {
        Book updatedBook = bookService.update(bookMapper.requestToBook(bookId, request));
        return ResponseEntity.ok(bookMapper.BookToResponse(updatedBook));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.deleteById(bookMapper.requestIdToBook(id));
        return ResponseEntity.noContent().build();
    }
}
