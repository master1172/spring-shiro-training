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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;

    public String getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    private String jobCategory;

    public String getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
    }

    private String jobLevel;

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    private BigDecimal salary;

    public static Map<String,Object> createCondition(PeopleJobVo peoplejobvo) {
        Map<String, Object> condition = Maps.newHashMap();
        if(peoplejobvo.getJobCategory()!=null){
            condition.put("jobCategory", peoplejobvo.getJobCategory().split(","));
        }
        if(StringUtils.isNoneBlank(peoplejobvo.getJobLevel())){
            condition.put("jobLevel", peoplejobvo.getJobLevel());
        }
        if(peoplejobvo.getSalary() !=null){
            condition.put("salary", peoplejobvo.getSalary());
        }
        return condition;
    }
    }


