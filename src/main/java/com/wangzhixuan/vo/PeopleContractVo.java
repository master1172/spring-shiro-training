package com.wangzhixuan.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Maps;
import com.wangzhixuan.model.PeopleContract;
import com.wangzhixuan.utils.ConstUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by administrator_cernet on 2016/11/27.
 */
public class PeopleContractVo implements Serializable {

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

    private Integer departmentId;

    private String departmentName;

    private Integer jobId;

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

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {return jobName;}

    public void setJobName(String jobName) {this.jobName = jobName;}

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

    public static Map<String,Object> CreateCondition(PeopleContractVo peopleContractvo){
        Map<String, Object> condition = Maps.newHashMap();

        if(StringUtils.isNoneBlank(peopleContractvo.getCode())){
            condition.put("code", peopleContractvo.getCode());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getName())){
            condition.put("name", peopleContractvo.getName());
        }

        if (peopleContractvo.getSex() != null){
            condition.put("sex", peopleContractvo.getSex());
        }

        if (peopleContractvo.getNationalId() != null){
            condition.put("nationalId", peopleContractvo.getNationalId());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getProvince())){
            condition.put("province", peopleContractvo.getProvince());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getCity())){
            condition.put("city", peopleContractvo.getCity());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getBirthday())){
            condition.put("birthday", peopleContractvo.getBirthday());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getBirthdayMin())){
            condition.put("birthdayMin", peopleContractvo.getBirthdayMin());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getBirthdayMax())){
            condition.put("birthdayMax", peopleContractvo.getBirthdayMax());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getEducationName())){
            condition.put("educationName", peopleContractvo.getEducationName());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getPoliticalName())){
            condition.put("politicalName", peopleContractvo.getPoliticalName());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getSpeciality())){
            condition.put("speciality", peopleContractvo.getSpeciality());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getHeight())){
            condition.put("height", peopleContractvo.getHeight());
        }

        if (peopleContractvo.getMarriageId() != null){
            condition.put("marriageId", peopleContractvo.getMarriageId());
        }

        if (peopleContractvo.getHukou() != null){
            condition.put("hukou", peopleContractvo.getHukou());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getSchoolDate())){
            condition.put("schoolDate", peopleContractvo.getSchoolDate());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getSchoolDateMix())){
            condition.put("schoolDateMin", peopleContractvo.getSchoolDateMix());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getSchoolDateMax())){
            condition.put("schoolDateMax", peopleContractvo.getSchoolDateMax());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getMobile())){
            condition.put("mobile", peopleContractvo.getMobile());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getAddress())){
            condition.put("address", peopleContractvo.getAddress());
        }

        if(peopleContractvo.getDepartmentId() != null){
            condition.put("departmentId", peopleContractvo.getDepartmentId());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getDepartmentName())){
            condition.put("departmentName", peopleContractvo.getDepartmentName());
        }

        if (peopleContractvo.getJobId() != null){
            condition.put("jobId", peopleContractvo.getJobId());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getComment())){
            condition.put("comment", peopleContractvo.getComment());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getPhoto())){
            condition.put("photo", peopleContractvo.getPhoto());
        }

        condition.put("status", ConstUtil.PEOPLE_CONTRACT);

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
