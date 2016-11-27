package com.wangzhixuan.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Maps;
import com.wangzhixuan.model.PeopleContract;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by administrator_cernet on 2016/11/27.
 */
public class PeopleContractVo implements Serializable {

    private Long id;

    private String code;

    private String name;

    private Integer sex;

    private Integer national_id;

    private String national_name;

    private String province;

    private String city;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String birthday;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String birthdayMin;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String birthdayMax;

    private String education_name;

    private String political_name;

    private String speciality;

    private String height;

    private Integer marriage_id;

    private String marriage_name;

    private Integer hukou;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String school_date;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String school_dateMix;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String school_dateMax;

    private String mobile;

    private String address;

    private String department_name;

    private String job_name;

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

    public Integer getNational_id() {
        return national_id;
    }

    public void setNational_id(Integer national_id) {
        this.national_id = national_id;
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

    public String getEducation_name() {
        return education_name;
    }

    public void setEducation_name(String education_name) {
        this.education_name = education_name;
    }

    public String getPolitical_name() {
        return political_name;
    }

    public void setPolitical_name(String political_name) {
        this.political_name = political_name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Integer getMarriage_id() {
        return marriage_id;
    }

    public void setMarriage_id(Integer marriage_id) {
        this.marriage_id = marriage_id;
    }

    public Integer getHukou() {
        return hukou;
    }

    public void setHukou(Integer hukou) {
        this.hukou = hukou;
    }

    public String getSchool_date() {
        return school_date;
    }

    public void setSchool_date(String school_date) {
        this.school_date = school_date;
    }

    public String getSchool_dateMix() {
        return school_dateMix;
    }

    public void setSchool_dateMix(String school_dateMix) {
        this.school_dateMix = school_dateMix;
    }

    public String getSchool_dateMax() {
        return school_dateMax;
    }

    public void setSchool_dateMax(String school_dateMax) {
        this.school_dateMax = school_dateMax;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
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

        if (peopleContractvo.getNational_id() != null){
            condition.put("national_id", peopleContractvo.getNational_id());
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

        if(StringUtils.isNoneBlank(peopleContractvo.getEducation_name())){
            condition.put("education_name", peopleContractvo.getEducation_name());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getPolitical_name())){
            condition.put("political_name", peopleContractvo.getPolitical_name());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getSpeciality())){
            condition.put("speciality", peopleContractvo.getSpeciality());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getHeight())){
            condition.put("height", peopleContractvo.getHeight());
        }

        if (peopleContractvo.getMarriage_id() != null){
            condition.put("marriage_id", peopleContractvo.getMarriage_id());
        }

        if (peopleContractvo.getHukou() != null){
            condition.put("hukou", peopleContractvo.getHukou());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getSchool_date())){
            condition.put("school_date", peopleContractvo.getSchool_date());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getSchool_dateMix())){
            condition.put("school_dateMin", peopleContractvo.getSchool_dateMix());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getSchool_dateMax())){
            condition.put("school_dateMax", peopleContractvo.getSchool_dateMax());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getMobile())){
            condition.put("mobile", peopleContractvo.getMobile());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getAddress())){
            condition.put("address", peopleContractvo.getAddress());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getDepartment_name())){
            condition.put("department_name", peopleContractvo.getDepartment_name());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getJob_name())){
            condition.put("job_name", peopleContractvo.getJob_name());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getComment())){
            condition.put("comment", peopleContractvo.getComment());
        }

        if(StringUtils.isNoneBlank(peopleContractvo.getPhoto())){
            condition.put("photo", peopleContractvo.getPhoto());
        }

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

    public String getNational_name() {
        return national_name;
    }

    public void setNational_name(String national_name) {
        this.national_name = national_name;
    }

    public String getMarriage_name() {
        return marriage_name;
    }

    public void setMarriage_name(String marriage_name) {
        this.marriage_name = marriage_name;
    }
}
