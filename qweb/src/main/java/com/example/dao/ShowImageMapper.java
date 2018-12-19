package com.example.dao;

import com.example.dto.ShowImage;
import com.example.dto.ShowImageExample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShowImage record);

    int insertSelective(ShowImage record);

    List<ShowImage> selectByExample(ShowImageExample example);

    ShowImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShowImage record);

    int updateByPrimaryKey(ShowImage record);
}