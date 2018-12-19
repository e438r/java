package com.example.service;

import com.example.dto.Article;
import com.example.dto.ArticleExample;

import java.util.List;

public interface ArticleService {
    List<Article> listByCategory(int cid);

    void add(Article article);

    void delete(Integer id);

    void update(Article article);

    Article get(Integer id);

    List<Article> listByExample(ArticleExample articleExample);
}
