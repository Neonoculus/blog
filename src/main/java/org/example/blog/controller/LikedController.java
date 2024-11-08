package org.example.blog.controller;

import org.example.blog.service.LikedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/liked")
public class LikedController {
    private final LikedService likedService;

    @Autowired
    public LikedController(LikedService likedService) {
        this.likedService = likedService;
    }
}
