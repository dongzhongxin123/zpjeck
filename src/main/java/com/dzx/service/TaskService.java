package com.dzx.service;


import com.dzx.entity.StudentTask;
import com.dzx.entity.Task;
import com.dzx.pojo.TaskStudentPojo;
import com.dzx.util.Result;
import org.apache.ibatis.annotations.Param;

/**
 * @Auther: Zpjeck
 * @Date: 2019/3/31 15:00
 * @Description:
 */
public interface TaskService {

    Task selectById(@Param("id") String id); // 根据id查询作业

    Result<Task> selectBystudent(Integer pageNum, Integer pageSize, String id);

    Result<Task> selectByTeacher(Integer pageNum, Integer pageSize, String teacherId);

    int save(Task task);

    int relayTask(StudentTask studentTask);

    Result<TaskStudentPojo> findStudentByTaskId(String taskId);

    Result<Task> listByStudentUnfinish(Integer pageNum, Integer pageSize, String id);

    Result deleteTask(String id);

    Result updateTask(Task task);

    Result<StudentTask> studentTaskList(Integer pageNum, Integer pageSize, String classId, String taskId);

    Result<StudentTask> findById(String id);
}
