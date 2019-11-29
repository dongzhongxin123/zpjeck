package com.dzx.dao;


import com.dzx.entity.StudentTask;
import com.dzx.entity.Task;
import com.dzx.pojo.TaskStudentPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskMapper {
    int countBytask(Task task);

    int deleteBytask(Task task);


    int insert(Task record);

    int insertSelective(Task record);

    List<Task> selectBytask(Task task);


    int updateBytaskSelective(@Param("record") Task record, @Param("task") Task task);

    int updateBytask(@Param("record") Task record, @Param("task") Task task);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);

    // 开始
    Task selectByPrimaryKey(String id);

    List<Task> selectBystudent(String id);

    List<Task> selectByTeacher(@Param("teacherId") String teacherId);

    int save(Task task);

    TaskStudentPojo findStudentByTaskId(@Param("id") String taskId);

    List<StudentTask> isExitStudentTask(@Param("studenId") String id, @Param("taskId") String taskId);

    int deleteTask(String id);

    int updateTask(Task task);

    /*
     *  默认查询为所有数据
     */
    List<StudentTask> studentTaskList(@Param("teacherId") String teacherId);

    /*
     *  按照班级查询
     */
    List<StudentTask> studentTaskList2(@Param("teacherId") String teacherId, @Param("classId") String classId, @Param("taskId") String taskId);

    StudentTask findById(String id);
}