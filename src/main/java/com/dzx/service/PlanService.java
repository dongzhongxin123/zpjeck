package com.dzx.service;


import com.dzx.entity.Plan;
import com.dzx.util.Result;

/**
 * @Auther: Zpjeck
 * @Date: 2019/3/31 21:32
 * @Description:
 */
public interface PlanService {

    /*
     *  添加计划
     */
    int insertPlan(Plan plan);

    Result<Plan> findList(Integer pageNum, Integer pageSize, String teacherId);

    Result<Plan> findByStudentId(Integer pageNum, Integer pageSize, String studentId);

    Plan findById(String id);

}
