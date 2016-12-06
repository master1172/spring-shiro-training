package com.wangzhixuan.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Maps;
import com.wangzhixuan.model.PeopleRetire;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by administrator_cernet on 2016/11/27.
 */
public class PeopleRetireVo implements Serializable {

    private Long id;

    private String code;

    private String retireJobName;

    private Integer retireJobLevelId;

    private String retireJobLevelName;

    private Integer sex;

    private Integer nationalId;

    private String nationalName;

    private String educationName;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String birthday;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String birthdayMin;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String birthdayMax;

    private String politicalName;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String workDate;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String workDateMin;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String workDateMax;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String retireDate;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String retireDateMin;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String retireDateMax;

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

    public String getRetireJobLevelName() {
        return retireJobLevelName;
    }

    public void setRetireJobLevelName(String retireJobLevelName) {
        this.retireJobLevelName = retireJobLevelName;
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

    public String getNationalName() {
        return nationalName;
    }

    public void setNationalName(String nationalName) {
        this.nationalName = nationalName;
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

    public String getBirthdayMin() {
        return birthdayMin;
    }

    public void setBirthdayMin(String birthdayMin) {
        this.birthdayMin = birthdayMin;
    }

    public String getBirthdayMax() {
        return birthdayMax;
    }

    public void setBirthdayMax(String birthdayMax) {
        this.birthdayMax = birthdayMax;
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

    public String getWorkDateMin() {
        return workDateMin;
    }

    public void setWorkDateMin(String workDateMin) {
        this.workDateMin = workDateMin;
    }

    public String getWorkDateMax() {
        return workDateMax;
    }

    public void setWorkDateMax(String workDateMax) {
        this.workDateMax = workDateMax;
    }

    public String getRetireDate() {
        return retireDate;
    }

    public void setRetireDate(String retireDate) {
        this.retireDate = retireDate;
    }

    public String getRetireDateMin() {
        return retireDateMin;
    }

    public void setRetireDateMin(String retireDateMin) {
        this.retireDateMin = retireDateMin;
    }

    public String getRetireDateMax() {
        return retireDateMax;
    }

    public void setRetireDateMax(String retireDateMax) {
        this.retireDateMax = retireDateMax;
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

    public static Map<String,Object> CreateCondition(PeopleRetireVo peopleRetirevo){
        Map<String, Object> condition = Maps.newHashMap();

        if(StringUtils.isNoneBlank(peopleRetirevo.getCode())){
            condition.put("code", peopleRetirevo.getCode());
        }

        if(StringUtils.isNoneBlank(peopleRetirevo.getRetireJobName())){
            condition.put("retireJobName", peopleRetirevo.getRetireJobName());
        }

        if (peopleRetirevo.getRetireJobLevelId() != null){
            condition.put("retireJobLevelId", peopleRetirevo.getRetireJobLevelId());
        }

        if (peopleRetirevo.getSex() != null){
            condition.put("sex", peopleRetirevo.getSex());
        }

        if (peopleRetirevo.getNationalId() != null){
            condition.put("nationalId", peopleRetirevo.getNationalId());
        }

        if(StringUtils.isNoneBlank(peopleRetirevo.getEducationName())){
            condition.put("educationName", peopleRetirevo.getEducationName());
        }

        if(StringUtils.isNoneBlank(peopleRetirevo.getBirthdayMin())){
            condition.put("birthdayMin", peopleRetirevo.getBirthdayMin());
        }

        if(StringUtils.isNoneBlank(peopleRetirevo.getBirthdayMax())){
            condition.put("birthdayMax", peopleRetirevo.getBirthdayMax());
        }

        if(StringUtils.isNoneBlank(peopleRetirevo.getPoliticalName())){
            condition.put("politicalName", peopleRetirevo.getPoliticalName());
        }

        if(StringUtils.isNoneBlank(peopleRetirevo.getWorkDateMin())){
            condition.put("workDateMin", peopleRetirevo.getWorkDateMin());
        }

        if(StringUtils.isNoneBlank(peopleRetirevo.getWorkDateMax())){
            condition.put("workDateMax", peopleRetirevo.getWorkDateMax());
        }

        if(StringUtils.isNoneBlank(peopleRetirevo.getRetireDateMin())){
            condition.put("retireDateMin", peopleRetirevo.getRetireDateMin());
        }

        if(StringUtils.isNoneBlank(peopleRetirevo.getRetireDateMax())){
            condition.put("retireDateMax", peopleRetirevo.getRetireDateMax());
        }

        if(StringUtils.isNoneBlank(peopleRetirevo.getAddress())){
            condition.put("address", peopleRetirevo.getAddress());
        }

        if(StringUtils.isNoneBlank(peopleRetirevo.getMobile())){
            condition.put("mobile", peopleRetirevo.getMobile());
        }

        if(StringUtils.isNoneBlank(peopleRetirevo.getContact())){
            condition.put("contact", peopleRetirevo.getContact());
        }

        if(StringUtils.isNoneBlank(peopleRetirevo.getContactNumber())){
            condition.put("contactNumber", peopleRetirevo.getContactNumber());
        }

        if (peopleRetirevo.getStatus() != null){
            condition.put("status", peopleRetirevo.getStatus());
        }

        if(StringUtils.isNoneBlank(peopleRetirevo.getComment())){
            condition.put("comment", peopleRetirevo.getComment());
        }

        return condition;
    }

}

