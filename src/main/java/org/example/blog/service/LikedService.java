package org.example.blog.service;

import org.example.blog.mapper.LikedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikedService {
    private final LikedMapper likedMapper;

    @Autowired
    public LikedService(LikedMapper likedMapper) {
        this.likedMapper = likedMapper;
    }
}
