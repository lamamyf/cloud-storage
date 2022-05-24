package com.udacity.jwdnd.course1.cloudstorage.entity.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface UserMapper {

    @Insert("INSERT into USERS (username, salt, password, first_name, last_name) VALUES (#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int addUser(User user);

    @Select("SELECT EXISTS(SELECT 1 FROM USERS WHERE username = #{username})")
    boolean existsByUsername(String username);

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    public Optional<User> getUser(String username);
}
