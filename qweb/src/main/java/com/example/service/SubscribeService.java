package com.example.service;

import com.example.dto.Subscribe;
import com.example.dto.SubscribeExample;

import java.util.List;

public interface SubscribeService {
    void add(Subscribe subscribe);

    void delete(Integer id);

    void update(Subscribe subscribe);

    Subscribe get(Integer id);

    List<Subscribe> list(SubscribeExample subscribeExample);
}
