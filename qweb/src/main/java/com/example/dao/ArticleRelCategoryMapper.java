package com.example.dao;

import com.example.dto.ArticleRelCategory;
import com.example.dto.ArticleRelCategoryExample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRelCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleRelCategory record);

    int insertSelective(ArticleRelCategory record);

    List<ArticleRelCategory> selectByExample(ArticleRelCategoryExample example);

    ArticleRelCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleRelCategory record);

    int updateByPrimaryKey(ArticleRelCategory record);
}