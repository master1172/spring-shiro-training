package com.wangzhixuan.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * Created by administrator_cernet on 2016/12/5.
 */
public class PeopleRetire extends PeopleBase implements Serializable {

    private static final long serialVersionUID = -8187664532609757193L;

    private Long id;

    private String code;

    private String name;

    private String retireJobName;

    private Integer retireJobLevelId;

    private Integer sex;

    private Integer nationalId;

    private String educationName;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String birthday;

    private String politicalName;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String workDate;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String retireDate;

    private String address;

    private String mobile;

    private String contact;

    private String contactNumber;

    private Integer status;

    private String comment;

    private String photo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName(){return name;}

    public void setName(String name) {this.name = name; }

    public String getRetireJobName() {
        return retireJobName;
    }

    public void setRetireJobName(String retireJobName) {
        this.retireJobName = retireJobName;
    }

    public Integer getRetireJobLevelId() {
        return retireJobLevelId;
    }

    public void setRetireJobLevelId(Integer retireJobLevelId) {
        this.retireJobLevelId = retireJobLevelId;
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

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPoliticalName() {
        return politicalName;
    }

    public void setPoliticalName(String politicalName) {
        this.politicalName = politicalName;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getRetireDate() {
        return retireDate;
    }

    public void setRetireDate(String retireDate) {
        this.retireDate = retireDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString(){
        return "PeopleRetire{" +
                "id=" + id +
                "}";
    }
}
