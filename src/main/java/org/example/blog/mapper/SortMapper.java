package org.example.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.blog.model.Sort;

import java.util.List;

@Mapper
public interface SortMapper {
    List<Sort> getAllSort();
}
