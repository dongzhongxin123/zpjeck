package com.dzx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherClass implements Serializable {
    private String id;

    private String teacherId;

    private String classId;

    private Byte flag;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

}