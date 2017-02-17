package com.wangzhixuan.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Maps;
import com.wangzhixuan.model.PeopleTemp;
import com.wangzhixuan.utils.ConstUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by administrator_cernet on 2016/11/27.
 */
public class PeopleTempVo implements Serializable {

    private Integer id;

    private String code;

    private String name;

    private Integer sex;

    private Integer nationalId;

    private String nationalName;

    private String province;

    private String city;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String birthday;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String birthdayMin;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String birthdayMax;

    private String educationName;

    private String politicalName;

    private String speciality;

    private String height;

    private Integer marriageId;

    private String marriageName;

    private Integer hukou;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String schoolDate;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String schoolDateMix;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String schoolDateMax;

    private String mobile;

    private String address;

    private String extraDepartmentName;

    private String jobName;

    private String comment;

    private String photo;

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

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Integer getMarriageId() {
        return marriageId;
    }

    public void setMarriageId(Integer marriageId) {
        this.marriageId = marriageId;
    }

    public Integer getHukou() {
        return hukou;
    }

    public void setHukou(Integer hukou) {
        this.hukou = hukou;
    }

    public String getSchoolDate() {
        return schoolDate;
    }

    public void setSchoolDate(String schoolDate) {
        this.schoolDate = schoolDate;
    }

    public String getSchoolDateMix() {
        return schoolDateMix;
    }

    public void setSchoolDateMix(String schoolDateMix) {
        this.schoolDateMix = schoolDateMix;
    }

    public String getSchoolDateMax() {
        return schoolDateMax;
    }

    public void setSchoolDateMax(String schoolDateMax) {
        this.schoolDateMax = schoolDateMax;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getExtraDepartmentName() {
        return extraDepartmentName;
    }

    public void setExtraDepartmentName(String departmentName) {
        this.extraDepartmentName = departmentName;
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

    public static Map<String,Object> CreateCondition(PeopleTempVo peopleTempvo){
        Map<String, Object> condition = Maps.newHashMap();

        if(StringUtils.isNoneBlank(peopleTempvo.getCode())){
            condition.put("code", peopleTempvo.getCode());
        }

        if(StringUtils.isNoneBlank(peopleTempvo.getName())){
            condition.put("name", peopleTempvo.getName());
        }

        if (peopleTempvo.getSex() != null){
            condition.put("sex", peopleTempvo.getSex());
        }

        if (peopleTempvo.getNationalId() != null){
            condition.put("nationalId", peopleTempvo.getNationalId());
        }

        if(StringUtils.isNoneBlank(peopleTempvo.getProvince())){
            condition.put("province", peopleTempvo.getProvince());
        }

        if(StringUtils.isNoneBlank(peopleTempvo.getCity())){
            condition.put("city", peopleTempvo.getCity());
        }

        if(StringUtils.isNoneBlank(peopleTempvo.getBirthdayMin())){
            condition.put("birthdayMin", peopleTempvo.getBirthdayMin());
        }

        if(StringUtils.isNoneBlank(peopleTempvo.getBirthdayMax())){
            condition.put("birthdayMax", peopleTempvo.getBirthdayMax());
        }

        if(StringUtils.isNoneBlank(peopleTempvo.getEducationName())){
            condition.put("educationName", peopleTempvo.getEducationName());
        }

        if(StringUtils.isNoneBlank(peopleTempvo.getPoliticalName())){
            condition.put("politicalName", peopleTempvo.getPoliticalName());
        }

        if(StringUtils.isNoneBlank(peopleTempvo.getSpeciality())){
            condition.put("speciality", peopleTempvo.getSpeciality());
        }

        if(StringUtils.isNoneBlank(peopleTempvo.getHeight())){
            condition.put("height", peopleTempvo.getHeight());
        }

        if (peopleTempvo.getMarriageId() != null){
            condition.put("marriageId", peopleTempvo.getMarriageId());
        }

        if (peopleTempvo.getHukou() != null){
            condition.put("hukou", peopleTempvo.getHukou());
        }

        if(StringUtils.isNoneBlank(peopleTempvo.getSchoolDateMix())){
            condition.put("schoolDateMin", peopleTempvo.getSchoolDateMix());
        }

        if(StringUtils.isNoneBlank(peopleTempvo.getSchoolDateMax())){
            condition.put("schoolDateMax", peopleTempvo.getSchoolDateMax());
        }

        if(StringUtils.isNoneBlank(peopleTempvo.getMobile())){
            condition.put("mobile", peopleTempvo.getMobile());
        }

        if(StringUtils.isNoneBlank(peopleTempvo.getAddress())){
            condition.put("address", peopleTempvo.getAddress());
        }

        if(StringUtils.isNoneBlank(peopleTempvo.getExtraDepartmentName())){
            condition.put("extraDepartmentName", peopleTempvo.getExtraDepartmentName());
        }

        if(StringUtils.isNoneBlank(peopleTempvo.getJobName())){
            condition.put("jobName", peopleTempvo.getJobName());
        }

        if(StringUtils.isNoneBlank(peopleTempvo.getComment())){
            condition.put("comment", peopleTempvo.getComment());
        }

        condition.put("status", ConstUtil.PEOPLE_TEMP);

        return condition;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNationalName() {
        return nationalName;
    }

    public void setNationalName(String nationalName) {
        this.nationalName = nationalName;
    }

    public String getMarriageName() {
        return marriageName;
    }

    public void setMarriageName(String marriageName) {
        this.marriageName = marriageName;
    }
}

