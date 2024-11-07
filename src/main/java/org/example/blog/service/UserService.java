package org.example.blog.service;

import org.example.blog.mapper.UserMapper;
import org.example.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.example.blog.util.TimestampUidGenerator.generateUid;

@Service
public class UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void createUser(User user) {
        user.setId(generateUid());
        userMapper.insert(user);
    }

    public User login(User user) {
        return userMapper.login(user);
    }
}
