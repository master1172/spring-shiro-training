package com.wangzhixuan.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * Created by administrator_cernet on 2016/12/26.
 */
public class PeopleTransfer extends PeopleBase implements Serializable {

    private static final long serialVersionUID = -5924347408576423461L;

    private Long id;

    private String peopleCode;

    private String peopleType;

    private String fromSchool;

    private String toSchool;

    @JsonFormat(pattern = "yy-mm-dd")
    private String transferDate;

    private String refLetterNo;

    private String salaryEndDate;

    @JsonFormat(pattern = "yy-mm-dd")
    private String partyTransferDate;

    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

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

    @Override
    public String toString(){
        return "PeopleTransfer{" +
                "id=" + id +
                "}";
    }
}
