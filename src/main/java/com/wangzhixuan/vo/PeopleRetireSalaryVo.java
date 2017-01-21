package com.wangzhixuan.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alibaba.fastjson.JSON;

public class PeopleRetireSalaryVo implements Serializable {
	private Integer id;

	private String peopleCode;

	private String peopleName;
	private int jobId;
	private String jobCategory;
	private String jobLevel;
	private BigDecimal jobSalary;
	private String retireJobName;
	private String retireJobLevelId;
	
	private BigDecimal baseSalary;

	private Integer rankId;

	private BigDecimal extraAllowance;

	private BigDecimal rentAllowance;

	private BigDecimal retireAllowance;

	private BigDecimal retireFeeIncrease;

	private BigDecimal foodAllowance;

	private BigDecimal heathAllowance;

	private BigDecimal medicareFee;

	private BigDecimal propertyAllowance;

	private BigDecimal heatingFee;

	private BigDecimal handicapAllowance;

	private BigDecimal grossIncome;

	private BigDecimal expense;

	private BigDecimal rentFee;

	private BigDecimal netIncome;

	private String payDate;

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

	public Integer getRankId() {
		return rankId;
	}

	public void setRankId(Integer rankId) {
		this.rankId = rankId;
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

	public BigDecimal getHeathAllowance() {
		return heathAllowance;
	}

	public void setHeathAllowance(BigDecimal heathAllowance) {
		this.heathAllowance = heathAllowance;
	}

	public BigDecimal getMedicareFee() {
		return medicareFee;
	}

	public void setMedicareFee(BigDecimal medicareFee) {
		this.medicareFee = medicareFee;
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

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate == null ? null : payDate.trim();
	}

	public String getPeopleName() {
		return peopleName;
	}

	public void setPeopleName(String peopleName) {
		this.peopleName = peopleName;
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

	public String getRetireJobName() {
		return retireJobName;
	}

	public void setRetireJobName(String retireJobName) {
		this.retireJobName = retireJobName;
	}

	public String getRetireJobLevelId() {
		return retireJobLevelId;
	}

	public void setRetireJobLevelId(String retireJobLevelId) {
		this.retireJobLevelId = retireJobLevelId;
	}

	public BigDecimal getRetireFeeIncrease() {
		return retireFeeIncrease;
	}

	public void setRetireFeeIncrease(BigDecimal retireFeeIncrease) {
		this.retireFeeIncrease = retireFeeIncrease;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
