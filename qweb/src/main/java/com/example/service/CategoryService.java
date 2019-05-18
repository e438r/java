package com.example.service;

import com.example.dto.Category;
import com.example.dto.CategoryExample;

import java.util.List;

public interface CategoryService {
    void add(Category category);

    void delete(Integer id);

    void update(Category category);

    Category get(Integer id);

    List<Category> list(CategoryExample categoryExample);

    String getAllInTree();

    int selectCount(CategoryExample example);
}
