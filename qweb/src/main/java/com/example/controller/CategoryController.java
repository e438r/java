package com.example.controller;

import com.example.Vo.CategoryVo;
import com.example.Vo.ResultParam;
import com.example.dto.Category;
import com.example.dto.CategoryExample;
import com.example.service.CategoryService;
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

    /**
     * http://localhost:8080/category/list?nodeName=&filterscount=0&groupscount=0&pagenum=0&pagesize=10&recordstartindex=0&recordendindex=10&_=1558145404596
     *
     * @param servletRequest
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<CategoryVo> categoryList(HttpServletRequest servletRequest) {
        String nodeName = servletRequest.getParameter("nodeName");
        CategoryExample example = new CategoryExample();
        CategoryExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(nodeName)) {
            criteria.andNodeNameLike(nodeName);
        }
        String pageSize = servletRequest.getParameter("pagesize");
        String startIndex = servletRequest.getParameter("recordstartindex");
        example.setLimit(startIndex + "," + pageSize);
        int pageCount = categoryService.selectCount(example);
        List<CategoryVo> categoryVoList = new ArrayList<>();
        if (pageCount == 0) {
            return categoryVoList;
        }
        List<Category> categoryList = categoryService.list(example);
        CategoryVo categoryVo;
        for (Category category : categoryList) {
            categoryVo = new CategoryVo();
            BeanUtils.copyProperties(category, categoryVo);
            categoryVoList.add(categoryVo);
        }
        categoryVoList.get(0).setCount(pageCount);
        return categoryVoList;
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
