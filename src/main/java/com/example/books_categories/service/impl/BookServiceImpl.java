package com.example.books_categories.service.impl;

import com.example.books_categories.configuration.properties.AppCacheProperties;
import com.example.books_categories.entity.Book;
import com.example.books_categories.repository.BookRepository;
import com.example.books_categories.repository.BookSpecification;
import com.example.books_categories.service.BookService;
import com.example.books_categories.service.CategoryService;
import com.example.books_categories.util.BeanUtils;
import com.example.books_categories.web.filter.BookFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheManager = "redisCacheManager")
public class BookServiceImpl implements BookService {


    private final BookRepository repository;


    private final CategoryService categoryService;

    @Override
    @Cacheable(value = AppCacheProperties.CacheNames.BOOKS_BY_CATEGORY, key = "#filter.categoryName")
    public List<Book> findAllByCategoryName(BookFilter filter) {
        return repository.findAll(BookSpecification.withFilter(filter));
    }

    @Override
    @Cacheable(value = AppCacheProperties.CacheNames.BOOK_BY_NAME_AND_AUTHOR, key = "#filter.bookName + #filter.author")
    public Book filterByNameAndAuthor(BookFilter filter) {
        return repository.findAll(BookSpecification.withFilter(filter)).getFirst();
    }

    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Book save(Book book) {
        categoryService.save(book.getCategory());
        return repository.save(book);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = AppCacheProperties.CacheNames.BOOK_BY_NAME_AND_AUTHOR, key = "#book.bookName + #book.author", beforeInvocation = true),
            @CacheEvict(cacheNames = AppCacheProperties.CacheNames.BOOKS_BY_CATEGORY, key = "#book.category.categoryName", beforeInvocation = true)
    })
    @Override
    public Book update(Book book) {
        return repository.save(book);
    }
    @Caching(evict = {
            @CacheEvict(cacheNames = AppCacheProperties.CacheNames.BOOK_BY_NAME_AND_AUTHOR, key = "#book.bookName + #book.author", beforeInvocation = true),
            @CacheEvict(cacheNames = AppCacheProperties.CacheNames.BOOKS_BY_CATEGORY, key = "#book.category.categoryName", beforeInvocation = true)
    })
    @Override
    public void deleteById(Book book) {
        repository.deleteById(book.getId());
    }
}
