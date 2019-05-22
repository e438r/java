package com.example.service;

import com.example.dto.Article;
import com.example.dto.ArticleExample;
import com.example.dto.CategoryExample;

import java.util.List;

public interface ArticleService {
    Integer add(Article article);

    void delete(Integer id);

    void update(Article article);

    Article get(Integer id);

    List<Article> list(ArticleExample articleExample);

    int selectCount(ArticleExample example);
}
