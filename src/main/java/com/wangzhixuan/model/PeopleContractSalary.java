package com.wangzhixuan.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by sterm on 2017/1/12.
 */
public class PeopleContractSalary implements Serializable {

	private Integer    id;
	private String     peopleCode;
	private Integer    jobId;
	private BigDecimal jobSalary;
	private BigDecimal schoolSalary;
	private String     examResult;
	private BigDecimal baseSalary;
	private BigDecimal jobExamSalary;
	private BigDecimal jobExamSalaryTotal;
	private BigDecimal telephoneAllowance;
	private BigDecimal trafficAllowance;
	private BigDecimal specialAllowance;
	private BigDecimal headAllowance;
	private BigDecimal onDutyFee;
	private BigDecimal onDutyDate;
	private BigDecimal onDutyFeeTotal;
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
	private BigDecimal timesheetStatus;

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

	public BigDecimal getTimesheetStatus() {
		return timesheetStatus;
	}

	public void setTimesheetStatus(BigDecimal timesheetStatus) {
		this.timesheetStatus = timesheetStatus;
	}

	public BigDecimal getJobExamSalaryTotal() {
		return jobExamSalaryTotal;
	}

	public void setJobExamSalaryTotal(BigDecimal jobExamSalaryTotal) {
		this.jobExamSalaryTotal = jobExamSalaryTotal;
	}

	@Override
	public String toString() {
		return "PeopleContractSalary{" + "id=" + id + "}";
	}

}
