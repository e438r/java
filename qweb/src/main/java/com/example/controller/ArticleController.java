package com.example.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.example.Vo.ResultParam;
import com.example.dto.Article;
import com.example.dto.ArticleExample;
import com.example.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {
    private Logger logger = LoggerFactory.getLogger(ArticleController.class);
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/index")
    public String Index(@ModelAttribute("model") ModelMap mm) {
        return "/article/index";
    }

    @RequestMapping("/get")
    @ResponseBody
    public String article(Integer id) {
        Article article = articleService.get(id);
        return JSONUtils.toJSONString(article);
    }

    @RequestMapping("/list")
    @ResponseBody
    public String articleList(Article article) {
        ArticleExample example = new ArticleExample();
        ArticleExample.Criteria criteria = example.createCriteria();

        List<Article> articleList = articleService.list(example);
        return JSONUtils.toJSONString(articleList);
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add(Article article) {
        articleService.add(article);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return JSONUtils.toJSONString(resultParam);
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(Article article) {
        articleService.update(article);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return JSONUtils.toJSONString(resultParam);
    }

    @RequestMapping("/del")
    @ResponseBody
    public String delete(Integer id) {
        articleService.delete(id);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return JSONUtils.toJSONString(resultParam);
    }
}
