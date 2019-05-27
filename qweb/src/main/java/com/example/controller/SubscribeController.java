package com.example.controller;

import com.example.Vo.ResultParam;
import com.example.Vo.SubscribeVo;
import com.example.Vo.UserVo;
import com.example.config.democonfig;
import com.example.dto.Subscribe;
import com.example.dto.SubscribeExample;
import com.example.dto.User;
import com.example.service.SubscribeService;
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
@RequestMapping("/subscribe")
public class SubscribeController {
    private Logger logger = LoggerFactory.getLogger(SubscribeController.class);

    @Autowired
    private democonfig democonfig;
    @Autowired
    private SubscribeService subscribeService;

    @RequestMapping("/index")
    public String Index(@ModelAttribute("model") ModelMap mm) {
        return "/subscribe/Index";
    }

    @RequestMapping("/get")
    @ResponseBody
    public Subscribe subscribe(Integer id) {
        Subscribe subscribe = subscribeService.get(id);
        return subscribe;
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<SubscribeVo> subscribeList(HttpServletRequest servletRequest) {
        SubscribeExample example = new SubscribeExample();
        SubscribeExample.Criteria criteria = example.createCriteria();

        String pageSize = servletRequest.getParameter("pagesize");
        String startIndex = servletRequest.getParameter("recordstartindex");
        example.setLimit(startIndex + "," + pageSize);
        int pageCount = subscribeService.selectCount(example);
        List<SubscribeVo> subscribeVoList = new ArrayList<>();
        if (pageCount == 0) {
            return subscribeVoList;
        }
        List<Subscribe> subscribeList = subscribeService.list(example);
        SubscribeVo subscribeVo;
        for (Subscribe subscribe : subscribeList) {
            subscribeVo = new SubscribeVo();
            BeanUtils.copyProperties(subscribe, subscribeVo);
            subscribeVoList.add(subscribeVo);
        }
        subscribeVoList.get(0).setCount(pageCount);
        return subscribeVoList;
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResultParam add(Subscribe subscribe) {
        subscribeService.add(subscribe);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return resultParam;
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultParam update(Subscribe subscribe) {
        subscribeService.update(subscribe);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return resultParam;
    }

    @RequestMapping("/del")
    @ResponseBody
    public ResultParam delete(Integer id) {
        subscribeService.delete(id);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return resultParam;
    }
}
