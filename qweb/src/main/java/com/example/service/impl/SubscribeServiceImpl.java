package com.example.service.impl;

import com.example.dao.SubscribeMapper;
import com.example.dto.Subscribe;
import com.example.dto.SubscribeExample;
import com.example.service.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SubscribeServiceImpl implements SubscribeService {
    @Autowired
    private SubscribeMapper subscribeMapper;

    @Override
    public void add(Subscribe subscribe) {
        subscribeMapper.insert(subscribe);
    }

    @Override
    public void delete(Integer id) {
        subscribeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Subscribe subscribe) {
        subscribeMapper.updateByPrimaryKeySelective(subscribe);
    }

    @Override
    public Subscribe get(Integer id) {
        return subscribeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Subscribe> list(SubscribeExample subscribeExample) {
        return subscribeMapper.selectByExample(subscribeExample);
    }
}
