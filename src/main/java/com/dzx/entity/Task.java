package com.dzx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task implements Serializable {
    private String id;

    private String teacherId;

    private String title;

    private String content;

    private Byte flag;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

}