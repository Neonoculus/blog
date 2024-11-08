package org.example.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.blog.model.Text;

import java.util.List;

@Mapper
public interface TextMapper {


    List<Text> getTextBySort(String sId);

    Text getTextByTID(String tId);

    List<Text> getTextByUId(String uId);
}
