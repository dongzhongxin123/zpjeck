package com.dzx.dao;


import com.dzx.entity.Interlocution;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InterlocutionMapper {
    int countByinterlocution(Interlocution interlocution);

    int deleteByinterlocution(Interlocution interlocution);

    int deleteByPrimaryKey(String id);


    int insertSelective(Interlocution record);

    List<Interlocution> selectByinterlocution(Interlocution interlocution);


    int updateByinterlocutionSelective(@Param("record") Interlocution record, @Param("interlocution") Interlocution interlocution);

    int updateByinterlocution(@Param("record") Interlocution record, @Param("interlocution") Interlocution interlocution);

    int updateByPrimaryKeySelective(Interlocution record);

    int updateByPrimaryKey(Interlocution record);

    // 开始

    int insert(Interlocution interlocution);

    Interlocution selectByPrimaryKey(String id);

    List<Interlocution> list(@Param("student_id") String studentId, @Param("type") String type);

    List<Interlocution> listByClass(String classId);

    int replyStudent(Interlocution interlocution);

    int deleteById(String id);

    /*
     *  根据类型查询
     */

    List<Interlocution> findListByType(@Param("type") String type, @Param("teacherId") String teacherId);

    /*
     *  根据班级查询
     */

    List<Interlocution> findListByClass(@Param("type") String type, @Param("teacherId") String teacherId, @Param("classId") String classId);


}