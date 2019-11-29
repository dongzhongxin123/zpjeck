package com.dzx.dao;


import com.dzx.entity.StudentTask;

public interface StudentTaskMapper {


    // 开始

    int save(StudentTask studentTask);


    int relayTask(StudentTask studentTask);

}