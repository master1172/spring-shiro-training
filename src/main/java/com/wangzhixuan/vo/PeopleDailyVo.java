package com.wangzhixuan.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by sterm on 2016/11/22.
 */
public class PeopleDailyVo implements Serializable{

    private Long id;

    private String code;

    private String name;

    private Integer sex;

    private Integer nationalId;

    private String nationalName;

    private String province;

    private String city;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String birthday;

    private String birthdayMin;

    private String birthdayMax;

    private String educationName;

    private String politicalName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String schoolDate;

    private String schoolDateMin;

    private String schoolDateMax;

    private String mobile;

    private String departmentName;

    private String jobName;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public String getPoliticalName() {
        return politicalName;
    }

    public void setPoliticalName(String politicalName) {
        this.politicalName = politicalName;
    }

    public String getSchoolDate() {
        return schoolDate;
    }

    public void setSchoolDate(String schoolDate) {
        this.schoolDate = schoolDate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
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

    public String getSchoolDateMin() {
        return schoolDateMin;
    }

    public void setSchoolDateMin(String schoolDateMin) {
        this.schoolDateMin = schoolDateMin;
    }

    public String getSchoolDateMax() {
        return schoolDateMax;
    }

    public void setSchoolDateMax(String schoolDateMax) {
        this.schoolDateMax = schoolDateMax;
    }

    public String toString(){
        return "id = " + id;
    }

    public static Map<String,Object> CreateCondition(PeopleDailyVo peopleDailyVo) {

        Map<String, Object> condition = Maps.newHashMap();

        if(StringUtils.isNoneBlank(peopleDailyVo.getName())){
            condition.put("name", peopleDailyVo.getName());
        }

        if(peopleDailyVo.getSex() != null){
            condition.put("sex",peopleDailyVo.getSex());
        }

        if(peopleDailyVo.getNationalId() != null){
            condition.put("nationalId", peopleDailyVo.getNationalId());
        }

        if(StringUtils.isNoneBlank(peopleDailyVo.getBirthdayMin())){
            condition.put("birthdayMin", peopleDailyVo.getBirthdayMin());
        }

        if(StringUtils.isNoneBlank(peopleDailyVo.getBirthdayMax())){
            condition.put("birthdayMax", peopleDailyVo.getBirthdayMax());
        }

        if(StringUtils.isNoneBlank(peopleDailyVo.getEducationName())){
            condition.put("educationName", peopleDailyVo.getEducationName());
        }

        if(StringUtils.isNoneBlank(peopleDailyVo.getPoliticalName())){
            condition.put("politicalName", peopleDailyVo.getPoliticalName());
        }

        if(StringUtils.isNoneBlank(peopleDailyVo.getSchoolDateMin())){
            condition.put("schoolDateMin",peopleDailyVo.getSchoolDateMin());
        }

        if(StringUtils.isNoneBlank(peopleDailyVo.getSchoolDateMax())){
            condition.put("schoolDateMax", peopleDailyVo.getSchoolDateMax());
        }

        if(StringUtils.isNoneBlank(peopleDailyVo.getDepartmentName())){
            condition.put("departmentName", peopleDailyVo.getDepartmentName());
        }

        if(StringUtils.isNoneBlank(peopleDailyVo.getJobName())){
            condition.put("jobName", peopleDailyVo.getJobName());
        }

        return condition;
    }
}
