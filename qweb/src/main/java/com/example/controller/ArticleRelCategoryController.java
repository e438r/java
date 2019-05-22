package com.example.controller;

import com.example.Vo.ResultParam;
import com.example.dto.ArticleRelCategory;
import com.example.service.ArticleRelCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rel")
public class ArticleRelCategoryController {
    private Logger logger = LoggerFactory.getLogger(ArticleRelCategoryController.class);
    @Autowired
    private ArticleRelCategoryService articleRelCategoryService;

    @RequestMapping("/get")
    @ResponseBody
    public ArticleRelCategory category(Integer id) {
        ArticleRelCategory category = articleRelCategoryService.get(id);
        return category;
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResultParam add(ArticleRelCategory articleRelCategory) {
        articleRelCategoryService.add(articleRelCategory);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return resultParam;
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultParam update(ArticleRelCategory articleRelCategory) {
        articleRelCategoryService.update(articleRelCategory);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return resultParam;
    }
}
