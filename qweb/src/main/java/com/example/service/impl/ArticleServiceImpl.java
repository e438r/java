package com.example.service.impl;

import com.example.dao.ArticleMapper;
import com.example.dto.Article;
import com.example.dto.ArticleExample;
import com.example.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Article> listByCategory(int cid) {
        return null;
    }

    @Override
    public void add(Article article) {
articleMapper.insert(article);
    }

    @Override
    public void delete(Integer id) {
articleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Article article) {
articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public Article get(Integer id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Article> listByExample(ArticleExample articleExample) {
        return articleMapper.selectByExample(articleExample);
    }
}
