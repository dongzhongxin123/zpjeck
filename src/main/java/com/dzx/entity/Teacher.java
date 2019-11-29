package com.dzx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher implements Serializable {
    private String id;

    private String name;

    private String number;

    private String password;

    private String phone;

    private String address;

    private String sex;

    private Integer age;

    private String portrait;

    private String status;

    private Byte flag;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

}