package com.wangzhixuan.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by sterm on 2017/2/15.
 */
public class RecruitVo implements Serializable{

    private Integer id;

    private String name;

    private Integer sex;

    private Integer age;

    private String major;

    private String applyJob;

    private String origin;

    private Integer nationalId;

    private String nationalName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String birthday;

    private String politicalName;

    private String health;

    private String graduateSchool;

    private String degree;

    private Integer degreeOnTime;

    private String schoolAddress;

    private String graduateStatus;

    private String foreignLanguageLevel;

    private Integer marriageId;

    private String marriageStatus;

    private String cellphone;

    private String photoId;

    private String email;

    private String address;

    private String zipcode;

    private String studyExperience;

    private String specialityAndAbility;

    private String socialExperience;

    private String award;

    private String photo;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getApplyJob() {
        return applyJob;
    }

    public void setApplyJob(String applyJob) {
        this.applyJob = applyJob;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getMarriageStatus() {return marriageStatus;}

    public void setMarriageStatus(String marriageStatus) {this.marriageStatus = marriageStatus;}

    public Integer getNationalId() {
        return nationalId;
    }

    public void setNationalId(Integer nationalId) {
        this.nationalId = nationalId;
    }

    public String getNationalName() {return nationalName;}

    public void setNationalName(String nationalName) {this.nationalName = nationalName;}

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

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Integer getDegreeOnTime() {return degreeOnTime;}

    public void setDegreeOnTime(Integer degreeOnTime) {
        this.degreeOnTime = degreeOnTime;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    public String getGraduateStatus() {
        return graduateStatus;
    }

    public void setGraduateStatus(String graduateStatus) {
        this.graduateStatus = graduateStatus;
    }

    public String getForeignLanguageLevel() {
        return foreignLanguageLevel;
    }

    public void setForeignLanguageLevel(String foreignLanguageLevel) {
        this.foreignLanguageLevel = foreignLanguageLevel;
    }

    public Integer getMarriageId() {
        return marriageId;
    }

    public void setMarriageId(Integer marriageId) {
        this.marriageId = marriageId;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getStudyExperience() {
        return studyExperience;
    }

    public void setStudyExperience(String studyExperience) {
        this.studyExperience = studyExperience;
    }

    public String getSpecialityAndAbility() {
        return specialityAndAbility;
    }

    public void setSpecialityAndAbility(String specialityAndAbility) {
        this.specialityAndAbility = specialityAndAbility;
    }

    public String getSocialExperience() {
        return socialExperience;
    }

    public void setSocialExperience(String socialExperience) {
        this.socialExperience = socialExperience;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public static Map<String,Object> CreateCondition(RecruitVo recruitVo){
        Map<String,Object> condition = Maps.newHashMap();

        if(StringUtils.isNoneBlank(recruitVo.getName())){
            condition.put("name",recruitVo.getName());
        }

        if(recruitVo.getSex() != null){
            condition.put("sex", recruitVo.getSex());
        }

        if(recruitVo.getMajor() != null){
            condition.put("major", recruitVo.getMajor());
        }

        if(StringUtils.isNoneBlank(recruitVo.getApplyJob())){
            condition.put("applyJob", recruitVo.getApplyJob());
        }

        if(recruitVo.getMarriageStatus() != null){
            condition.put("marriageStatus", recruitVo.getMarriageStatus());
        }

        if(recruitVo.getDegreeOnTime() != null){
            condition.put("degreeOnTime", recruitVo.getDegreeOnTime());
        }

        if (StringUtils.isNoneBlank(recruitVo.getBirthday())){
            condition.put("birthday",recruitVo.getBirthday());
        }

        if(recruitVo.getOrigin() != null){
            condition.put("origin", recruitVo.getOrigin());
        }

        if(recruitVo.getNationalName() != null){
            condition.put("nationalName", recruitVo.getNationalName());
        }

        if(recruitVo.getGraduateStatus() != null){
            condition.put("graduateStatus", recruitVo.getGraduateStatus());
        }

        if(StringUtils.isNoneBlank(recruitVo.getForeignLanguageLevel())){
            condition.put("foreignLanguageLevel", recruitVo.getForeignLanguageLevel());
        }
        return condition;
    }
}

