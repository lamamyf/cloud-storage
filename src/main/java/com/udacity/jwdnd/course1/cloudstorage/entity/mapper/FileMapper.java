package com.udacity.jwdnd.course1.cloudstorage.entity.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.File;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileMapper {

    @Insert("INSERT INTO FILES (name, content_type, size, user_id, data) VALUES (#{name}, #{contentType}, #{size}, #{userId}, #{data})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(File file);

    @Select("SELECT name FROM FILES WHERE user_id = #{userId}")
    List<String> getFilesByUserId(int userId);

    @Select("SELECT * FROM FILES WHERE user_id = #{userId} and name = #{name}")
    File getFileByNameAndUserId(String name, int userId);
}
