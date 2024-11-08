package org.example.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.blog.model.Liked;

import java.util.List;

@Mapper
public interface LikedMapper {
    List<Liked> getLikedByUser(String id);
}
