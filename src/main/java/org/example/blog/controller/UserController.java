package org.example.blog.controller;

import org.example.blog.model.ResponseResult;
import org.example.blog.model.User;
import org.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController  // 使用 @RestController，已包含 @Controller 和 @ResponseBody
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 创建新用户
    @PostMapping("/register")
    public ResponseResult<Void> createUser(@ModelAttribute User user) {
        try {
            userService.createUser(user);
            // 如果创建成功，返回一个成功的响应
            return ResponseResult.success(null);  // 没有返回数据，可以用 null
        } catch (Exception e) {
            // 如果发生错误，返回一个错误的响应
            return ResponseResult.internalServerError("注册失败：" + e.getMessage());
        }
    }

    // 用户登录
    @PostMapping("/login")
    public ResponseResult<User> login(@ModelAttribute User user) {
        try {
            User loggedInUser = userService.login(user);
            if (loggedInUser == null) {
                // 如果登录失败，返回 401 未授权响应
                return ResponseResult.unauthorized("用户名或密码错误");
            }
            // 登录成功，返回用户信息
            return ResponseResult.success(loggedInUser);
        } catch (Exception e) {
            // 如果发生错误，返回 500 错误响应
            return ResponseResult.internalServerError("登录失败：" + e.getMessage());
        }
    }
}