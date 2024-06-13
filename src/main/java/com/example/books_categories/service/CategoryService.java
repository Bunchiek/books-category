package com.example.books_categories.service;

import com.example.books_categories.entity.Category;

public interface CategoryService {
    Category save(Category category);
    Category findByCategoryName(String categoryName);
}
