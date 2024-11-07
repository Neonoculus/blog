package org.example.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.blog.model.Text;

import java.util.List;

@Mapper
public interface TextMapper {


    List<Text> getTextToSort(String sId);
}
