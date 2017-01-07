package com.wangzhixuan.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Maps;
import com.wangzhixuan.model.PeopleTransfer;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by administrator_cernet on 2016/12/26.
 */
public class PeopleTransferVo implements Serializable {

    private Long id;

    private String peopleName;

    private String peopleCode;

    private String peopleType;

    private String fromSchool;

    private String toSchool;

    @JsonFormat(pattern = "yy-mm-dd")
    private String transferDate;

    @JsonFormat(pattern = "yy-mm-dd")
    private String transferDateMin;

    @JsonFormat(pattern = "yy-mm-dd")
    private String transferDateMax;

    private String refLetterNo;

    private String salaryEndDate;

    @JsonFormat(pattern = "yy-mm-dd")
    private String partyTransferDate;

    @JsonFormat(pattern = "yy-mm-dd")
    private String partyTransferDateMin;

    @JsonFormat(pattern = "yy-mm-dd")
    private String partyTransferDateMax;

    private String photo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPeopleType() {
        return peopleType;
    }

    public void setPeopleType(String peopleType) {
        this.peopleType = peopleType;
    }

    public String getFromSchool() {
        return fromSchool;
    }

    public void setFromSchool(String fromSchool) {
        this.fromSchool = fromSchool;
    }

    public String getToSchool() {
        return toSchool;
    }

    public void setToSchool(String toSchool) {
        this.toSchool = toSchool;
    }

    public String getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(String transferDate) {
        this.transferDate = transferDate;
    }

    public String getTransferDateMin() {
        return transferDateMin;
    }

    public void setTransferDateMin(String transferDateMin) {
        this.transferDateMin = transferDateMin;
    }

    public String getTransferDateMax() {
        return transferDateMax;
    }

    public void setTransferDateMax(String transferDateMax) {
        this.transferDateMax = transferDateMax;
    }

    public String getRefLetterNo() {
        return refLetterNo;
    }

    public void setRefLetterNo(String refLetterNo) {
        this.refLetterNo = refLetterNo;
    }

    public String getSalaryEndDate() {
        return salaryEndDate;
    }

    public void setSalaryEndDate(String salaryEndDate) {
        this.salaryEndDate = salaryEndDate;
    }

    public String getPartyTransferDate() {
        return partyTransferDate;
    }

    public void setPartyTransferDate(String partyTransferDate) {
        this.partyTransferDate = partyTransferDate;
    }

    public String getPartyTransferDateMin() {
        return partyTransferDateMin;
    }

    public void setPartyTransferDateMin(String partyTransferDateMin) {
        this.partyTransferDateMin = partyTransferDateMin;
    }

    public String getPartyTransferDateMax() {
        return partyTransferDateMax;
    }

    public void setPartyTransferDateMax(String partyTransferDateMax) {
        this.partyTransferDateMax = partyTransferDateMax;
    }

    public static Map<String,Object> CreateCondition(PeopleTransferVo peopleTransferVo){
        Map<String, Object> condition = Maps.newHashMap();

        if(StringUtils.isNoneBlank(peopleTransferVo.getPeopleName())){
            condition.put("peopleName",peopleTransferVo.getPeopleName());
        }

        if(StringUtils.isNoneBlank(peopleTransferVo.getFromSchool())){
            condition.put("fromSchool",peopleTransferVo.getFromSchool());
        }

        if(StringUtils.isNoneBlank(peopleTransferVo.getToSchool())){
            condition.put("toSchool",peopleTransferVo.getToSchool());
        }

        if(StringUtils.isNoneBlank(peopleTransferVo.getTransferDateMin())){
            condition.put("transferDateMin",peopleTransferVo.getTransferDateMin());
        }

        if(StringUtils.isNoneBlank(peopleTransferVo.getTransferDateMax())){
            condition.put("transferDateMax",peopleTransferVo.getTransferDateMax());
        }

        if(StringUtils.isNoneBlank(peopleTransferVo.getRefLetterNo())){
            condition.put("refLetterNo",peopleTransferVo.getRefLetterNo());
        }

        if(StringUtils.isNoneBlank(peopleTransferVo.getSalaryEndDate())){
            condition.put("salaryEndDate",peopleTransferVo.getSalaryEndDate());
        }

        if(StringUtils.isNoneBlank(peopleTransferVo.getPartyTransferDateMin())){
            condition.put("partyTransferDateMin",peopleTransferVo.getPartyTransferDateMin());
        }

        if(StringUtils.isNoneBlank(peopleTransferVo.getPartyTransferDateMax())){
            condition.put("partyTransferDateMax",peopleTransferVo.getPartyTransferDateMax());
        }

        return condition;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
