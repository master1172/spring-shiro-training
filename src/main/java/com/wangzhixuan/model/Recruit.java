package com.wangzhixuan.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * Created by sterm on 2017/2/15.
 */
public class Recruit implements Serializable{

    private Integer id;

    private String name;

    private Integer sex;

    private Integer age;

    private String speciality;

    private String applyJob;

    private String origin;

    private String nationalId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String birthday;

    private String politicalName;

    private String health;

    private String graduateSchool;

    private String degree;

    private String degreeOnTime;

    private String schoolAddress;

    private String graduateStatus;

    private String foreignLanguageLevel;

    private Integer marriageId;

    private String cellphone;

    private String photoId;

    private String email;

    private String address;

    private String zipcode;

    private String studyExperience;

    private String specialityAndAbility;

    private String socialExperience;

    private String award;


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

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
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

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
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

    public String getDegreeOnTime() {
        return degreeOnTime;
    }

    public void setDegreeOnTime(String degreeOnTime) {
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
}
