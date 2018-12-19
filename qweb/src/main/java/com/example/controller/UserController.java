package com.example.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.example.Vo.ResultParam;
import com.example.config.democonfig;
import com.example.dto.User;
import com.example.dto.UserExample;
import com.example.service.UserService;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.rabbitmq.tools.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.transform.Result;
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
    public String user(Integer id){
        User user = userService.get(id);
        return JSONUtils.toJSONString(user);
    }

    @RequestMapping("/list")
    @ResponseBody
    public String userList(User user){
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        if(!Strings.isNullOrEmpty(user.getUserName())) {
            criteria.andUserNameLike(user.getUserName());
        }
        if(!Strings.isNullOrEmpty(user.getPhone())){
            criteria.andPhoneLike(user.getPhone());
        }
        List<User> userList = userService.list(example);
        return JSONUtils.toJSONString(userList);
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add(User user){
        userService.add(user);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return JSONUtils.toJSONString(resultParam);
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(User user){
        userService.update(user);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return JSONUtils.toJSONString(resultParam);
    }

    @RequestMapping("/del")
    @ResponseBody
    public String delete(Integer id){
        userService.delete(id);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return JSONUtils.toJSONString(resultParam);
    }
}
