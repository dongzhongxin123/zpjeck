package com.dzx.dao;


import com.dzx.entity.Clazz;
import com.dzx.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherClassMapper {
    List<Clazz> findByTeacherId(@Param("teacherId") String teacherId);

    Teacher findByClassId(String classId);
}