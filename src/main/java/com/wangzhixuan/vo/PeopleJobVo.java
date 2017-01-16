package com.wangzhixuan.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by wangwk on 2017/1/15.
 */
public class PeopleJobVo implements Serializable {
    private Long id;

    public String getJob_category() {
        return Job_category;
    }

    public void setJob_category(String job_category) {
        this.Job_category = job_category;
    }

    private String Job_category;

    public String getJob_level() {
        return Job_level;
    }

    public void setJob_level(String job_level) {
        this.Job_level = job_level;
    }

    private String Job_level;

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    private BigDecimal salary;

    public static Map<String,Object> CreateCondition(PeopleJobVo peoplejobvo) {
        Map<String, Object> condition = Maps.newHashMap();
        if(peoplejobvo.getJob_category()!=null){
            condition.put("Job_category", peoplejobvo.getJob_category().split(","));
        }
        if(StringUtils.isNoneBlank(peoplejobvo.getJob_category())){
            condition.put("Job_level", peoplejobvo.getJob_level());
        }
        if(StringUtils.isNoneBlank(peoplejobvo.getJob_category())){
            condition.put("salary", peoplejobvo.getSalary());
        }
        return condition;
    }
    }


