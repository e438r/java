package com.example.controller;

import com.example.config.democonfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    private Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private democonfig democonfig;

    //http://localhost:8080/user/index
    @RequestMapping("/")
    public String Index(@ModelAttribute("model") ModelMap mm) {
        return "/main/Index";
    }

    @RequestMapping("/send")
    @ResponseBody
    public String openA(Integer id) {
        String cmd = null;
        switch (id) {
            case 1:
                cmd = democonfig.getOpena();
                break;
            case 2:
                cmd = democonfig.getOpenb();
                break;
            default:

                break;
        }
        return cmd + id;
    }

}
