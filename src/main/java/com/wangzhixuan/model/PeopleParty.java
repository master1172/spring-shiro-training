package com.wangzhixuan.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * Created by administrator_cernet on 2016/12/17.
 */
public class PeopleParty extends PeopleBase implements Serializable {

    private static final long serialVersionUID = -7668814450409110736L;

    private Long id;

    private String peopleCode;

    private String peopleType;

    private String peopleName;

    private Integer branchId;

    private Integer departmentId;

    private Integer sex;

    private Integer nationalId;

    @JsonFormat(pattern = "yy-mm-dd")
    private String birthday;

    private String nativeName;

    private Integer partyStatusId;

    @JsonFormat(pattern = "yy-mm-dd")
    private String partyDate;

    private Integer degreeId;

    @JsonFormat(pattern = "yy-mm-dd")
    private String workDate;

    private String jobName;

    private Integer jobLevelId;

    @JsonFormat(pattern = "yy-mm-dd")
    private String jobDate;

    private String formation;

    @JsonFormat(pattern = "yy-mm-dd")
    private String partyInDate;

    @JsonFormat(pattern = "yy-mm-dd")
    private String partyOutDate;

    private String photo;

    private String comment;

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

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getNationalId() {
        return nationalId;
    }

    public void setNationalId(Integer nationalId) {
        this.nationalId = nationalId;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public Integer getPartyStatusId() {
        return partyStatusId;
    }

    public void setPartyStatusId(Integer partyStatusId) {
        this.partyStatusId = partyStatusId;
    }

    public String getPartyDate() {
        return partyDate;
    }

    public void setPartyDate(String partyDate) {
        this.partyDate = partyDate;
    }

    public Integer getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(Integer degreeId) {
        this.degreeId = degreeId;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getJobLevelId() {
        return jobLevelId;
    }

    public void setJobLevelId(Integer jobLevelId) {
        this.jobLevelId = jobLevelId;
    }

    public String getJobDate() {
        return jobDate;
    }

    public void setJobDate(String jobDate) {
        this.jobDate = jobDate;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getPartyInDate() {
        return partyInDate;
    }

    public void setPartyInDate(String partyInDate) {
        this.partyInDate = partyInDate;
    }

    public String getPartyOutDate() {
        return partyOutDate;
    }

    public void setPartyOutDate(String partyOutDate) {
        this.partyOutDate = partyOutDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString(){
        return "PeopleParty{" +
                "id=" + id +
                "}";
    }
}
