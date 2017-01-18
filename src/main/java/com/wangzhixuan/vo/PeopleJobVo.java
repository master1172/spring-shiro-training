package com.wangzhixuan.vo;

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
        return job_category;
    }

    public void setJob_category(String job_category) {
        this.job_category = job_category;
    }

    private String job_category;

    public String getJob_level() {
        return job_level;
    }

    public void setJob_level(String job_level) {
        this.job_level = job_level;
    }

    private String job_level;

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
            condition.put("job_category", peoplejobvo.getJob_category().split(","));
        }
        if(StringUtils.isNoneBlank(peoplejobvo.getJob_level())){
            condition.put("job_level", peoplejobvo.getJob_level());
        }
        if(StringUtils.isNoneBlank(peoplejobvo.getSalary().toString())){
            condition.put("salary", peoplejobvo.getSalary());
        }
        return condition;
    }
    }


