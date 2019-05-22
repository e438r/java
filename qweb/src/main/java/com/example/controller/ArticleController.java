package com.example.controller;

import com.example.Vo.ArticleVo;
import com.example.Vo.CategoryVo;
import com.example.Vo.ResultParam;
import com.example.dto.Article;
import com.example.dto.ArticleExample;
import com.example.dto.Category;
import com.example.service.ArticleService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    public Article article(Integer id) {
        Article article = articleService.get(id);
        return article;
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<ArticleVo> articleList(HttpServletRequest servletRequest) {
        ArticleExample example = new ArticleExample();
        ArticleExample.Criteria criteria = example.createCriteria();
        String title = servletRequest.getParameter("title");
        if (StringUtils.isNotBlank(title)) {
            criteria.andTitleLike(title);
        }
        String pageSize = servletRequest.getParameter("pagesize");
        String startIndex = servletRequest.getParameter("recordstartindex");
        example.setLimit(startIndex + "," + pageSize);
        List<Article> articleList = articleService.list(example);
        int pageCount = articleService.selectCount(example);
        List<ArticleVo> articleVoList = new ArrayList<>();
        ArticleVo articleVo;
        for (Article article : articleList) {
            articleVo = new ArticleVo();
            BeanUtils.copyProperties(article, articleVo);
            articleVoList.add(articleVo);
        }
        articleVoList.get(0).setCount(pageCount);
        return articleVoList;
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResultParam add(Article article) {
        Integer id = articleService.add(article);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage(id.toString());
        return resultParam;
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultParam update(Article article) {
        articleService.update(article);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return resultParam;
    }

    @RequestMapping("/del")
    @ResponseBody
    public ResultParam delete(Integer id) {
        articleService.delete(id);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return resultParam;
    }
}
