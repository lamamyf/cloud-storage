package com.udacity.jwdnd.course1.cloudstorage.entity.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credential;
import com.udacity.jwdnd.course1.cloudstorage.entity.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, user_id) VALUES " +
                                     "(#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(Credential credential);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, password = #{password} WHERE id = #{id}")
    void updateCredentialById(String url, String username, String password, int id);

    @Delete("DELETE FROM CREDENTIALS WHERE id = #{id}")
    void deleteCredentialById(int id);

    @Select("SELECT * FROM CREDENTIALS WHERE user_id = #{userId}")
    List<Note> getCredentialsByUserId(int userId);
}
