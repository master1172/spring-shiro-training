package com.wangzhixuan.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by sterm on 2017/1/12.
 */
public class PeopleSalaryBase implements Serializable {

    private static final long serialVersionUID = -5321613594382537452L;

    private Integer id;

    private String peopleCode;

    private String peopleName;

    private Integer jobId;

    private BigDecimal jobSalary;

    private Integer rankId;

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

    private BigDecimal lifeInsuranceBase;

    private BigDecimal jobInsuranceBase;

    private BigDecimal woundInsuranceBase;

    private BigDecimal birthInsuranceBase;

    private BigDecimal healthInsuranceBase;

    private BigDecimal annuityBase;

    @JsonFormat(pattern = "yyyy-MM-dd")
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

    public Integer getRankId() {
        return rankId;
    }

    public void setRankId(Integer rankId) {
        this.rankId = rankId;
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

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastChangeDate) {
        this.lastUpdateDate = lastChangeDate;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
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

    @Override
    public String toString(){
        return "PeopleSalaryBase{" +
                "id=" + id +
                "}";
    }
}
