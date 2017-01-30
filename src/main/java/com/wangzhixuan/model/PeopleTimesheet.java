package com.wangzhixuan.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PeopleTimesheet implements Serializable {
	private static final long serialVersionUID = 199L;

	private Integer id;

	private String peopleCode;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private String checkDate;

	private String status;

	private BigDecimal vacationPeriod;

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