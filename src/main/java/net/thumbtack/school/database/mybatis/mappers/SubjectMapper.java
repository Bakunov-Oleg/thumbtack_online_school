package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.Subject;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SubjectMapper {
    @Insert("INSERT INTO subject (name) VALUES " +
            "(#{name} )")
    @Options(useGeneratedKeys = true)
    Integer insert(Subject subject);

    @Select("SELECT * FROM subject WHERE id = #{id}")
    Subject getById(int id);

    @Select("SELECT * FROM subject")
    List<Subject> getAll();

    @Update("UPDATE subject SET name = #{subject.name} WHERE id = #{subject.id} ")
    void update(@Param("subject") Subject subject);

    @Delete("DELETE FROM subject WHERE id = #{subject.id}")
    void delete(@Param("subject") Subject subject);

    @Delete("DELETE FROM subject")
    void deleteAll();

    @Select("SELECT * FROM `subject` WHERE id IN (SELECT subject_id FROM group_subject WHERE group_id = #{group.id})")
    List<Subject> getByGroupId(@Param("group") Group group);

}