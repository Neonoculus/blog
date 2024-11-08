package org.example.blog.controller;

import org.example.blog.model.ResponseResult;
import org.example.blog.model.User;
import org.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController  // 使用 @RestController，已包含 @Controller 和 @ResponseBody
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 创建新用户
    @PostMapping("/register")
    public ResponseResult<Void> createUser(@RequestBody User user) {
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
    public ResponseResult<User> login(@RequestBody  User user) {
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

    // 获取用户信息
    @PostMapping("/getUserInfo")
    public ResponseResult<User> getUserInfo(@RequestBody  User user) {
        try {
            User returnUser = userService.getUserInfo(user);
            if (returnUser == null||returnUser.getId()==null||(returnUser.getUsername()==null&&returnUser.getPassword()==null)) {
                // 如果获取失败，返回 404 资源未找到
                return ResponseResult.notFound("用户信息获取失败，请联系管理员");
            }
            // 获取成功，返回用户信息
            return ResponseResult.success(returnUser);
        } catch (Exception e) {
            // 如果发生错误，返回 500 错误响应
            return ResponseResult.internalServerError("登录失败：" + e.getMessage());
        }
    }

    // 修改用户信息
    @PostMapping("/updateUserInfo")
    public ResponseResult<User> updateUserInfo(@RequestBody  User user) {
        try {
            User returnUser = userService.updateUserInfo(user);
            //修改后获取新的用户信息
            if (returnUser == null||returnUser.getId()==null||(returnUser.getUsername()==null&&returnUser.getPassword()==null)) {
                // 如果获取失败，返回 404 资源未找到
                return ResponseResult.notFound("用户信息修改失败，请联系管理员");
            }
            // 获取成功，返回用户信息
            return ResponseResult.success(returnUser);
        } catch (Exception e) {
            // 如果发生错误，返回 500 错误响应
            return ResponseResult.internalServerError("登录失败：" + e.getMessage());
        }
    }
}