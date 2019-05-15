package com.example.controller;

import com.example.Vo.ResultParam;
import com.example.dto.ShowImage;
import com.example.dto.ShowImageExample;
import com.example.service.ImageService;
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
@RequestMapping("/image")
public class ImageController {
    private Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private ImageService imageService;

    @RequestMapping("/index")
    public String Index(@ModelAttribute("model") ModelMap mm) {
        return "/image/index";
    }

    @RequestMapping("/get")
    @ResponseBody
    public ShowImage image(Integer id) {
        ShowImage image = imageService.get(id);
        return image;
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<ShowImage> imageList(HttpServletRequest servletRequest) {
        ShowImageExample example = new ShowImageExample();
        ShowImageExample.Criteria criteria = example.createCriteria();

        List<ShowImage> imageList = imageService.list(example);
        return imageList;
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResultParam add(ShowImage image) {
        imageService.add(image);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return resultParam;
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultParam update(ShowImage image) {
        imageService.update(image);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return resultParam;
    }

    @RequestMapping("/del")
    @ResponseBody
    public ResultParam delete(Integer id) {
        imageService.delete(id);
        ResultParam resultParam = new ResultParam();
        resultParam.setErrorMessage("OK");
        return resultParam;
    }
}
