package com.wangzhixuan.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TimesheetVo implements Serializable {
    private Integer id;

    private String peopleCode;

    private String peopleType;

    private Date checkDate;

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

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
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
        sb.append(", peopleType=").append(peopleType);
        sb.append(", checkDate=").append(checkDate);
        sb.append(", status=").append(status);
        sb.append(", vacationPeriod=").append(vacationPeriod);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}