package com.wangzhixuan.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by sterm on 2017/2/12.
 */
public class PeopleContractSalaryBase implements Serializable {
    private Long id;
    private String peopleCode;
    private Integer jobId;
    private BigDecimal jobSalary;
    private BigDecimal schoolSalary;
    private String examResult;
    private BigDecimal baseSalary;
    private BigDecimal jobExamSalary;
    private BigDecimal telephoneAllowance;
    private BigDecimal trafficAllowance;
    private BigDecimal specialAllowance;
    private BigDecimal headAllowance;
    private BigDecimal extraWorkFee;
    private BigDecimal extraWorkDate;
    private BigDecimal extraWorkAllowance;
    private BigDecimal bonus;
    private BigDecimal reissueFee;
    private BigDecimal temperatureAllowance;
    private BigDecimal grossIncome;
    private BigDecimal lifeInsurance;
    private BigDecimal jobInsurance;
    private BigDecimal healthInsurance;
    private BigDecimal houseFund;
    private BigDecimal Expense;
    private BigDecimal netIncome;
    @JsonFormat(pattern = "yyyy-MM")
    private String payDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPeopleCode() {
        return peopleCode;
    }

    public void setPeopleCode(String peopleCode) {
        this.peopleCode = peopleCode;
    }

    public BigDecimal getBaseSalary() {return baseSalary;}

    public void setBaseSalary(BigDecimal baseSalary) {this.baseSalary = baseSalary;}

    public int getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public BigDecimal getJobSalary() {
        return jobSalary;
    }

    public void setJobSalary(BigDecimal jobSalary) {
        this.jobSalary = jobSalary;
    }

    public BigDecimal getSchoolSalary() {
        return schoolSalary;
    }

    public void setSchoolSalary(BigDecimal schoolSalary) {
        this.schoolSalary = schoolSalary;
    }

    public String getExamResult() {
        return examResult;
    }

    public void setExamResult(String examResult) {
        this.examResult = examResult;
    }

    public BigDecimal getJobExamSalary() {
        return jobExamSalary;
    }

    public void setJobExamSalary(BigDecimal jobExamSalary) {
        this.jobExamSalary = jobExamSalary;
    }

    public BigDecimal getTelephoneAllowance() {
        return telephoneAllowance;
    }

    public void setTelephoneAllowance(BigDecimal telephoneAllowance) {
        this.telephoneAllowance = telephoneAllowance;
    }

    public BigDecimal getTrafficAllowance() {
        return trafficAllowance;
    }

    public void setTrafficAllowance(BigDecimal trafficAllowance) {
        this.trafficAllowance = trafficAllowance;
    }

    public BigDecimal getSpecialAllowance() {
        return specialAllowance;
    }

    public void setSpecialAllowance(BigDecimal specialAllowance) {
        this.specialAllowance = specialAllowance;
    }

    public BigDecimal getHeadAllowance() {
        return headAllowance;
    }

    public void setHeadAllowance(BigDecimal headAllowance) {
        this.headAllowance = headAllowance;
    }

    public BigDecimal getExtraWorkFee() {
        return extraWorkFee;
    }

    public void setExtraWorkFee(BigDecimal extraWorkFee) {
        this.extraWorkFee = extraWorkFee;
    }

    public BigDecimal getExtraWorkDate() {
        return extraWorkDate;
    }

    public void setExtraWorkDate(BigDecimal extraWorkDate) {
        this.extraWorkDate = extraWorkDate;
    }

    public BigDecimal getExtraWorkAllowance() {
        return extraWorkAllowance;
    }

    public void setExtraWorkAllowance(BigDecimal extraWorkAllowance) {
        this.extraWorkAllowance = extraWorkAllowance;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public BigDecimal getReissueFee() {
        return reissueFee;
    }

    public void setReissueFee(BigDecimal reissueFee) {
        this.reissueFee = reissueFee;
    }

    public BigDecimal getTemperatureAllowance() {
        return temperatureAllowance;
    }

    public void setTemperatureAllowance(BigDecimal temperatureAllowance) {
        this.temperatureAllowance = temperatureAllowance;
    }

    public BigDecimal getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(BigDecimal grossIncome) {
        this.grossIncome = grossIncome;
    }

    public BigDecimal getLifeInsurance() {
        return lifeInsurance;
    }

    public void setLifeInsurance(BigDecimal lifeInsurance) {
        this.lifeInsurance = lifeInsurance;
    }

    public BigDecimal getJobInsurance() {
        return jobInsurance;
    }

    public void setJobInsurance(BigDecimal jobInsurance) {
        this.jobInsurance = jobInsurance;
    }

    public BigDecimal getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(BigDecimal healthInsurance) {
        this.healthInsurance = healthInsurance;
    }

    public BigDecimal getHouseFund() {
        return houseFund;
    }

    public void setHouseFund(BigDecimal houseFund) {
        this.houseFund = houseFund;
    }

    public BigDecimal getExpense() {
        return Expense;
    }

    public void setExpense(BigDecimal expense) {
        Expense = expense;
    }

    public BigDecimal getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(BigDecimal netIncome) {
        this.netIncome = netIncome;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    @Override
    public String toString() {
        return "PeopleContractSalaryBase{" + "id=" + id + "}";
    }
}
