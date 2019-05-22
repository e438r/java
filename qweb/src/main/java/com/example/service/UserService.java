package com.example.service;

import com.example.dto.CategoryExample;
import com.example.dto.User;
import com.example.dto.UserExample;

import java.util.List;

public interface UserService {
    void add(User user);

    void delete(Integer id);

    void update(User user);

    User get(Integer id);

    List<User> list(UserExample userExample);

    int selectCount(UserExample example);
}
