package com.dzx.service.impl;

import com.dzx.dao.TeacherClassMapper;
import com.dzx.entity.Clazz;
import com.dzx.entity.Teacher;
import com.dzx.service.TeacherClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Zpjeck
 * @Date: 2019/4/1 12:07
 * @Description:
 */
@Service
public class TeacherClassServiceImpl implements TeacherClassService {

    @Autowired
    private TeacherClassMapper teacherClassMapper;


    @Override
    public Teacher findByClassId(String classId) {
        return teacherClassMapper.findByClassId(classId);
    }

    @Override
    public List<Clazz> findByTeacherId(String teacherId) {
        return teacherClassMapper.findByTeacherId(teacherId);
    }
}
