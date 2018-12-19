package com.example.service.impl;

import com.example.dao.ShowImageMapper;
import com.example.dto.ShowImage;
import com.example.dto.ShowImageExample;
import com.example.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ShowImageMapper showImageMapper;

    @Override
    public void add(ShowImage showImage) {
        showImageMapper.insert(showImage);
    }

    @Override
    public void delete(Integer id) {
        showImageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(ShowImage showImage) {
        showImageMapper.updateByPrimaryKeySelective(showImage);
    }

    @Override
    public ShowImage get(Integer id) {
        return showImageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ShowImage> list(ShowImageExample showImageExample) {
        return showImageMapper.selectByExample(showImageExample);
    }
}
