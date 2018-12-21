package com.example.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.example.Vo.ResultParam;
import com.example.dto.Category;
import com.example.dto.CategoryExample;
import com.example.service.CategoryService;
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
    public String category(Integer id) {
        Category category = categoryService.get(id);
        return JSONUtils.toJSONString(category);
    }

    @RequestMapping("/list")
    @ResponseBody
    public String categoryList(Category category) {
        CategoryExample example = new CategoryExample();
        CategoryExample.Criteria criteria = example.createCriteria();

        List<Category> categoryList = categoryService.list(example);
        return JSONUtils.toJSONString(categoryList);
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add(Category category) {
        categoryService.add(category);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return JSONUtils.toJSONString(resultParam);
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(Category category) {
        categoryService.update(category);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return JSONUtils.toJSONString(resultParam);
    }

    @RequestMapping("/del")
    @ResponseBody
    public String delete(Integer id) {
        categoryService.delete(id);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return JSONUtils.toJSONString(resultParam);
    }
}
