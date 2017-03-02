package com.wangzhixuan.model;

import java.io.Serializable;
import java.util.Date;

public class Abroad implements Serializable {
    private static final long serialVersionUID = 1;
    private Integer id;

    private String code;

    private String name;

    private Integer departmentId;

    private Integer jobId;

    private String abroadDate;

    private String returnDate;

    private Integer stayPeriod;

    private String vacation;

    private String country;

    private String passportType;

    private String passportStatus;

    private String photoId;

    private String passportNumber;

    private String reason;

    private String visitPeople;

    private String relationship;

    private String city;

    private String office;

    private String personalIssue;

    private String funding;

    private String fundingSource;

    private String issueDate;

    private String pickPassportDate;

    private String returnPassportDate;

    private String comment;

    private String recordType;

    private String recordStatus;

    private String recordIdType;

    private String recordIdNumber;

    private String recordIdExpire;

    public String getRecordIdExpire() {
        return recordIdExpire;
    }

    public void setRecordIdExpire(String recordIdExpire) {
        this.recordIdExpire = recordIdExpire;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Integer getStayPeriod() {
        return stayPeriod;
    }

    public void setStayPeriod(Integer stayPeriod) {
        this.stayPeriod = stayPeriod;
    }

    public String getVacation() {
        return vacation;
    }

    public void setVacation(String vacation) {
        this.vacation = vacation;
    }

    public String getPassportType() {
        return passportType;
    }

    public void setPassportType(String passportType) {
        this.passportType = passportType;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getVisitPeople() {
        return visitPeople;
    }

    public void setVisitPeople(String visitPeople) {
        this.visitPeople = visitPeople;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getPersonalIssue() {
        return personalIssue;
    }

    public void setPersonalIssue(String personalIssue) {
        this.personalIssue = personalIssue;
    }

    public String getFundingSource() {
        return fundingSource;
    }

    public void setFundingSource(String fundingSource) {
        this.fundingSource = fundingSource;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getRecordIdType() {
        return recordIdType;
    }

    public void setRecordIdType(String recordIdType) {
        this.recordIdType = recordIdType;
    }

    public String getRecordIdNumber() {
        return recordIdNumber;
    }

    public void setRecordIdNumber(String recordIdNumber) {
        this.recordIdNumber = recordIdNumber;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getJobId() {return jobId;}

    public void setJobId(Integer jobId) {this.jobId = jobId;}

    public String getAbroadDate() {return abroadDate;}

    public void setAbroadDate(String abroadDate) {
        this.abroadDate = abroadDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getPassportStatus() {
        return passportStatus;
    }

    public void setPassportStatus(String passportStatus) {
        this.passportStatus = passportStatus == null ? null : passportStatus.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getFunding() {
        return funding;
    }

    public void setFunding(String funding) {
        this.funding = funding == null ? null : funding.trim();
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getPickPassportDate() {
        return pickPassportDate;
    }

    public void setPickPassportDate(String pickPassportDate) {
        this.pickPassportDate = pickPassportDate;
    }

    public String getReturnPassportDate() {
        return returnPassportDate;
    }

    public void setReturnPassportDate(String returnPassportDate) {
        this.returnPassportDate = returnPassportDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", departmentId=").append(departmentId);
        sb.append(", abroadDate=").append(abroadDate);
        sb.append(", returnDate=").append(returnDate);
        sb.append(", stayPeriod=").append(stayPeriod);
        sb.append(", vacation=").append(vacation);
        sb.append(", country=").append(country);
        sb.append(", passportType=").append(passportType);
        sb.append(", passportStatus=").append(passportStatus);
        sb.append(", photoId=").append(photoId);
        sb.append(", passportNumber=").append(passportNumber);
        sb.append(", reason=").append(reason);
        sb.append(", visitPeople=").append(visitPeople);
        sb.append(", relationShip=").append(relationship);
        sb.append(", city=").append(city);
        sb.append(", office=").append(office);
        sb.append(", personalIssue").append(personalIssue);
        sb.append(", funding=").append(funding);
        sb.append(", fundingSource=").append(fundingSource);
        sb.append(", issueDate=").append(issueDate);
        sb.append(", pickPassportDate=").append(pickPassportDate);
        sb.append(", returnPassportDate=").append(returnPassportDate);
        sb.append(", comment=").append(comment);
        sb.append(", recordType=").append(recordType);
        sb.append(", recordStatus").append(recordStatus);
        sb.append(", recordIdType").append(recordIdType);
        sb.append(", recordIdNumber").append(recordIdNumber);
        sb.append(", recordIdExpire").append(recordIdExpire);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}