package com.wangzhixuan.vo;

import com.google.common.collect.Maps;
import com.wangzhixuan.model.SocialSecurityBase;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by sterm on 2017/2/26.
 */
public class SocialSecurityBaseVo implements Serializable {
    private Integer id;

    private String code;

    private String name;

    private Integer departmentId;

    private BigDecimal lifeInsuranceBase;

    private BigDecimal jobInsuranceBase;

    private BigDecimal woundInsuranceBase;

    private BigDecimal birthInsuranceBase;

    private BigDecimal healthInsuranceBase;

    private BigDecimal annuityBase;

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public BigDecimal getLifeInsuranceBase() {
        return lifeInsuranceBase;
    }

    public void setLifeInsuranceBase(BigDecimal lifeInsuranceBase) {
        this.lifeInsuranceBase = lifeInsuranceBase;
    }

    public BigDecimal getJobInsuranceBase() {
        return jobInsuranceBase;
    }

    public void setJobInsuranceBase(BigDecimal jobInsuranceBase) {
        this.jobInsuranceBase = jobInsuranceBase;
    }

    public BigDecimal getWoundInsuranceBase() {
        return woundInsuranceBase;
    }

    public void setWoundInsuranceBase(BigDecimal woundInsuranceBase) {
        this.woundInsuranceBase = woundInsuranceBase;
    }

    public BigDecimal getBirthInsuranceBase() {
        return birthInsuranceBase;
    }

    public void setBirthInsuranceBase(BigDecimal birthInsuranceBase) {
        this.birthInsuranceBase = birthInsuranceBase;
    }

    public BigDecimal getHealthInsuranceBase() {
        return healthInsuranceBase;
    }

    public void setHealthInsuranceBase(BigDecimal healthInsuranceBase) {
        this.healthInsuranceBase = healthInsuranceBase;
    }

    public BigDecimal getAnnuityBase() {
        return annuityBase;
    }

    public void setAnnuityBase(BigDecimal annuityBase) {
        this.annuityBase = annuityBase;
    }

    public static Map<String,Object> CreateCondition(SocialSecurityBaseVo socialSecurityBaseVo) {
        Map<String,Object> condition = Maps.newHashMap();

        if(StringUtils.isNoneBlank(socialSecurityBaseVo.getName())){
            condition.put("name",socialSecurityBaseVo.getName());
        }

        if(socialSecurityBaseVo.getStatus() != null){
            condition.put("status",socialSecurityBaseVo.getStatus());
        }

        return condition;
    }
}
