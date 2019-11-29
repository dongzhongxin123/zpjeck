package com.dzx.service;


import com.dzx.entity.Clazz;
import com.dzx.entity.Teacher;

import java.util.List;

/**
 * @Auther: Zpjeck
 * @Date: 2019/4/1 12:05
 * @Description:
 */
public interface TeacherClassService {

    /*
     *  根据老师id查询班级班级信息
     */
    List<Clazz> findByTeacherId(String teacherId);

    /*
     *  根据班级id 查询老师信息
     */
    Teacher findByClassId(String classId);
}
