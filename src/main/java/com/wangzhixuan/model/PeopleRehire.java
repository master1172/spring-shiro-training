package com.wangzhixuan.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * Created by administrator_cernet on 2016/11/27.
 */
public class PeopleRehire extends PeopleBase implements Serializable {

    private static final long serialVersionUID = -6771435680301413458L;

    private Integer id;

    private String code;

    private String name;

    private Integer sex;

    private Integer nationalId;

    private Integer nativeId;

    private String birthPlace;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String birthday;

    private String educationName;

    private String politicalName;

    private String healthStatus;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String retireDate;

    private String speciality;

    private Integer beforeDepartmentId;

    private String beforeJobName;

    private Integer beforeJobId;

    private Integer afterDepartmentId;

    private String afterJobName;

    private Integer afterJobId;

    private String photoId;

    private String address;

    private String hukouAddress;

    private String photo;

    private String rehireCategory;

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
        this.nativeId = nationalId;
    }

    public Integer getNativeId() {
        return nativeId;
    }

    public void setNativeId(Integer nativeId) {
        this.nationalId = nativeId;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
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

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getRetireDate() {
        return retireDate;
    }

    public void setRetireDate(String retireDate) {
        this.retireDate = retireDate;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Integer getBeforeDepartmentId() {
        return beforeDepartmentId;
    }

    public void setBeforeDepartmentId(Integer beforeDepartmentId) {
        this.beforeDepartmentId = beforeDepartmentId;
    }

    public String getBeforeJobName() {
        return beforeJobName;
    }

    public void setBeforeJobName(String beforeJobName) {
        this.beforeJobName = beforeJobName;
    }

    public Integer getBeforeJobId() {
        return beforeJobId;
    }

    public void setBeforeJobId(Integer beforeJobId) {
        this.beforeJobId = beforeJobId;
    }

    public String getAfterJobName() {
        return afterJobName;
    }

    public void setAfterJobName(String afterJobName) {
        this.afterJobName = afterJobName;
    }

    public Integer getAfterJobId() {
        return afterJobId;
    }

    public void setAfterJobId(Integer afterJobLevelId) {
        this.afterJobId = afterJobId;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHukouAddress() {
        return hukouAddress;
    }

    public void setHukouAddress(String hukouAddress) {
        this.hukouAddress = hukouAddress;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRehireCategory() {
        return rehireCategory;
    }

    public void setRehireCategory(String category) {
        this.rehireCategory = category;
    }

    public Integer getAfterDepartmentId() {
        return afterDepartmentId;
    }

    public void setAfterDepartmentId(Integer afterDepartmentId) {
        this.afterDepartmentId = afterDepartmentId;
    }

    @Override
    public String toString(){
        return "PeopleRehire{" +
                "id=" + id +
                "}";
    }

}
