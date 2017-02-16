package com.wangzhixuan.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Maps;
import com.wangzhixuan.model.PeopleDispatch;
import com.wangzhixuan.model.PeopleTemp;
import com.wangzhixuan.utils.ConstUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by administrator_cernet on 2016/11/27.
 */
public class PeopleDispatchVo implements Serializable {

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

    public static Map<String,Object> CreateCondition(PeopleDispatchVo peopleDispatchvo){
        Map<String, Object> condition = Maps.newHashMap();

        if(StringUtils.isNoneBlank(peopleDispatchvo.getCode())){
            condition.put("code", peopleDispatchvo.getCode());
        }

        if(StringUtils.isNoneBlank(peopleDispatchvo.getName())){
            condition.put("name", peopleDispatchvo.getName());
        }

        if (peopleDispatchvo.getSex() != null){
            condition.put("sex", peopleDispatchvo.getSex());
        }

        if (peopleDispatchvo.getNationalId() != null){
            condition.put("nationalId", peopleDispatchvo.getNationalId());
        }

        if(StringUtils.isNoneBlank(peopleDispatchvo.getProvince())){
            condition.put("province", peopleDispatchvo.getProvince());
        }

        if(StringUtils.isNoneBlank(peopleDispatchvo.getCity())){
            condition.put("city", peopleDispatchvo.getCity());
        }

        if(StringUtils.isNoneBlank(peopleDispatchvo.getBirthdayMin())){
            condition.put("birthdayMin", peopleDispatchvo.getBirthdayMin());
        }

        if(StringUtils.isNoneBlank(peopleDispatchvo.getBirthdayMax())){
            condition.put("birthdayMax", peopleDispatchvo.getBirthdayMax());
        }

        if(StringUtils.isNoneBlank(peopleDispatchvo.getEducationName())){
            condition.put("educationName", peopleDispatchvo.getEducationName());
        }

        if(StringUtils.isNoneBlank(peopleDispatchvo.getPoliticalName())){
            condition.put("politicalName", peopleDispatchvo.getPoliticalName());
        }

        if(StringUtils.isNoneBlank(peopleDispatchvo.getSpeciality())){
            condition.put("speciality", peopleDispatchvo.getSpeciality());
        }

        if(StringUtils.isNoneBlank(peopleDispatchvo.getHeight())){
            condition.put("height", peopleDispatchvo.getHeight());
        }

        if (peopleDispatchvo.getMarriageId() != null){
            condition.put("marriageId", peopleDispatchvo.getMarriageId());
        }

        if (peopleDispatchvo.getHukou() != null){
            condition.put("hukou", peopleDispatchvo.getHukou());
        }

        if(StringUtils.isNoneBlank(peopleDispatchvo.getSchoolDateMix())){
            condition.put("schoolDateMin", peopleDispatchvo.getSchoolDateMix());
        }

        if(StringUtils.isNoneBlank(peopleDispatchvo.getSchoolDateMax())){
            condition.put("schoolDateMax", peopleDispatchvo.getSchoolDateMax());
        }

        if(StringUtils.isNoneBlank(peopleDispatchvo.getMobile())){
            condition.put("mobile", peopleDispatchvo.getMobile());
        }

        if(StringUtils.isNoneBlank(peopleDispatchvo.getAddress())){
            condition.put("address", peopleDispatchvo.getAddress());
        }

        if(StringUtils.isNoneBlank(peopleDispatchvo.getExtraDepartmentName())){
            condition.put("extraDepartmentName", peopleDispatchvo.getExtraDepartmentName());
        }

        if(StringUtils.isNoneBlank(peopleDispatchvo.getJobName())){
            condition.put("jobName", peopleDispatchvo.getJobName());
        }

        if(StringUtils.isNoneBlank(peopleDispatchvo.getComment())){
            condition.put("comment", peopleDispatchvo.getComment());
        }

        condition.put("status", ConstUtil.PEOPLE_DISPATCH);

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

