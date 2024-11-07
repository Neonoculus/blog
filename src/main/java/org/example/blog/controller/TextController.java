package org.example.blog.controller;

import org.example.blog.model.ResponseResult;
import org.example.blog.model.Text;
import org.example.blog.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/text")
public class TextController {
    private final TextService textService;

    @Autowired
    public TextController(TextService textService) {
        this.textService = textService;
    }

    //获取主页文章
    @PostMapping("/getMainPageTexts")
    public ResponseResult<List> getMainPageTexts() {
        try {
            // 获取主页文本数据
            List mainPageTextList = textService.getMainPageTextList();

            // 如果返回的数据为空，返回 404
            if (mainPageTextList.isEmpty()) {
                return ResponseResult.notFound(null);
            }

            // 返回成功结果，包含实际返回的文本列表
            return ResponseResult.success(mainPageTextList);

        } catch (Exception e) {
            // 如果发生异常，返回 500 错误，包含异常信息
            return ResponseResult.internalServerError("主页内容获取失败：" + e.getMessage());
        }
    }
}
