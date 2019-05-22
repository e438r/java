package com.example.controller;

import com.example.Vo.ResultParam;
import com.example.Vo.UserVo;
import com.example.config.democonfig;
import com.example.dto.User;
import com.example.dto.UserExample;
import com.example.service.UserService;
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
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private democonfig democonfig;

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String Index(@ModelAttribute("model") ModelMap mm) {
        return "/user/index";
    }

    @RequestMapping("/get")
    @ResponseBody
    public User user(Integer id) {
        User user = userService.get(id);
        return user;
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<UserVo> userList(HttpServletRequest servletRequest) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        String userName = servletRequest.getParameter("name");
        String phone = servletRequest.getParameter("phone");
        if (StringUtils.isNotBlank(userName)) {
            criteria.andUserNameLike(userName);
        }
        if (StringUtils.isNotBlank(phone)) {
            criteria.andPhoneLike(phone);
        }

        String pageSize = servletRequest.getParameter("pagesize");
        String startIndex = servletRequest.getParameter("recordstartindex");
        example.setLimit(startIndex + "," + pageSize);
        List<User> userList = userService.list(example);
        int pageCount = userService.selectCount(example);
        List<UserVo> userVoList = new ArrayList<>();
        UserVo userVo;
        for (User user : userList) {
            userVo = new UserVo();
            BeanUtils.copyProperties(user, userVo);
            userVoList.add(userVo);
        }
        userVoList.get(0).setCount(pageCount);
        return userVoList;
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResultParam add(User user) {
        userService.add(user);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return resultParam;
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultParam update(User user) {
        userService.update(user);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return resultParam;
    }

    @RequestMapping("/del")
    @ResponseBody
    public ResultParam delete(Integer id) {
        userService.delete(id);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return resultParam;
    }
}
