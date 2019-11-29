package com.dzx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    private String id;

    private String classId;

    private String name;

    private String number;

    private String password;

    private String phone;

    private String address;

    private String sex;

    private Integer age;

    private String portrait;

    private Byte flag;

    private Date createTime;

    private String createTy;

    private Date updateTime;

    private String updateBy;

}