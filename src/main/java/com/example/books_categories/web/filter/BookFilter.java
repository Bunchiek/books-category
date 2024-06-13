package com.example.books_categories.web.filter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookFilter {

    private String bookName;
    private String author;
    private String categoryName;
}
