package org.example.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.blog.model.User;

@Mapper
public interface UserMapper {
    void insert(User user);

    User login(User user);
}
