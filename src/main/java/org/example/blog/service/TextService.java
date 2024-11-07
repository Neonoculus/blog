package org.example.blog.service;

import org.example.blog.mapper.CommandMapper;
import org.example.blog.mapper.SortMapper;
import org.example.blog.mapper.TextMapper;
import org.example.blog.model.Command;
import org.example.blog.model.Sort;
import org.example.blog.model.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TextService {
    private final TextMapper textMapper;
    private final SortMapper sortMapper;

    @Autowired
    public TextService(TextMapper textMapper, SortMapper sortMapper) {
        this.textMapper = textMapper;
        this.sortMapper = sortMapper;
    }



    public List<Text> getMainPageTextList() {
        List<Sort> sortList = sortMapper.getAllSort();
        List<Text> mainPageTextList = new ArrayList<>();
        Set<String> hashSet = new HashSet<>();  // 用来跟踪已经添加过的文本的哈希值
        List<Text> textRandomPool;
        Text textsToAdd;

        int sortNumber = sortList.size();
        int totalTextsAdded = 0;  // 记录已添加的文本数量
        int totalTextTries = 0;   // 记录文本选择尝试重新失败的次数

        // 循环直到我们选择足够的文本
        while (totalTextsAdded < 10 ) {
            // 增加一个最大尝试次数限制来避免死循环
            int sortRandom = (int) (Math.random() * sortNumber);  // 随机选择一个 sort
            Sort sort = sortList.get(sortRandom);

            textRandomPool = textMapper.getTextToSort(sort.getS_id());  // 获取该 sort 下的所有文本

            // 如果这个分类下没有文本，则跳过
            if (textRandomPool == null || textRandomPool.isEmpty()) {
                continue;
            }

            // 随机选择一个文本，直到它的哈希值不在 hashSet 中
            Text randomText = null;
            for (int i = 0; i < textRandomPool.size(); i++) {
                textsToAdd = textRandomPool.get((int) (Math.random() * textRandomPool.size()));
                // 检查哈希值是否已存在，避免重复
                if (!hashSet.contains(String.valueOf(textsToAdd.hashCode()))) {
                    randomText = textsToAdd;
                    break;
                }
            }

            // 如果找到了新的文本，则添加到列表
            if (randomText != null) {
                hashSet.add(String.valueOf(randomText.hashCode()));
                mainPageTextList.add(randomText);
                totalTextsAdded++;  // 增加已添加文本的数量
            } else {
                totalTextTries++;  // 如果没有找到新的文本，则增加尝试次数
            }

            // 如果已经尝试过所有分类，且仍未填充满10条数据，就跳出循环
            if (totalTextTries >= sortNumber) {
                System.out.println("尝试超过最大次数，返回已获取的数据。");
                break;
            }
        }

        // 如果最终返回的数据不足10条，直接返回现有的文本列表
        if (totalTextsAdded < 10) {
            System.out.println("数据不足10条，返回已获取的数据。");
        }

        // 返回找到的文本列表
        return mainPageTextList;
    }
}
