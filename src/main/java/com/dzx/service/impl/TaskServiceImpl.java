package com.dzx.service.impl;


import com.dzx.Enum.ResultEnum;
import com.dzx.dao.StudentTaskMapper;
import com.dzx.dao.TaskMapper;
import com.dzx.entity.StudentTask;
import com.dzx.entity.Task;
import com.dzx.entity.Teacher;
import com.dzx.pojo.StudentClass;
import com.dzx.pojo.TaskStudentPojo;
import com.dzx.service.StudentService;
import com.dzx.service.TaskService;
import com.dzx.service.TeacherService;
import com.dzx.util.Result;
import com.dzx.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Auther: Zpjeck
 * @Date: 2019/3/31 15:02
 * @Description:
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private StudentTaskMapper studentTaskMapper;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private HttpSession session;


    @Override
    public Task selectById(String id) {
        Task task = taskMapper.selectByPrimaryKey(id);
        return task;
    }

    // 查询学生已完成列表
    @Override
    public Result<Task> selectBystudent(Integer pageNum, Integer pageSize, String id) {
        Page page = PageHelper.startPage(pageNum, pageSize, true);
        // 查询数据
        List<Task> list = taskMapper.selectBystudent(id);
        List<Task> taskList = new ArrayList<>();

        for (Task task : list) {
            Teacher teacher = teacherService.findByTeacher(task.getTeacherId());
            task.setCreateBy(teacher.getName());
            List<StudentTask> exitStudentTask = taskMapper.isExitStudentTask(id, task.getId());
            System.out.println(exitStudentTask);
            if (exitStudentTask != null && exitStudentTask.size() != 0) {
                taskList.add(task);
            }
        }
        PageInfo<Task> pageInfo = new PageInfo<>(taskList);
        int total = (int) pageInfo.getTotal();

        return ResultUtil.success(taskList, total);
    }

    @Override
    public Result<Task> selectByTeacher(Integer pageNum, Integer pageSize, String teacherId) {
        Page page = PageHelper.startPage(pageNum, pageSize, true);
        // 查询数据
        List<Task> list = taskMapper.selectByTeacher(teacherId);
        for (Task task : list) {
            Teacher teacher = teacherService.findByTeacher(task.getTeacherId());
            task.setCreateBy(teacher.getName());
        }
        PageInfo<Task> pageInfo = new PageInfo<Task>(list);
        long total = pageInfo.getTotal();
        int size = (int) total;

        return ResultUtil.success(list, size);
    }

    @Override
    public int save(Task task) {
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        task.setCreateBy(teacher.getId());
        task.setCreateTime(new Date());
        task.setTeacherId(teacher.getId());
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        task.setId(id);
        return taskMapper.save(task);
    }

    @Override
    public int relayTask(StudentTask studentTask) {
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        studentTask.setUpdateTime(new Date());
        studentTask.setUpdateBy(teacher.getId());
        return studentTaskMapper.relayTask(studentTask);
    }

    @Override
    public Result<TaskStudentPojo> findStudentByTaskId(String taskId) {
        TaskStudentPojo taskStudentPojo = taskMapper.findStudentByTaskId(taskId);
        if (taskStudentPojo == null) {
            return ResultUtil.error(-1, "查询失败");
        }
        return ResultUtil.success(taskStudentPojo);
    }

    // 查询未完成列表
    @Override
    public Result<Task> listByStudentUnfinish(Integer pageNum, Integer pageSize, String id) {
        Page page = PageHelper.startPage(pageNum, pageSize, true);
        // 查询数据
        List<Task> list = taskMapper.selectBystudent(id);
        List<Task> taskList = new ArrayList<>();

        for (Task task : list) {
            Teacher teacher = teacherService.findByTeacher(task.getTeacherId());
            task.setCreateBy(teacher.getName());
            List<StudentTask> exitStudentTask = taskMapper.isExitStudentTask(id, task.getId());
            System.out.println(exitStudentTask);
            if (exitStudentTask.size() == 0) {
                taskList.add(task);
            }
        }
        PageInfo<Task> pageInfo = new PageInfo<>(taskList);
        int total = (int) pageInfo.getTotal();


        return ResultUtil.success(taskList, total);
    }


    @Override
    public Result deleteTask(String id) {
        int i = taskMapper.deleteTask(id);
        if (i == 0) {
            return ResultUtil.error(ResultEnum.USER_DATABASE_FAIL.getCode(), "删除失败");
        }
        return ResultUtil.success();
    }

    @Override
    public Result updateTask(Task task) {
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        task.setUpdateBy(teacher.getId());
        task.setUpdateTime(new Date());
        int i = taskMapper.updateTask(task);
        if (i == 0) {
            return ResultUtil.error(ResultEnum.USER_DATABASE_FAIL.getCode(), "删除失败");
        }
        return ResultUtil.success();
    }

    @Override
    public Result studentTaskList(Integer pageNum, Integer pageSize, String classId, String taskId) {
        Page page = PageHelper.startPage(pageNum, pageSize, true);

        Teacher teacher = (Teacher) session.getAttribute("teacher");
        List<StudentTask> studentTaskList = new ArrayList<>();
        if (classId == null) {
            studentTaskList = taskMapper.studentTaskList(teacher.getId());
        } else {
            studentTaskList = taskMapper.studentTaskList2(teacher.getId(), classId, taskId);
        }
        for (StudentTask studentTask : studentTaskList) {
            if (studentTask != null) {
                StudentClass studentClass = studentService.selectByPrimaryKey(studentTask.getStudentId());
                studentTask.setStudentId(studentClass.getName());
                studentTask.setClassId(studentClass.getClassName());
            }
            Task task = taskMapper.selectByPrimaryKey(studentTask.getTaskId());
            studentTask.setTaskId(task.getTitle());

        }
        PageInfo<StudentTask> pageInfo = new PageInfo<>(studentTaskList);
        int total = (int) pageInfo.getTotal();
        return ResultUtil.success(studentTaskList, total);
    }

    @Override
    public Result<StudentTask> findById(String id) {
        StudentTask studentTask = taskMapper.findById(id);

        if (studentTask != null) {
            StudentClass studentClass = studentService.selectByPrimaryKey(studentTask.getStudentId());
            studentTask.setStudentId(studentClass.getName());
            studentTask.setClassId(studentClass.getClassName());
            Task task = taskMapper.selectByPrimaryKey(studentTask.getTaskId());
            studentTask.setTaskId(task.getTitle());
            studentTask.setCreateBy(task.getContent());
        }
        return ResultUtil.success(studentTask);
    }
}
