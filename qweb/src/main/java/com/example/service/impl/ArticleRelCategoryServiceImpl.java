package com.example.service.impl;

import com.example.dao.ArticleRelCategoryMapper;
import com.example.dto.ArticleRelCategory;
import com.example.dto.ArticleRelCategoryExample;
import com.example.service.ArticleRelCategoryService;
import org.checkerframework.checker.compilermsgs.qual.CompilerMessageKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleRelCategoryServiceImpl implements ArticleRelCategoryService {
    @Autowired
    private ArticleRelCategoryMapper articleRelCategoryMapper;

    @Override
    public void add(ArticleRelCategory articleRelCategory) {
        articleRelCategoryMapper.insert(articleRelCategory);
    }

    @Override
    public void delete(Integer id) {
        articleRelCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<ArticleRelCategory> list(ArticleRelCategoryExample articleRelCategoryExample) {
        return articleRelCategoryMapper.selectByExample(articleRelCategoryExample);
    }
}
