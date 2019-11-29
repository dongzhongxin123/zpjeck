package com.dzx.service;


import com.dzx.entity.StudentTask;

/**
 * @Auther: Zpjeck
 * @Date: 2019/3/31 16:56
 * @Description:
 */
public interface StudentTaskService {
    int save(StudentTask studentTask);

    int relayTask(StudentTask studentTask);
}
