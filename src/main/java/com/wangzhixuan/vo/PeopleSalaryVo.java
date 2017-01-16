package com.wangzhixuan.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by sterm on 2017/1/15.
 */
public class PeopleSalaryVo implements Serializable{

    private Long id;

    private Long peopleCode;

    private String peopleName;

    private Integer age;

    private int jobId;

    private String jobCategory;

    private String jobLevel;

    private BigDecimal jobSalary;

    private int rankId;

    private BigDecimal rankSalary;

    private BigDecimal reserveSalary;

    private String examResult;

    private BigDecimal jobAllowance;

    private BigDecimal performanceAllowance;

    private BigDecimal rentAllowance;

    private BigDecimal houseAllowance;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String workDate;

    private BigDecimal timeSheetStatus;

    private BigDecimal dutyAllowance;

    private BigDecimal extraAllowance;

    private BigDecimal telephoneAllowance;

    private BigDecimal trafficAllowance;

    private BigDecimal onDutyFee;

    private BigDecimal onDutyDate;

    private BigDecimal onDutyFeeTotal;

    private BigDecimal propertyAllowance;

    private BigDecimal extraJobAllowance;

    private BigDecimal temperatureAllowance;

    private BigDecimal reissueFee;

    private BigDecimal medicare;

    private BigDecimal yearlyBonus;

    private BigDecimal grossSalary;

    private BigDecimal lifeInsurance;

    private BigDecimal jobInsurance;

    private BigDecimal healthInsurance;

    private BigDecimal annuity;

    private BigDecimal houseFund;

    private BigDecimal expense;

    private BigDecimal tax;

    private BigDecimal netIncome;

    @JsonFormat(pattern = "yyyy-MM")
    private String payDate;


}
