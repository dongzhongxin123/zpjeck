package com.dzx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clazz implements Serializable {
    private String id;

    private String className;

    private String classNum;

    private Byte flag;

    private Date createtime;

    private String createby;

    private Date updatetime;

    private String updateby;

}