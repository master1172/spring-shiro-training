package com.wangzhixuan.model;

import java.io.Serializable;

public class ExamMonthly implements Serializable {
    private Integer id;

    private String peopleCode;

    private String name;

    private String peopleType;

    private Integer year;

    private Integer month;

    private String examResult;

    private String examOperation;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPeopleType() {
        return peopleType;
    }

    public void setPeopleType(String peopleType) {
        this.peopleType = peopleType == null ? null : peopleType.trim();
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getExamResult() {
        return examResult;
    }

    public void setExamResult(String examResult) {
        this.examResult = examResult == null ? null : examResult.trim();
    }

    public String getExamOperation() {
        return examOperation;
    }

    public void setExamOperation(String examOperation) {
        this.examOperation = examOperation == null ? null : examOperation.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", peopleCode=").append(peopleCode);
        sb.append(", name=").append(name);
        sb.append(", peopleType=").append(peopleType);
        sb.append(", year=").append(year);
        sb.append(", month=").append(month);
        sb.append(", examResult=").append(examResult);
        sb.append(", examOperation=").append(examOperation);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}