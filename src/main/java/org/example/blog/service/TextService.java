package org.example.blog.service;

import org.example.blog.mapper.LikedMapper;
import org.example.blog.mapper.SortMapper;
import org.example.blog.mapper.TextMapper;
import org.example.blog.model.Liked;
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
    private final LikedMapper likedMapper;

    @Autowired
    public TextService(TextMapper textMapper, SortMapper sortMapper, LikedMapper likedMapper) {
        this.textMapper = textMapper;
        this.sortMapper = sortMapper;
        this.likedMapper = likedMapper;
    }


    //获取主页文章
    public List<Text> getMainPageTextList() {
        List<Sort> sortList = sortMapper.getAllSort();
        List<Text> mainPageTextList = new ArrayList<>();
        Set<String> hashSet = new HashSet<>();  // 用来跟踪已经添加过的文本的哈希值
        List<Text> textRandomPool;
        Text textsToAdd;

        int sortNumber = sortList.size();
        int totalTextsAdded = 0;  // 记录已添加的文本数量
        int totalTextTries = 0;   // 记录文本选择尝试失败的次数

        // 循环直到我们选择足够的文本
        while (totalTextsAdded < 10) {
            // 增加一个最大尝试次数限制来避免死循环
            int sortRandom = (int) (Math.random() * sortNumber);  // 随机选择一个 sort
            Sort sort = sortList.get(sortRandom);

            textRandomPool = textMapper.getTextBySort(sort.getS_id());  // 获取该 sort 下的所有文本

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
            if (totalTextTries >= sortNumber+10) {
                System.out.println("尝试超过最大次数，返回已获取的数据。");
                break;
            }
        }

        // 如果最终返回的数据不足10条，直接返回现有的文本列表，并添加提示
        if (totalTextsAdded < 10) {
            System.out.println("数据不足10条，返回已获取的数据。");
        }

        // 返回找到的文本列表
        return mainPageTextList;
    }

    //根据类别获取文章
    public List getTextListBySort(String sId) {
        // 返回找到的文本列表
        return textMapper.getTextBySort(sId);
    }

    public List<Text> getTextByLiked(String tId) {
        List<Liked> likedList = likedMapper.getLikedByUser(tId);
        List<Text> likedTextList = new ArrayList<>();

        if (likedList == null || likedList.isEmpty()) {
            return likedTextList;  // 如果没有喜欢的文章，直接返回空列表
        }

        for (Liked liked : likedList) {
            // 获取文章并添加到返回列表
            Text text = textMapper.getTextByTID(liked.getT_id());
            if (text != null) {
                likedTextList.add(text);
            }
        }
        // 返回找到的文本列表
        return likedTextList;
    }

    public List<Text> getTextByUser(String uId) {
        // 返回找到的文本列表
        return textMapper.getTextByUId(uId);
    }
}
