package com.example.controller;

import com.example.config.democonfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {
    private Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private democonfig democonfig;

    @RequestMapping("/")
    public String Index(@ModelAttribute("model") ModelMap mm, HttpServletRequest request){
         Cookie[] cookies = request.getCookies();
         String userName;
         String passWord;
         for (Cookie cookie : cookies){
             if (cookie.getName().equals("uname")){
                 userName = cookie.getValue();
             }
         }
        return "/system/index";
    }

}
