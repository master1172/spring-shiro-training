package com.wangzhixuan.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by administrator_cernet on 2016/11/22.
 */
public class PeopleDeathVo implements Serializable {

    private Long id;

    private String code;

    private String name;

    private Integer sex;

    private Integer national;

    private String nationalName;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String birthday;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String birthdayMin;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String birthdayMax;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String school_date;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String school_dateMin;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String school_dateMax;

    private String category;

    private Integer job_level_id;

    private String job_level_name;

    private String department;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String death_date;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String death_dateMin;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String death_dateMax;

    private String death_reason;

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

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getNational() {
        return national;
    }

    public void setNational(Integer national) {
        this.national = national;
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

    public String getSchool_date() {
        return school_date;
    }

    public void setSchool_date(String school_date) {
        this.school_date = school_date;
    }

    public String getSchool_dateMin() {
        return school_dateMin;
    }

    public void setSchool_dateMin(String school_dateMin) {
        this.school_dateMin = school_dateMin;
    }

    public String getSchool_dateMax() {
        return school_dateMax;
    }

    public void setSchool_dateMax(String school_dateMax) {
        this.school_dateMax = school_dateMax;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getJob_level_id() {
        return job_level_id;
    }

    public void setJob_level_id(Integer job_level_id) {
        this.job_level_id = job_level_id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDeath_date() {
        return death_date;
    }

    public void setDeath_date(String death_date) {
        this.death_date = death_date;
    }

    public String getDeath_dateMin() {
        return death_dateMin;
    }

    public void setDeath_dateMin(String death_dateMin) {
        this.death_dateMin = death_dateMin;
    }

    public String getDeath_dateMax() {
        return death_dateMax;
    }

    public void setDeath_dateMax(String death_dateMax) {
        this.death_dateMax = death_dateMax;
    }

    public String getDeath_reason() {
        return death_reason;
    }

    public void setDeath_reason(String death_reason) {
        this.death_reason = death_reason;
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

    public String getNationalName() {
        return nationalName;
    }

    public void setNationalName(String nationalName) {
        this.nationalName = nationalName;
    }

    public static Map<String,Object> CreateCondition(PeopleDeathVo peopleDeathvo){
        Map<String, Object> condition = Maps.newHashMap();

        if(StringUtils.isNoneBlank(peopleDeathvo.getCode())){
            condition.put("code", peopleDeathvo.getCode());
        }

        if(StringUtils.isNoneBlank(peopleDeathvo.getName())){
            condition.put("name", peopleDeathvo.getName());
        }

        if (peopleDeathvo.getSex() != null){
            condition.put("sex", peopleDeathvo.getSex());
        }

        if (peopleDeathvo.getNational() != null){
            condition.put("national", peopleDeathvo.getNational());
        }

        if (StringUtils.isNoneBlank(peopleDeathvo.getBirthdayMin())){
            condition.put("birthdayMin",peopleDeathvo.getBirthdayMin());
        }

        if (StringUtils.isNoneBlank(peopleDeathvo.getBirthdayMax())){
            condition.put("birthdayMax",peopleDeathvo.getBirthdayMax());
        }

        if (StringUtils.isNoneBlank(peopleDeathvo.getSchool_dateMin())){
            condition.put("school_dateMin",peopleDeathvo.getSchool_dateMin());
        }

        if (StringUtils.isNoneBlank(peopleDeathvo.getSchool_dateMax())){
            condition.put("school_dateMax",peopleDeathvo.getSchool_dateMax());
        }

        if(StringUtils.isNoneBlank(peopleDeathvo.getCategory())){
            condition.put("category", peopleDeathvo.getCategory());
        }

        if (peopleDeathvo.getJob_level_id() != null){
            condition.put("job_level_id", peopleDeathvo.getJob_level_id());
        }

        if(StringUtils.isNoneBlank(peopleDeathvo.getDepartment())){
            condition.put("department", peopleDeathvo.getDepartment());
        }

        if (StringUtils.isNoneBlank(peopleDeathvo.getDeath_dateMin())){
            condition.put("death_dateMin",peopleDeathvo.getDeath_dateMin());
        }

        if (StringUtils.isNoneBlank(peopleDeathvo.getDeath_dateMax())){
            condition.put("death_dateMax",peopleDeathvo.getDeath_dateMax());
        }

        if(StringUtils.isNoneBlank(peopleDeathvo.getDeath_reason())){
            condition.put("death_reason", peopleDeathvo.getDeath_reason());
        }

        if(StringUtils.isNoneBlank(peopleDeathvo.getComment())){
            condition.put("comment", peopleDeathvo.getComment());
        }

        return condition;
    }

    public String getJob_level_name() {
        return job_level_name;
    }

    public void setJob_level_name(String job_level_name) {
        this.job_level_name = job_level_name;
    }
}
