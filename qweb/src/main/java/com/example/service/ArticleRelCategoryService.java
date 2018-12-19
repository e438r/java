package com.example.service;

import com.example.dto.ArticleRelCategory;
import com.example.dto.ArticleRelCategoryExample;

import java.util.List;

public interface ArticleRelCategoryService {
    void add(ArticleRelCategory articleRelCategory);

    void delete(Integer id);

    List<ArticleRelCategory> list(ArticleRelCategoryExample articleRelCategoryExample);
}
