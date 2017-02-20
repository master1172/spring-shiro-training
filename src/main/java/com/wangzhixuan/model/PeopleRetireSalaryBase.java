package com.wangzhixuan.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;

public class PeopleRetireSalaryBase implements Serializable {

    private Integer id;

    private String peopleCode;

    private String peopleName;

    private BigDecimal baseSalary;

    private BigDecimal extraAllowance;

    private BigDecimal rentAllowance;

    private BigDecimal retireAllowance;

    private BigDecimal retireFeeIncrease;

    private BigDecimal foodAllowance;

    private BigDecimal healthAllowance;

    private BigDecimal medicare;

    private BigDecimal propertyAllowance;

    private BigDecimal heatingFee;

    private BigDecimal handicapAllowance;

    private BigDecimal grossIncome;

    private BigDecimal expense;

    private BigDecimal rentFee;

    private BigDecimal netIncome;

    private String lastUpdateDate;

    private static final long serialVersionUID = 1L;

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
        this.peopleCode = peopleCode == null ? null : peopleCode.trim();
    }

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(BigDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }

    public BigDecimal getExtraAllowance() {
        return extraAllowance;
    }

    public void setExtraAllowance(BigDecimal extraAllowance) {
        this.extraAllowance = extraAllowance;
    }

    public BigDecimal getRentAllowance() {
        return rentAllowance;
    }

    public void setRentAllowance(BigDecimal rentAllowance) {
        this.rentAllowance = rentAllowance;
    }

    public BigDecimal getRetireAllowance() {
        return retireAllowance;
    }

    public void setRetireAllowance(BigDecimal retireAllowance) {
        this.retireAllowance = retireAllowance;
    }

    public BigDecimal getFoodAllowance() {
        return foodAllowance;
    }

    public void setFoodAllowance(BigDecimal foodAllowance) {
        this.foodAllowance = foodAllowance;
    }

    public BigDecimal getHealthAllowance() {
        return healthAllowance;
    }

    public void setHealthAllowance(BigDecimal heathAllowance) {
        this.healthAllowance = heathAllowance;
    }

    public BigDecimal getMedicare() { return medicare; }

    public void setMedicare(BigDecimal medicareFee) {
        this.medicare = medicareFee;
    }

    public BigDecimal getPropertyAllowance() {
        return propertyAllowance;
    }

    public void setPropertyAllowance(BigDecimal propertyAllowance) {
        this.propertyAllowance = propertyAllowance;
    }

    public BigDecimal getHeatingFee() {
        return heatingFee;
    }

    public void setHeatingFee(BigDecimal heatingFee) {
        this.heatingFee = heatingFee;
    }

    public BigDecimal getHandicapAllowance() {
        return handicapAllowance;
    }

    public void setHandicapAllowance(BigDecimal handicapAllowance) {
        this.handicapAllowance = handicapAllowance;
    }

    public BigDecimal getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(BigDecimal grossIncome) {
        this.grossIncome = grossIncome;
    }

    public BigDecimal getExpense() {
        return expense;
    }

    public void setExpense(BigDecimal expense) {
        this.expense = expense;
    }

    public BigDecimal getRentFee() {
        return rentFee;
    }

    public void setRentFee(BigDecimal rentFee) {
        this.rentFee = rentFee;
    }

    public BigDecimal getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(BigDecimal netIncome) {
        this.netIncome = netIncome;
    }

    public BigDecimal getRetireFeeIncrease() {
		return retireFeeIncrease;
	}

	public void setRetireFeeIncrease(BigDecimal retireFeeIncrease) {
		this.retireFeeIncrease = retireFeeIncrease;
	}

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", peopleCode=").append(peopleCode);
        sb.append(", baseSalary=").append(baseSalary);
        sb.append(", extraAllowance=").append(extraAllowance);
        sb.append(", rentAllowance=").append(rentAllowance);
        sb.append(", retireAllowance=").append(retireAllowance);
        sb.append(", retireFeeAllowance=").append(retireFeeIncrease);
        sb.append(", foodAllowance=").append(foodAllowance);
        sb.append(", healthAllowance=").append(healthAllowance);
        sb.append(", medicare=").append(medicare);
        sb.append(", propertyAllowance=").append(propertyAllowance);
        sb.append(", heatingFee=").append(heatingFee);
        sb.append(", handicapAllowance=").append(handicapAllowance);
        sb.append(", grossIncome=").append(grossIncome);
        sb.append(", expense=").append(expense);
        sb.append(", rentFee=").append(rentFee);
        sb.append(", netIncome=").append(netIncome);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}