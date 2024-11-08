package org.example.blog.controller;

import org.example.blog.model.ResponseResult;
import org.example.blog.model.Command;
import org.example.blog.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/command")
public class CommandController {
    private final CommandService commandService;

    @Autowired
    public CommandController(CommandService commandService) {
        this.commandService = commandService;
    }
    @PostMapping("/submitCommand")
    public ResponseResult<Void> submitCommand(@RequestBody Command command) {
        try {
            commandService.submitCommand(command);
            // 如果创建成功，返回一个成功的响应
            return ResponseResult.success(null);  // 没有返回数据，可以用 null
        } catch (Exception e) {
            // 如果发生错误，返回一个错误的响应
            return ResponseResult.internalServerError("注册失败：" + e.getMessage());
        }
    }
}
