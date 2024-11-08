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
        // 调用 userMapper 的 login 方法进行用户登录验证，并返回用户信息
        return userMapper.login(user);
    }

    public User getUserInfo(User user) {
        // 定义一个变量用于存储返回的用户信息
        User returnUser = null;

        // 如果传入的 user 对象为 null，直接返回 null
        if (user == null) {
            return null;
        }
        // 如果用户提供了 ID，使用 ID 获取用户信息
        else if (user.getId() != null) {
            returnUser = getUserInfoById(user.getId());
        }
        // 如果用户提供了 Email，使用 Email 获取用户信息
        else if (user.getEmail() != null) {
            returnUser = getUserInfoByEmail(user.getEmail());
        }
        // 如果用户提供了 Phone，使用 Phone 获取用户信息
        else if (user.getPhone() != null) {
            returnUser = getUserInfoByPhone(user.getPhone());
        }
        // 如果用户提供了 Card，使用 Card 获取用户信息
        else if (user.getCard() != null) {
            returnUser = getUserInfoByCard(user.getCard());
        }
        // 如果用户提供了 Nickname，使用 Nickname 获取用户信息
        else if (user.getNickname() != null) {
            returnUser = getUserInfoByNickname(user.getNickname());
        }

        // 返回根据不同条件查询到的用户信息（如果有）
        return returnUser;
    }

    // 通过用户的 ID 查询用户信息
    private User getUserInfoById(String id) {
        return userMapper.getUserInfoById(id);
    }

    // 通过用户的 Email 查询用户信息
    private User getUserInfoByEmail(String email) {
        return userMapper.getUserInfoByEmail(email);
    }

    // 通过用户的 Phone 查询用户信息
    private User getUserInfoByPhone(String phone) {
        return userMapper.getUserInfoByPhone(phone);
    }

    // 通过用户的 Card 查询用户信息
    private User getUserInfoByCard(String card) {
        return userMapper.getUserInfoByCard(card);
    }

    // 通过用户的 Nickname 查询用户信息
    private User getUserInfoByNickname(String nickname) {
        return userMapper.getUserInfoByNickname(nickname);
    }

    public User updateUserInfo(User user) {
        userMapper.updateUserInfo(user);
        return getUserInfoById(user.getId());
    }
}
