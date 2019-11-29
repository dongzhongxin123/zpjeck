package com.dzx.service.impl;

import com.dzx.dao.StudentTaskMapper;
import com.dzx.entity.Student;
import com.dzx.entity.StudentTask;
import com.dzx.entity.Teacher;
import com.dzx.service.StudentTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

/**
 * @Auther: Zpjeck
 * @Date: 2019/3/31 16:57
 * @Description:
 */
@Service
public class StudentTaskServiceImpl implements StudentTaskService {
    @Autowired
    private StudentTaskMapper studentTaskMapper;
    @Autowired
    private HttpSession session;


    @Override
    public int save(StudentTask studentTask) {
        if (studentTask == null) {
            return 0;
        }
        Student student = (Student) session.getAttribute("student");
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        studentTask.setId(id);
        studentTask.setClassId(student.getClassId());
        studentTask.setStudentId(student.getId());
        studentTask.setCreateBy(student.getId());
        studentTask.setCreateTime(new Date());
        return studentTaskMapper.save(studentTask);
    }

    @Override
    public int relayTask(StudentTask studentTask) {

        Teacher teacher = (Teacher) session.getAttribute("teacher");
        studentTask.setUpdateBy(teacher.getId());
        studentTask.setUpdateTime(new Date());
        return studentTaskMapper.relayTask(studentTask);
    }
}
