package com.wangzhixuan.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Maps;
import com.wangzhixuan.model.PeopleRehire;
import com.wangzhixuan.utils.ConstUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by administrator_cernet on 2016/11/27.
 */
public class PeopleRehireVo implements Serializable {

    private Integer id;

    private String code;

    private String name;

    private Integer sex;

    private Integer nationalId;

    private String nationalName;

    private Integer nativeId;

    private String nativeName;

    private String birthPlace;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String birthday;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String birthdayMin;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String birthdayMax;

    private String educationName;

    private String politicalName;

    private String healthStatus;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String retireDate;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String retireDateMin;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String retireDateMax;

    private String speciality;

    private Integer beforeDepartmentId;

    private String beforeDepartmentName;

    private String beforeJobName;

    private Integer beforeJobLevelId;

    private String beforeJobLevelName;

    private Integer afterDepartmentId;

    private String afterDepartmentName;

    private String afterJobName;

    private Integer afterJobLevelId;

    private String afterJobLevelName;

    private String photoId;

    private String address;

    private String hukouAddress;

    private String photo;

    private String category;

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

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
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

    public Integer getBeforeDepartmentId() { return beforeDepartmentId; }

    public void setBeforeDepartmentId(Integer beforeDepartmentId){
        this.beforeDepartmentId = beforeDepartmentId;
    }

    public String getBeforeDepartmentName() {
        return beforeDepartmentName;
    }

    public void setBeforeDepartmentName(String beforeDepartmentName) {
        this.beforeDepartmentName = beforeDepartmentName;
    }

    public String getBeforeJobName() {
        return beforeJobName;
    }

    public void setBeforeJobName(String beforeJobName) {
        this.beforeJobName = beforeJobName;
    }

    public Integer getBeforeJobLevelId() {
        return beforeJobLevelId;
    }

    public void setBeforeJobLevelId(Integer beforeJobLevelId) {
        this.beforeJobLevelId = beforeJobLevelId;
    }

    public Integer getAfterDepartmentId() {
        return afterDepartmentId;
    }

    public void setAfterDepartmentId(Integer afterDepartmentId) {
        this.afterDepartmentId = afterDepartmentId;
    }

    public String getAfterJobName() {
        return afterJobName;
    }

    public void setAfterJobName(String afterJobName) {
        this.afterJobName = afterJobName;
    }

    public Integer getAfterJobLevelId() {
        return afterJobLevelId;
    }

    public void setAfterJobLevelId(Integer afterJobLevelId) {
        this.afterJobLevelId = afterJobLevelId;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNationalName() {
        return nationalName;
    }

    public void setNationalName(String nationalName) {
        this.nationalName = nationalName;
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

    public String getBeforeJobLevelName() {
        return beforeJobLevelName;
    }

    public void setBeforeJobLevelName(String beforeJobLevelName) {
        this.beforeJobLevelName = beforeJobLevelName;
    }

    public String getAfterDepartmentName() {
        return afterDepartmentName;
    }

    public void setAfterDepartmentName(String afterDepartmentName) {
        this.afterDepartmentName = afterDepartmentName;
    }

    public String getAfterJobLevelName() {
        return afterJobLevelName;
    }

    public void setAfterJobLevelName(String afterJobLevelName) {
        this.afterJobLevelName = afterJobLevelName;
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

    public Integer getNativeId() {
        return nativeId;
    }

    public void setNativeId(Integer nativeId) {
        this.nativeId = nativeId;
    }

    public static Map<String,Object> CreateCondition(PeopleRehireVo peopleRehirevo){
        Map<String, Object> condition = Maps.newHashMap();

        if(StringUtils.isNoneBlank(peopleRehirevo.getCode())){
            condition.put("code", peopleRehirevo.getCode());
        }

        if(StringUtils.isNoneBlank(peopleRehirevo.getName())){
            condition.put("name", peopleRehirevo.getName());
        }

        if (peopleRehirevo.getSex() != null){
            condition.put("sex", peopleRehirevo.getSex());
        }

        if (peopleRehirevo.getNationalId() != null){
            condition.put("nationalId", peopleRehirevo.getNationalId());
        }

        if(peopleRehirevo.getNationalId() != null){
            condition.put("nativeId", peopleRehirevo.getNativeId());
        }

        if(StringUtils.isNoneBlank(peopleRehirevo.getBirthPlace())){
            condition.put("birthPlace", peopleRehirevo.getBirthPlace());
        }

        if(StringUtils.isNoneBlank(peopleRehirevo.getBirthdayMin())){
            condition.put("birthdayMin", peopleRehirevo.getBirthdayMin());
        }

        if(StringUtils.isNoneBlank(peopleRehirevo.getBirthdayMax())){
            condition.put("birthdayMax", peopleRehirevo.getBirthdayMax());
        }

        if(StringUtils.isNoneBlank(peopleRehirevo.getEducationName())){
            condition.put("educationName", peopleRehirevo.getEducationName());
        }

        if(StringUtils.isNoneBlank(peopleRehirevo.getPoliticalName())){
            condition.put("politicalName", peopleRehirevo.getPoliticalName());
        }

        if(StringUtils.isNoneBlank(peopleRehirevo.getHealthStatus())){
            condition.put("healthStatus", peopleRehirevo.getHealthStatus());
        }

        if(StringUtils.isNoneBlank(peopleRehirevo.getRetireDateMin())){
            condition.put("retireDateMin", peopleRehirevo.getRetireDateMin());
        }

        if(StringUtils.isNoneBlank(peopleRehirevo.getRetireDateMax())){
            condition.put("retireDateMax", peopleRehirevo.getRetireDateMax());
        }

        if(StringUtils.isNoneBlank(peopleRehirevo.getSpeciality())){
            condition.put("speciality", peopleRehirevo.getSpeciality());
        }

        if(peopleRehirevo.getBeforeDepartmentId() != null){
            condition.put("beforeDepartmentId", peopleRehirevo.getBeforeDepartmentId());
        }

        if(StringUtils.isNoneBlank(peopleRehirevo.getBeforeJobName())){
            condition.put("beforeJobName", peopleRehirevo.getBeforeJobName());
        }

        if (peopleRehirevo.getBeforeJobLevelId() != null){
            condition.put("beforeJobLevelId", peopleRehirevo.getBeforeJobLevelId());
        }

        if (peopleRehirevo.getAfterDepartmentId() != null){
            condition.put("afterDepartmentId", peopleRehirevo.getAfterDepartmentId());
        }

        if(StringUtils.isNoneBlank(peopleRehirevo.getAfterJobName())){
            condition.put("afterJobName", peopleRehirevo.getAfterJobName());
        }

        if (peopleRehirevo.getAfterJobLevelId() != null){
            condition.put("afterJobLevelId", peopleRehirevo.getAfterJobLevelId());
        }

        if(StringUtils.isNoneBlank(peopleRehirevo.getPhotoId())){
            condition.put("photoId", peopleRehirevo.getPhotoId());
        }

        if(StringUtils.isNoneBlank(peopleRehirevo.getAddress())){
            condition.put("address", peopleRehirevo.getAddress());
        }

        if(StringUtils.isNoneBlank(peopleRehirevo.getHukouAddress())){
            condition.put("hukouAddress", peopleRehirevo.getHukouAddress());
        }

        if(StringUtils.isNoneBlank(peopleRehirevo.getCategory())){
            condition.put("category", peopleRehirevo.getCategory());
        }

        condition.put("status", ConstUtil.PEOPLE_REHIRE);

        return condition;
    }
}
