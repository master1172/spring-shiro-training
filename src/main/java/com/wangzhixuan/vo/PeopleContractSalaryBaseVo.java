package com.wangzhixuan.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Maps;
import com.wangzhixuan.utils.ConstUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by sterm on 2017/2/20.
 */
public class PeopleContractSalaryBaseVo implements Serializable{

    private Integer    id;
    private String     peopleCode;
    private String     peopleName;
    private Integer    sex;
    private String     birthdayMin;
    private String     birthdayMax;
    private Integer    jobId;
    private String     jobIdList;
    private BigDecimal jobSalary;
    private String     jobLevel;
    private BigDecimal schoolSalary;
    private String     examResult;
    private BigDecimal baseSalary;
    private BigDecimal jobExamSalary;
    private BigDecimal telephoneAllowance;
    private BigDecimal trafficAllowance;
    private BigDecimal specialAllowance;
    private BigDecimal headAllowance;
    private BigDecimal onDutyFee;
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
    private String lastUpdateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getJobId() {
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

    public String getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
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

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(BigDecimal baseSalary) {
        this.baseSalary = baseSalary;
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

    public BigDecimal getOnDutyFee() {
        return onDutyFee;
    }

    public void setOnDutyFee(BigDecimal onDutyFee) {
        this.onDutyFee = onDutyFee;
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

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getJobIdList() {
        return jobIdList;
    }

    public void setJobIdList(String jobIdList) {
        this.jobIdList = jobIdList;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBirthdayMin() {
        return birthdayMin;
    }

    public void setBirthdayMin(String birthdayMin) {
        this.birthdayMin = birthdayMin;
    }

    public String getBirthdayMax() {
        return birthdayMax;
    }

    public void setBirthdayMax(String birthdayMax) {
        this.birthdayMax = birthdayMax;
    }

    public static Map<String,Object> CreateCondition(PeopleContractSalaryBaseVo peopleContractSalaryBaseVo) {
        Map<String,Object> condition = Maps.newHashMap();
        if(StringUtils.isNoneBlank(peopleContractSalaryBaseVo.getPeopleName())){
            condition.put("name", peopleContractSalaryBaseVo.getPeopleName());
        }

        if(StringUtils.isNoneBlank(peopleContractSalaryBaseVo.getJobIdList())){
            condition.put("jobIdList", peopleContractSalaryBaseVo.getJobIdList().split(","));
        }

        if(peopleContractSalaryBaseVo.getSex() != null){
            condition.put("sex", peopleContractSalaryBaseVo.getSex());
        }

        if(StringUtils.isNoneBlank(peopleContractSalaryBaseVo.getBirthdayMin())){
            condition.put("birthdayMin", peopleContractSalaryBaseVo.getBirthdayMin());
        }

        if(StringUtils.isNoneBlank(peopleContractSalaryBaseVo.getBirthdayMax())){
            condition.put("birthdayMax", peopleContractSalaryBaseVo.getBirthdayMax());
        }

        condition.put("status", ConstUtil.PEOPLE_CONTRACT);
        return condition;
    }
}
