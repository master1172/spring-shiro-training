package com.wangzhixuan.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by sterm on 2017/2/21.
 */
public class SalaryChangeRecord implements Serializable{

    private Integer id;

    private String peopleCode;

    private Integer prevJobId;

    private BigDecimal prevJobSalary;

    private Integer newJobId;

    private BigDecimal newJobSalary;

    private Integer prevRankId;

    private BigDecimal prevRankSalary;

    private Integer newRankId;

    private BigDecimal newRankSalary;

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

    public String getPeopleCode() {
        return peopleCode;
    }

    public void setPeopleCode(String peopleCode) {
        this.peopleCode = peopleCode == null ? null : peopleCode.trim();
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
        this.changeReason = changeReason == null ? null : changeReason.trim();
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
        this.peopleOpinion = peopleOpinion == null ? null : peopleOpinion.trim();
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
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
