package com.wangzhixuan.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * Created by administrator_cernet on 2016/11/22.
 */
public class PeopleDeath extends PeopleBase implements Serializable {

    private static final long serialVersionUID = -3874035833405975520L;

    private Long id;

    private String code;

    private Integer sex;

    private Integer national;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String birthday;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String school_date;

    private String category;

    private Integer job_level_id;

    private String department;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private String death_date;

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

    public String getSchool_date() {
        return school_date;
    }

    public void setSchool_date(String school_date) {
        this.school_date = school_date;
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

    @Override
    public String toString(){
        return "People{" +
                "id=" + id +
                "}";
    }
}
