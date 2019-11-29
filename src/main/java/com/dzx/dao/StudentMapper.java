package com.dzx.dao;

import com.dzx.entity.Interlocution;
import com.dzx.entity.Student;
import com.dzx.entity.StudentTask;
import com.dzx.pojo.StudentClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    /*
     *  统计学生多少人  根据  班级id
     */
    int countBystudent(Student student);

    /*
     *  删除学生  根据学生id  逻辑删除，非物理删除
     */
    int deleteByPrimaryKey(String id);

    /*
     *  添加学生
     */
    int insert(Student student);

    /*
     *  查找学生列表，根据班级id
     */
    List<Student> selectBystudent(Student student);

    /*
     *  查找某个学生 根据学生id
     */
    StudentClass selectByPrimaryKey(String id);

    /*
     *  更新学生信息
     */
    int updateByPrimaryKey(Student student);

    /*
     *  学生登录
     */
    Student login(@Param("number") String number);

    Student selectById(String id);


    int plan(String id);

    int wd(String id);

    int xd(String id);

    int task(String id);


    List<StudentTask> taskList(@Param("studentId") String studentId);

    List<Interlocution> xwdList(@Param("studentId") String studentId);


}