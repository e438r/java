package com.example.service;

import com.example.dto.ArticleRelCategory;
import com.example.dto.ArticleRelCategoryExample;

import java.util.List;

public interface ArticleRelCategoryService {
    ArticleRelCategory get(Integer id);

    void add(ArticleRelCategory articleRelCategory);

    void update(ArticleRelCategory articleRelCategory);

    void delete(Integer id);

    List<ArticleRelCategory> list(ArticleRelCategoryExample articleRelCategoryExample);
}
