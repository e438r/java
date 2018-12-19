package com.example.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.example.Vo.ResultParam;
import com.example.config.democonfig;
import com.example.dto.Subscribe;
import com.example.dto.SubscribeExample;
import com.example.service.SubscribeService;
import com.google.common.base.Strings;
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
    public String subscribe(Integer id) {
        Subscribe subscribe = subscribeService.get(id);
        return JSONUtils.toJSONString(subscribe);
    }

    @RequestMapping("/list")
    @ResponseBody
    public String subscribeList(Subscribe subscribe) {
        SubscribeExample example = new SubscribeExample();
        SubscribeExample.Criteria criteria = example.createCriteria();
        if (null != subscribe.getCreateTime()) {
            criteria.andCreateTimeGreaterThanOrEqualTo(subscribe.getCreateTime());
        }
        if(null != subscribe.getStartTime()){
            criteria.andStartTimeGreaterThanOrEqualTo(subscribe.getStartTime());
        }
        if(null != subscribe.getEndTime()){
            criteria.andEndTimeLessThanOrEqualTo(subscribe.getEndTime());
        }
        List<Subscribe> subscribeList = subscribeService.list(example);
        return JSONUtils.toJSONString(subscribeList);
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add(Subscribe subscribe) {
        subscribeService.add(subscribe);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return JSONUtils.toJSONString(resultParam);
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(Subscribe subscribe) {
        subscribeService.update(subscribe);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return JSONUtils.toJSONString(resultParam);
    }

    @RequestMapping("/del")
    @ResponseBody
    public String delete(Integer id) {
        subscribeService.delete(id);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return JSONUtils.toJSONString(resultParam);
    }
}
