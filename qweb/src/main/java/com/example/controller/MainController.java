package com.example.controller;

import com.example.config.democonfig;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController {
    private Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private democonfig democonfig;

    @RequestMapping("/")
    public String Index(@ModelAttribute("model") ModelMap mm, HttpServletRequest request, HttpServletResponse response){
         Cookie[] cookies = request.getCookies();
         String userName = "";
         String passWord = "";
         if (null != cookies){
             for (Cookie cookie : cookies){
                 if (cookie.getName().equals("uname")){
                     userName = cookie.getValue();
                 }
                 if (cookie.getName().equals("pwd")){
                     passWord = cookie.getValue();
                 }
             }
         }

         if (Strings.isNullOrEmpty(userName) || Strings.isNullOrEmpty(passWord)){
             addCookie(response,"uname", "zxq");
             addCookie(response, "pwd", "1qaz");
         }
        return "/system/index";
    }

    /**
     * 添加cookie
     * @param response
     * @param name
     * @param value
     */
    @RequestMapping("/addCookie")
    public void addCookie(HttpServletResponse response,String name,String value){
        Cookie cookie = new Cookie(name.trim(), value.trim());
        cookie.setMaxAge(30 * 60);// 设置为30min
        cookie.setPath("/");
        System.out.println("已添加===============");
        response.addCookie(cookie);
    }
    /**
     * 修改cookie
     * @param request
     * @param response
     * @param name
     * @param value
     * 注意一、修改、删除Cookie时，新建的Cookie除value、maxAge之外的所有属性，例如name、path、domain等，都要与原Cookie完全一样。否则，浏览器将视为两个不同的Cookie不予覆盖，导致修改、删除失败。
     */
    @RequestMapping("/editCookie")
    public void editCookie(HttpServletRequest request,HttpServletResponse response,String name,String value){
        Cookie[] cookies = request.getCookies();
        if (null==cookies) {
            System.out.println("没有cookie==============");
        } else {
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(name)){
                    System.out.println("原值为:"+cookie.getValue());
                    cookie.setValue(value);
                    cookie.setPath("/");
                    cookie.setMaxAge(30 * 60);// 设置为30min
                    System.out.println("被修改的cookie名字为:"+cookie.getName()+",新值为:"+cookie.getValue());
                    response.addCookie(cookie);
                    break;
                }
            }
        }

    }
    /**
     * 删除cookie
     * @param request
     * @param response
     * @param name
     */
    @RequestMapping("/delCookie")
    public void delCookie(HttpServletRequest request,HttpServletResponse response,String name){
        Cookie[] cookies = request.getCookies();
        if (null==cookies) {
            System.out.println("没有cookie==============");
        } else {
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(name)){
                    cookie.setValue(null);
                    cookie.setMaxAge(0);// 立即销毁cookie
                    cookie.setPath("/");
                    System.out.println("被删除的cookie名字为:"+cookie.getName());
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }
}
