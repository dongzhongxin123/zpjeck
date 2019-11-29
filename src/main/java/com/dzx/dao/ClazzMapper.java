package com.dzx.dao;

import com.dzx.entity.Clazz;

import java.util.List;

public interface ClazzMapper {
    int countByclazz(Clazz clazz);

    int deleteByclazz(Clazz clazz);

    int deleteByPrimaryKey(String id);

    int insert(Clazz record);

    int insertSelective(Clazz record);

    List<Clazz> selectByclazz(Clazz clazz);

    Clazz selectByPrimaryKey(String id);

}