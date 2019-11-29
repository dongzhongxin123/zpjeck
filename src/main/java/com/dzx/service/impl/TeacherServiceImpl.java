package com.dzx.service.impl;


import com.dzx.dao.ClazzMapper;
import com.dzx.dao.TeacherClassMapper;
import com.dzx.dao.TeacherMapper;
import com.dzx.entity.Clazz;
import com.dzx.entity.Student;
import com.dzx.entity.Teacher;
import com.dzx.entity.TeacherClass;
import com.dzx.service.TeacherService;
import com.dzx.util.MD5Encryption;
import com.dzx.util.Result;
import com.dzx.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @Auther: Zpjeck
 * @Date: 2019/4/1 17:36
 * @Description:
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private HttpSession session;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private ClazzMapper clazzMapper;

    @Autowired
    private TeacherClassMapper teacherClassMapper;

    @Override
    public Teacher login(String number) {
        return teacherMapper.login(number);
    }

    @Override
    public int insertByStudent(Student student) {
        Teacher teacher = (Teacher) session.getAttribute("admin");
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        student.setId(id);
        student.setPassword(MD5Encryption.getMD5String("123456"));
        student.setCreateTy(student.getId());
        student.setCreateTime(new Date());
        return teacherMapper.insertByStudent(student);
    }

    @Override
    public int delectByStudentId(String studentId) {
        return teacherMapper.delectByStudentId(studentId);
    }

    @Override
    public int insertClass(Clazz clazz) {
        Teacher teacher = (Teacher) session.getAttribute("admin");
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        clazz.setId(id);
        clazz.setCreateby(teacher.getId());
        clazz.setCreatetime(new Date());
        return teacherMapper.insertClass(clazz);
    }

    @Override
    public int deleteByClassId(String classId) {
        return teacherMapper.deleteByClassId(classId);
    }

    @Override
    public int allotByTeacherClass(TeacherClass teacherClass) {
        Teacher teacher1 = (Teacher) session.getAttribute("admin");
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        teacherClass.setId(id);
        teacherClass.setCreateBy(teacher1.getId());
        teacherClass.setCreateTime(new Date());
        return teacherMapper.allotByTeacherClass(teacherClass);
    }

    @Override
    public int alterTeacher(Teacher teacher) {
        return teacherMapper.alterTeacher(teacher);
    }

    @Override
    public int alertStudent(Student student) {
        return teacherMapper.alertStudent(student);
    }

    @Override
    public int updateInfo(Teacher teacher) {
        return teacherMapper.updateInfo(teacher);
    }

    @Override
    public Result<Student> studentList(Integer pageNum, Integer pageSize) {
        Page page = PageHelper.startPage(pageNum, pageSize);
        List<Student> studentList = teacherMapper.studentList("1");
        for (Student student : studentList) {
            if (student.getSex().equals("0")) {
                student.setSex("女");
            } else {
                student.setSex("男");
            }
            Clazz clazz = clazzMapper.selectByPrimaryKey(student.getClassId());
            student.setClassId(clazz.getClassName());
        }
        return ResultUtil.success(studentList, studentList.size());
    }

    @Override
    public Result<Teacher> teacherList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Teacher> teachers = teacherMapper.teacherList();
        for (Teacher t : teachers) {
            if (t.getSex().equals("1")) {
                t.setSex("男");
            } else {
                t.setSex("女");
            }
            if (t.getStatus().equals("0")) {
                t.setStatus("管理员");
            } else {
                t.setStatus("教师");
            }
        }
        return ResultUtil.success(teachers, teachers.size());
    }

    @Override
    public Result<Clazz> classList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Clazz> clazzes = teacherMapper.classList();
        for (Clazz clazz : clazzes) {
            Teacher teacher = teacherClassMapper.findByClassId(clazz.getId());
            if (teacher != null) {
                clazz.setCreateby(teacher.getName());
            } else {
                clazz.setCreateby("<p style='color:red'>未分配指导老师</p>");
            }

        }
        return ResultUtil.success(clazzes, clazzes.size());
    }

    @Override
    public Result<Clazz> classList2(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Clazz> clazzes = teacherMapper.classList();
        List<Clazz> clazzList = new ArrayList<>();
        for (Clazz clazz : clazzes) {
            List<TeacherClass> exitTeacherClass = teacherMapper.isExitTeacherClass(clazz.getId());
            if (exitTeacherClass.size() == 0) {
                clazzList.add(clazz);
            }
        }
        return ResultUtil.success(clazzList, clazzList.size());
    }


    @Override
    public Clazz findByClassId(String classId) {

        Clazz clazz = teacherMapper.findByClassId(classId);
        Teacher teacher = teacherClassMapper.findByClassId(clazz.getId());
        if (teacher != null) {
            clazz.setCreateby(teacher.getName());
        } else {
            clazz.setCreateby("no");
        }
        return clazz;
    }

    @Override
    public Student findByStudent(String studentId) {
        Student student = teacherMapper.findByStudent(studentId);
        // 根据班级id选择班级
        Clazz clazz = teacherMapper.findByClassId(student.getClassId());
        student.setClassId(clazz.getClassName());
        return student;
    }

    @Override
    public Teacher findByTeacher(String teacherId) {
        return teacherMapper.findByTeacher(teacherId);
    }

    @Override
    public int alterClass(Clazz clazz) {
        Teacher teacher = (Teacher) session.getAttribute("admin");
        clazz.setUpdateby(teacher.getId());
        clazz.setUpdatetime(new Date());
        return teacherMapper.alterClass(clazz);
    }

    @Override
    public int insertByTeacher(Teacher teacher) {
        Teacher teacher1 = (Teacher) session.getAttribute("admin");
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        teacher.setId(id);
        teacher.setPassword(MD5Encryption.getMD5String("123456"));
        teacher.setCreateBy(teacher1.getId());
        teacher.setCreateTime(new Date());
        return teacherMapper.insertTeacher(teacher);
    }

    @Override
    public int delectByTeacherId(String teacherId) {
        return teacherMapper.deleteByTeacherId(teacherId);
    }


    @Override
    public int teacherNum() {
        return teacherMapper.teacherNum();
    }

    @Override
    public int studentNum() {
        return teacherMapper.studentNum();
    }

    @Override
    public int classNum() {
        return teacherMapper.classNum();
    }

    @Override
    public Result<Teacher> list() {

        List<Teacher> teacherList = teacherMapper.list();
        return ResultUtil.success(teacherList);
    }

    @Override
    public Teacher findById(String id) {
        return teacherMapper.findById(id);
    }

    @Override
    public Result countByTeacher() {
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        String id = teacher.getId();
        // 所带班级个数
        int classNum = teacherMapper.classNumByTeacher(id);
        // 学生人数
        int stuNum = teacherMapper.stuNumByTeacher(id);
        // 布置作业个数
        int taskNum = teacherMapper.taskByTeacher(id);
        // 学业计划
        int plan = teacherMapper.planNum(id);
        // 心得体会
        int xd = teacherMapper.xdNum(id);
        // 疑难问题
        int wd = teacherMapper.wdNum(id);
        teacher.setPassword("");
        Map<String, String> map = new HashMap<>();
        map.put("classNum", String.valueOf(classNum));
        map.put("stuNum", String.valueOf(stuNum));
        map.put("plan", String.valueOf(plan));
        map.put("taskNum", String.valueOf(taskNum));
        map.put("xd", String.valueOf(xd));
        map.put("wd", String.valueOf(wd));
        map.put("name", teacher.getName());
        return ResultUtil.success(map);
    }
}
