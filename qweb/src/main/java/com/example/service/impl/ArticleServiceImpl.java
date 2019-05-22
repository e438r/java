package com.example.service.impl;

import com.example.dao.ArticleMapper;
import com.example.dto.Article;
import com.example.dto.ArticleExample;
import com.example.dto.CategoryExample;
import com.example.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Integer add(Article article) {
        return articleMapper.insert(article);
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
    public List<Article> list(ArticleExample articleExample) {
        return articleMapper.selectByExample(articleExample);
    }

    @Override
    public int selectCount(ArticleExample example) {
        return articleMapper.selectCount(example);
    }
}
