package org.example.blog.util;

import java.util.UUID;

public class TimestampUidGenerator {
    //根据时间戳来生成随机id
    public static String generateUid() {
        long timestamp = System.currentTimeMillis(); // 当前时间戳
        String randomPart = UUID.randomUUID().toString().replace("-", "").substring(0, 8); // 随机部分
        return timestamp + randomPart;
    }
}
