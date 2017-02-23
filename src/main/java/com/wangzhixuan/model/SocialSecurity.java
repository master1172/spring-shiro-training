package com.wangzhixuan.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by sterm on 2017/2/23.
 */
public class SocialSecurity implements Serializable{

    private Integer id;
    private Integer peopleId;

    private BigDecimal lifeInsuranceBase;
    private BigDecimal lifeInsuranceSchool;
    private BigDecimal lifeInsurancePeople;

    private BigDecimal jobInsuranceBase;
    private BigDecimal jobInsuranceSchool;
    private BigDecimal jobInsurancePeople;

    private BigDecimal woundInsuranceBase;
    private BigDecimal woundInsuranceSchool;

    private BigDecimal birthInsuranceBase;
    private BigDecimal birthInsuranceSchool;

    private BigDecimal healthInsuranceBase;
    private BigDecimal healthInsuranceSchool;
    private BigDecimal healthInsurancePeople;

    private BigDecimal annuityBase;
    private BigDecimal annuitySchool;
    private BigDecimal annuityPeople;

    private String payDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(Integer peopleId) {
        this.peopleId = peopleId;
    }

    public BigDecimal getLifeInsuranceBase() {
        return lifeInsuranceBase;
    }

    public void setLifeInsuranceBase(BigDecimal lifeInsuranceBase) {
        this.lifeInsuranceBase = lifeInsuranceBase;
    }

    public BigDecimal getLifeInsuranceSchool() {
        return lifeInsuranceSchool;
    }

    public void setLifeInsuranceSchool(BigDecimal lifeInsuranceSchool) {
        this.lifeInsuranceSchool = lifeInsuranceSchool;
    }

    public BigDecimal getLifeInsurancePeople() {
        return lifeInsurancePeople;
    }

    public void setLifeInsurancePeople(BigDecimal lifeInsurancePeople) {
        this.lifeInsurancePeople = lifeInsurancePeople;
    }

    public BigDecimal getJobInsuranceBase() {
        return jobInsuranceBase;
    }

    public void setJobInsuranceBase(BigDecimal jobInsuranceBase) {
        this.jobInsuranceBase = jobInsuranceBase;
    }

    public BigDecimal getJobInsuranceSchool() {
        return jobInsuranceSchool;
    }

    public void setJobInsuranceSchool(BigDecimal jobInsuranceSchool) {
        this.jobInsuranceSchool = jobInsuranceSchool;
    }

    public BigDecimal getJobInsurancePeople() {
        return jobInsurancePeople;
    }

    public void setJobInsurancePeople(BigDecimal jobInsurancePeople) {
        this.jobInsurancePeople = jobInsurancePeople;
    }

    public BigDecimal getWoundInsuranceBase() {
        return woundInsuranceBase;
    }

    public void setWoundInsuranceBase(BigDecimal woundInsuranceBase) {
        this.woundInsuranceBase = woundInsuranceBase;
    }

    public BigDecimal getWoundInsuranceSchool() {
        return woundInsuranceSchool;
    }

    public void setWoundInsuranceSchool(BigDecimal woundInsuranceSchool) {
        this.woundInsuranceSchool = woundInsuranceSchool;
    }

    public BigDecimal getBirthInsuranceBase() {
        return birthInsuranceBase;
    }

    public void setBirthInsuranceBase(BigDecimal birthInsuranceBase) {
        this.birthInsuranceBase = birthInsuranceBase;
    }

    public BigDecimal getBirthInsuranceSchool() {
        return birthInsuranceSchool;
    }

    public void setBirthInsuranceSchool(BigDecimal birthInsuranceSchool) {
        this.birthInsuranceSchool = birthInsuranceSchool;
    }

    public BigDecimal getHealthInsuranceBase() {
        return healthInsuranceBase;
    }

    public void setHealthInsuranceBase(BigDecimal healthInsuranceBase) {
        this.healthInsuranceBase = healthInsuranceBase;
    }

    public BigDecimal getHealthInsuranceSchool() {
        return healthInsuranceSchool;
    }

    public void setHealthInsuranceSchool(BigDecimal healthInsuranceSchool) {
        this.healthInsuranceSchool = healthInsuranceSchool;
    }

    public BigDecimal getHealthInsurancePeople() {
        return healthInsurancePeople;
    }

    public void setHealthInsurancePeople(BigDecimal healthInsurancePeople) {
        this.healthInsurancePeople = healthInsurancePeople;
    }

    public BigDecimal getAnnuityBase() {
        return annuityBase;
    }

    public void setAnnuityBase(BigDecimal annuityBase) {
        this.annuityBase = annuityBase;
    }

    public BigDecimal getAnnuitySchool() {
        return annuitySchool;
    }

    public void setAnnuitySchool(BigDecimal annuitySchool) {
        this.annuitySchool = annuitySchool;
    }

    public BigDecimal getAnnuityPeople() {
        return annuityPeople;
    }

    public void setAnnuityPeople(BigDecimal annuityPeople) {
        this.annuityPeople = annuityPeople;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }
}
