package com.example.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.example.Vo.ResultParam;
import com.example.dto.Category;
import com.example.dto.CategoryExample;
import com.example.service.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private Logger logger = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/index")
    public String Index(@ModelAttribute("model") ModelMap mm) {
        return "/category/index";
    }

    @RequestMapping("/get")
    @ResponseBody
    public Category category(Integer id) {
        Category category = categoryService.get(id);
        return category;
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<Category> categoryList(HttpServletRequest servletRequest) {
        String nodeName = servletRequest.getParameter("nodeName");
        CategoryExample example = new CategoryExample();
        CategoryExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(nodeName)) {
            criteria.andNodeNameLike(nodeName);
        }
        List<Category> categoryList = categoryService.list(example);
        return categoryList;
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResultParam add(Category category) {
        categoryService.add(category);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return resultParam;
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultParam update(Category category) {
        categoryService.update(category);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return resultParam;
    }

    @RequestMapping("/del")
    @ResponseBody
    public ResultParam delete(Integer id) {
        categoryService.delete(id);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return resultParam;
    }
}
