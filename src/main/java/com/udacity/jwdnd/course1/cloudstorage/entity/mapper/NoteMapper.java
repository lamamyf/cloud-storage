package com.udacity.jwdnd.course1.cloudstorage.entity.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Insert("INSERT INTO NOTES (title, description, user_id) VALUES (#{title}, #{description}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(Note note);

    @Update("UPDATE NOTES SET title = #{title}, description = #{description} WHERE id = #{id}")
    void updateNoteById(String title, String description, int id);

    @Delete("DELETE FROM NOTES WHERE id = #{id}")
    void deleteNoteById(int id);

    @Select("SELECT * FROM NOTES WHERE user_id = #{userId}")
    List<Note> getNotesByUserId(int userId);
}
