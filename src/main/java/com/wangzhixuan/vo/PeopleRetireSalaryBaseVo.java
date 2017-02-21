package com.wangzhixuan.vo;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.wangzhixuan.utils.ConstUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

public class PeopleRetireSalaryBaseVo implements Serializable {

	private Integer id;

	private String peopleCode;

	private String peopleName;

	private Integer sex;

	private String birthdayMin;

	private String birthdayMax;

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

	public void setHealthAllowance(BigDecimal healthAllowance) {
		this.healthAllowance = healthAllowance;
	}

	public BigDecimal getMedicare() {
		return medicare;
	}

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

	public String getPeopleName() {
		return peopleName;
	}

	public void setPeopleName(String peopleName) {
		this.peopleName = peopleName;
	}

	public BigDecimal getRetireFeeIncrease() {
		return retireFeeIncrease;
	}

	public void setRetireFeeIncrease(BigDecimal retireFeeIncrease) {
		this.retireFeeIncrease = retireFeeIncrease;
	}

	public String getLastUpdateDate() {return lastUpdateDate;}

	public void setLastUpdateDate(String lastUpdateDate) {this.lastUpdateDate = lastUpdateDate;}
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	public static Map<String,Object> CreateCondition(PeopleRetireSalaryBaseVo peopleRetireSalaryBaseVo) {
		Map<String,Object> condition = Maps.newHashMap();
		if (StringUtils.isNoneBlank(peopleRetireSalaryBaseVo.getPeopleName())){
			condition.put("name",peopleRetireSalaryBaseVo.getPeopleName());
		}
		condition.put("status", ConstUtil.PEOPLE_RETIRE);

		return condition;
	}
}
