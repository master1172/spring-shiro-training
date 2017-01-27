package com.wangzhixuan.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

public class PeopleTimesheetVo implements Serializable {
	private Integer id;

	private String peopleCode;

	private String peopleName;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private String checkDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private String checkDateMin;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private String checkDateMax;

	private String status;

	private BigDecimal vacationPeriod;

	private BigDecimal vacationPeriodMin;

	private BigDecimal vacationPeriodMax;

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

	public String getPeopleName() {
		return peopleName;
	}

	public void setPeopleName(String peopleName) {
		this.peopleName = peopleName;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public BigDecimal getVacationPeriod() {
		return vacationPeriod;
	}

	public void setVacationPeriod(BigDecimal vacationPeriod) {
		this.vacationPeriod = vacationPeriod;
	}

	public BigDecimal getVacationPeriodMin() {
		return vacationPeriodMin;
	}

	public void setVacationPeriodMin(BigDecimal vacationPeriodMin) {
		this.vacationPeriodMin = vacationPeriodMin;
	}

	public BigDecimal getVacationPeriodMax() {
		return vacationPeriodMax;
	}

	public void setVacationPeriodMax(BigDecimal vacationPeriodMax) {
		this.vacationPeriodMax = vacationPeriodMax;
	}

	public String getCheckDateMin() {
		return checkDateMin;
	}

	public void setCheckDateMin(String checkDateMin) {
		this.checkDateMin = checkDateMin;
	}

	public String getCheckDateMax() {
		return checkDateMax;
	}

	public void setCheckDateMax(String checkDateMax) {
		this.checkDateMax = checkDateMax;
	}

	public static Map<String, Object> CreateCondition(PeopleTimesheetVo timesheetVo) {

		Map<String, Object> condition = Maps.newHashMap();

		if (StringUtils.isNoneBlank(timesheetVo.getPeopleCode())) {
			condition.put("peopleCode", timesheetVo.getPeopleCode());
		}

		if (StringUtils.isNoneBlank(timesheetVo.getPeopleName())){
			condition.put("peopleName", timesheetVo.getPeopleName());
		}

		if (StringUtils.isNoneBlank(timesheetVo.getCheckDateMin())) {
			condition.put("checkDateMin", timesheetVo.getCheckDateMin());
		}
		if (StringUtils.isNoneBlank(timesheetVo.getCheckDateMax())) {
			condition.put("checkDateMax", timesheetVo.getCheckDateMax());
		}

		if (StringUtils.isNoneBlank(timesheetVo.getCheckDate())){
			condition.put("checkDate",timesheetVo.getCheckDate());
		}

		if (StringUtils.isNoneBlank(timesheetVo.getStatus())) {
			condition.put("status", timesheetVo.getStatus());
		}

		if (timesheetVo.getVacationPeriod() != null) {
			condition.put("vacationPeriod", timesheetVo.getVacationPeriod());
		}

		if (timesheetVo.getVacationPeriodMin() != null){
			condition.put("vacationPeriodMin",timesheetVo.getVacationPeriodMin());
		}

		if(timesheetVo.getVacationPeriodMax()!=null){
			condition.put("vacationPeriodMax",timesheetVo.getVacationPeriodMax());
		}

		return condition;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", peopleCode=").append(peopleCode);
		sb.append(", checkDate=").append(checkDate);
		sb.append(", status=").append(status);
		sb.append(", vacationPeriod=").append(vacationPeriod);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}