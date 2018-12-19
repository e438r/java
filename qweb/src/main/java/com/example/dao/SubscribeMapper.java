package com.example.dao;

import com.example.dto.Subscribe;
import com.example.dto.SubscribeExample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscribeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Subscribe record);

    int insertSelective(Subscribe record);

    List<Subscribe> selectByExample(SubscribeExample example);

    Subscribe selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Subscribe record);

    int updateByPrimaryKey(Subscribe record);
}