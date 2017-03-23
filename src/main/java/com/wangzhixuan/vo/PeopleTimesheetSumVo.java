package com.wangzhixuan.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by sterm on 2017/3/22.
 */
public class PeopleTimesheetSumVo implements Serializable{

    private Integer id;

    private String code;

    private String name;

    private BigDecimal vacationSum;

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

    public BigDecimal getVacationSum() {
        return vacationSum;
    }

    public void setVacationSum(BigDecimal vacationSum) {
        this.vacationSum = vacationSum;
    }
}
