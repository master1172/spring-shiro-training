package com.wangzhixuan.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sterm on 2017/1/15.
 */
public class PeopleSalaryBaseVo implements Serializable{

    private Long id;

    private String peopleCode;

    private String peopleName;

    private Integer age;

    private int jobId;

    private String jobCategory;

    private String jobLevel;

    private BigDecimal jobSalary;

    private int rankId;

    private String rankLevel;

    private BigDecimal rankSalary;

    private BigDecimal reserveSalary;

    private String examResult;

    private BigDecimal jobAllowance;

    private BigDecimal performanceAllowance;

    private BigDecimal rentAllowance;

    private BigDecimal houseAllowance;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String workDate;

    private BigDecimal timesheetStatus;

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

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String lastChangeDate;

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

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    public String getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
    }

    public BigDecimal getJobSalary() {
        return jobSalary;
    }

    public void setJobSalary(BigDecimal jobSalary) {
        this.jobSalary = jobSalary;
    }

    public int getRankId() {
        return rankId;
    }

    public void setRankId(int rankId) {
        this.rankId = rankId;
    }

    public String getRankLevel() {
        return rankLevel;
    }

    public void setRankLevel(String rankLevel) {
        this.rankLevel = rankLevel;
    }

    public BigDecimal getRankSalary() {
        return rankSalary;
    }

    public void setRankSalary(BigDecimal rankSalary) {
        this.rankSalary = rankSalary;
    }

    public BigDecimal getReserveSalary() {
        return reserveSalary;
    }

    public void setReserveSalary(BigDecimal reserveSalary) {
        this.reserveSalary = reserveSalary;
    }

    public String getExamResult() {
        return examResult;
    }

    public void setExamResult(String examResult) {
        this.examResult = examResult;
    }

    public BigDecimal getJobAllowance() {
        return jobAllowance;
    }

    public void setJobAllowance(BigDecimal jobAllowance) {
        this.jobAllowance = jobAllowance;
    }

    public BigDecimal getPerformanceAllowance() {
        return performanceAllowance;
    }

    public void setPerformanceAllowance(BigDecimal performanceAllowance) {
        this.performanceAllowance = performanceAllowance;
    }

    public BigDecimal getRentAllowance() {
        return rentAllowance;
    }

    public void setRentAllowance(BigDecimal rentAllowance) {
        this.rentAllowance = rentAllowance;
    }

    public BigDecimal getHouseAllowance() {
        return houseAllowance;
    }

    public void setHouseAllowance(BigDecimal houseAllowance) {
        this.houseAllowance = houseAllowance;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public BigDecimal getTimesheetStatus() {
        return timesheetStatus;
    }

    public void setTimesheetStatus(BigDecimal timesheetStatus) {
        this.timesheetStatus = timesheetStatus;
    }

    public BigDecimal getDutyAllowance() {
        return dutyAllowance;
    }

    public void setDutyAllowance(BigDecimal dutyAllowance) {
        this.dutyAllowance = dutyAllowance;
    }

    public BigDecimal getExtraAllowance() {
        return extraAllowance;
    }

    public void setExtraAllowance(BigDecimal extraAllowance) {
        this.extraAllowance = extraAllowance;
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

    public BigDecimal getOnDutyFee() {
        return onDutyFee;
    }

    public void setOnDutyFee(BigDecimal onDutyFee) {
        this.onDutyFee = onDutyFee;
    }

    public BigDecimal getOnDutyDate() {
        return onDutyDate;
    }

    public void setOnDutyDate(BigDecimal onDutyDate) {
        this.onDutyDate = onDutyDate;
    }

    public BigDecimal getOnDutyFeeTotal() {
        return onDutyFeeTotal;
    }

    public void setOnDutyFeeTotal(BigDecimal onDutyFeeTotal) {
        this.onDutyFeeTotal = onDutyFeeTotal;
    }

    public BigDecimal getPropertyAllowance() {
        return propertyAllowance;
    }

    public void setPropertyAllowance(BigDecimal propertyAllowance) {
        this.propertyAllowance = propertyAllowance;
    }

    public BigDecimal getExtraJobAllowance() {
        return extraJobAllowance;
    }

    public void setExtraJobAllowance(BigDecimal extraJobAllowance) {
        this.extraJobAllowance = extraJobAllowance;
    }

    public BigDecimal getTemperatureAllowance() {
        return temperatureAllowance;
    }

    public void setTemperatureAllowance(BigDecimal temperatureAllowance) {
        this.temperatureAllowance = temperatureAllowance;
    }

    public BigDecimal getReissueFee() {
        return reissueFee;
    }

    public void setReissueFee(BigDecimal reissueFee) {
        this.reissueFee = reissueFee;
    }

    public BigDecimal getMedicare() {
        return medicare;
    }

    public void setMedicare(BigDecimal medicare) {
        this.medicare = medicare;
    }

    public BigDecimal getYearlyBonus() {
        return yearlyBonus;
    }

    public void setYearlyBonus(BigDecimal yearlyBonus) {
        this.yearlyBonus = yearlyBonus;
    }

    public BigDecimal getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(BigDecimal grossSalary) {
        this.grossSalary = grossSalary;
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

    public BigDecimal getAnnuity() {
        return annuity;
    }

    public void setAnnuity(BigDecimal annuity) {
        this.annuity = annuity;
    }

    public BigDecimal getHouseFund() {
        return houseFund;
    }

    public void setHouseFund(BigDecimal houseFund) {
        this.houseFund = houseFund;
    }

    public BigDecimal getExpense() {
        return expense;
    }

    public void setExpense(BigDecimal expense) {
        this.expense = expense;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(BigDecimal netIncome) {
        this.netIncome = netIncome;
    }

    public String getLastChangeDate() {
        return lastChangeDate;
    }

    public void setLastChangeDate(String lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }

    public String toString(){
        return "";
    }

    public static Map<String,Object> createCondition(PeopleSalaryBaseVo peopleSalaryBaseVo) {
        Map<String,Object> condition = Maps.newHashMap();


        if(StringUtils.isNoneBlank(peopleSalaryBaseVo.getPeopleName())){
            condition.put("name", peopleSalaryBaseVo.getPeopleName());
        }



        condition.put("jobId", peopleSalaryBaseVo.getJobId());
        condition.put("rankId", peopleSalaryBaseVo.getRankId());


        return condition;
    }
}
