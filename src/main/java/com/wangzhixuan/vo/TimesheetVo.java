package com.wangzhixuan.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

public class TimesheetVo implements Serializable {
	private Integer id;

	private String peopleCode;

	private String peopleType;

	private String checkDate;

	private String checkDateMin;
	private String checkDateMax;

	private String status;

	private BigDecimal vacationPeriod;

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

	public String getPeopleType() {
		return peopleType;
	}

	public void setPeopleType(String peopleType) {
		this.peopleType = peopleType == null ? null : peopleType.trim();
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

	public static Map<String, Object> CreateCondition(TimesheetVo timesheetVo) {
		Map<String, Object> condition = Maps.newHashMap();

		if (StringUtils.isNoneBlank(timesheetVo.getPeopleCode())) {
			condition.put("peopleCode", timesheetVo.getPeopleCode());
		}
		if (StringUtils.isNoneBlank(timesheetVo.getPeopleType())) {
			condition.put("peopleType", timesheetVo.getPeopleType());
		}
		if (StringUtils.isNoneBlank(timesheetVo.getCheckDateMin())) {
			condition.put("checkDateMin", timesheetVo.getCheckDateMin());
		}
		if (StringUtils.isNoneBlank(timesheetVo.getCheckDateMax())) {
			condition.put("checkDateMax", timesheetVo.getCheckDateMax());
		}
		if (StringUtils.isNoneBlank(timesheetVo.getStatus())) {
			condition.put("status", timesheetVo.getStatus());
		}
		if (timesheetVo.getVacationPeriod() != null) {
			condition.put("vacationPeriod", timesheetVo.getVacationPeriod());
		}
		return condition;
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", peopleCode=").append(peopleCode);
		sb.append(", peopleType=").append(peopleType);
		sb.append(", checkDate=").append(checkDate);
		sb.append(", status=").append(status);
		sb.append(", vacationPeriod=").append(vacationPeriod);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}