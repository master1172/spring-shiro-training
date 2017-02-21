package com.wangzhixuan.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by sterm on 2017/2/21.
 */
public class SalaryChangeRecordVo implements Serializable{

    private Integer id;

    private String peopleCode;

    private String peopleName;

    private Integer prevJobId;

    private BigDecimal prevJobSalary;

    private String prevJobLevel;

    private Integer newJobId;

    private BigDecimal newJobSalary;

    private String newJobLevel;

    private Integer prevRankId;

    private BigDecimal prevRankSalary;

    private String prevRankLevel;

    private Integer newRankId;

    private BigDecimal newRankSalary;

    private String newRankLevel;

    private BigDecimal prevReserveSalary;

    private BigDecimal newReserveSalary;

    private String changeReason;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String changeDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String effectDate;

    private String peopleOpinion;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String peopleCheckDate;

    private String comment;

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

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public Integer getPrevJobId() {
        return prevJobId;
    }

    public void setPrevJobId(Integer prevJobId) {
        this.prevJobId = prevJobId;
    }

    public BigDecimal getPrevJobSalary() {
        return prevJobSalary;
    }

    public void setPrevJobSalary(BigDecimal prevJobSalary) {
        this.prevJobSalary = prevJobSalary;
    }

    public String getPrevJobLevel() {
        return prevJobLevel;
    }

    public void setPrevJobLevel(String prevJobLevel) {
        this.prevJobLevel = prevJobLevel;
    }

    public Integer getNewJobId() {
        return newJobId;
    }

    public void setNewJobId(Integer newJobId) {
        this.newJobId = newJobId;
    }

    public BigDecimal getNewJobSalary() {
        return newJobSalary;
    }

    public void setNewJobSalary(BigDecimal newJobSalary) {
        this.newJobSalary = newJobSalary;
    }

    public String getNewJobLevel() {
        return newJobLevel;
    }

    public void setNewJobLevel(String newJobLevel) {
        this.newJobLevel = newJobLevel;
    }

    public Integer getPrevRankId() {
        return prevRankId;
    }

    public void setPrevRankId(Integer prevRankId) {
        this.prevRankId = prevRankId;
    }

    public BigDecimal getPrevRankSalary() {
        return prevRankSalary;
    }

    public void setPrevRankSalary(BigDecimal prevRankSalary) {
        this.prevRankSalary = prevRankSalary;
    }

    public String getPrevRankLevel() {
        return prevRankLevel;
    }

    public void setPrevRankLevel(String prevRankLevel) {
        this.prevRankLevel = prevRankLevel;
    }

    public Integer getNewRankId() {
        return newRankId;
    }

    public void setNewRankId(Integer newRankId) {
        this.newRankId = newRankId;
    }

    public BigDecimal getNewRankSalary() {
        return newRankSalary;
    }

    public void setNewRankSalary(BigDecimal newRankSalary) {
        this.newRankSalary = newRankSalary;
    }

    public String getNewRankLevel() {
        return newRankLevel;
    }

    public void setNewRankLevel(String newRankLevel) {
        this.newRankLevel = newRankLevel;
    }

    public BigDecimal getPrevReserveSalary() {
        return prevReserveSalary;
    }

    public void setPrevReserveSalary(BigDecimal prevReserveSalary) {
        this.prevReserveSalary = prevReserveSalary;
    }

    public BigDecimal getNewReserveSalary() {
        return newReserveSalary;
    }

    public void setNewReserveSalary(BigDecimal newReserveSalary) {
        this.newReserveSalary = newReserveSalary;
    }

    public String getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(String effectDate) {
        this.effectDate = effectDate;
    }

    public String getPeopleOpinion() {
        return peopleOpinion;
    }

    public void setPeopleOpinion(String peopleOpinion) {
        this.peopleOpinion = peopleOpinion;
    }

    public String getPeopleCheckDate() {
        return peopleCheckDate;
    }

    public void setPeopleCheckDate(String peopleCheckDate) {
        this.peopleCheckDate = peopleCheckDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
