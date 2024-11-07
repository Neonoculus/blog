package org.example.blog.service;

import org.example.blog.mapper.SortMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SortService {
    private final SortMapper sortMapper;

    @Autowired
    public SortService(SortMapper sortMapper) {
        this.sortMapper = sortMapper;
    }


}
