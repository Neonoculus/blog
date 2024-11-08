package org.example.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.blog.model.User;

@Mapper
public interface UserMapper {
    void insert(User user);

    User login(User user);

    User getUserInfoById(String id);

    User getUserInfoByEmail(String email);

    User getUserInfoByPhone(String phone);

    User getUserInfoByCard(String card);

    User getUserInfoByNickname(String nickname);

    void updateUserInfo(User user);
}
