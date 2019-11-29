package com.dzx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentTask implements Serializable {
    private String id;

    private String taskId;

    private String studentId;

    private String classId;

    private String answer;

    private String feedback;

    private Byte flag;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private Integer score;

}