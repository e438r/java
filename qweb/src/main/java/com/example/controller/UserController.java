package com.example.controller;

import com.example.Vo.ResultParam;
import com.example.config.democonfig;
import com.example.dto.User;
import com.example.dto.UserExample;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    public String Index(@ModelAttribute("model") ModelMap mm){
        return "/user/Index";
    }

    @RequestMapping("/get")
    @ResponseBody
    public User user(Integer id){
        User user = userService.get(id);
        return user;
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<User> userList(HttpServletRequest servletRequest){
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();

        List<User> userList = userService.list(example);
        return userList;
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResultParam add(User user){
        userService.add(user);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return resultParam;
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultParam update(User user){
        userService.update(user);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return resultParam;
    }

    @RequestMapping("/del")
    @ResponseBody
    public ResultParam delete(Integer id){
        userService.delete(id);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return resultParam;
    }
}
