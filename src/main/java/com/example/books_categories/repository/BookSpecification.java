package com.example.books_categories.repository;

import com.example.books_categories.entity.Book;
import com.example.books_categories.web.filter.BookFilter;
import org.springframework.data.jpa.domain.Specification;

public interface BookSpecification {

    static Specification<Book> withFilter(BookFilter filter) {
        return Specification.where(byBookName(filter.getBookName()))
                .and(byAuthor(filter.getAuthor()))
                .and(byCategoryName(filter.getCategoryName()));
    }

    static Specification<Book> byCategoryName(String categoryName) {
        return ((root, query, criteriaBuilder) -> {
            if(categoryName == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("category").get("categoryName"), categoryName);
        });
    }

    static Specification<Book> byBookName(String bookName) {
        return ((root, query, criteriaBuilder) -> {
            if(bookName == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("bookName"), bookName);
        });
    }

    static Specification<Book> byAuthor(String author) {
        return ((root, query, criteriaBuilder) -> {
            if(author == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("author"), author);
        });
    }
}
