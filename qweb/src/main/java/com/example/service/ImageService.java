package com.example.service;

import com.example.dto.ShowImage;
import com.example.dto.ShowImageExample;

import java.util.List;

public interface ImageService {
    void add(ShowImage showImage);

    void delete(Integer id);

    void update(ShowImage showImage);

    ShowImage get(Integer id);

    List<ShowImage> list(ShowImageExample showImageExample);
}
