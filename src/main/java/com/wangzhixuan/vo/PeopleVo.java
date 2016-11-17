package com.wangzhixuan.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Created by liushaoyang on 2016/9/18.
 */
public class PeopleVo implements Serializable{

    private Long id;

    private String name;

    private Integer sex;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String birthday;

    private String Job;

    private BigDecimal salary;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String birthdayMin;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String birthdayMax;

    private BigDecimal salaryMin;

    private BigDecimal salaryMax;

    private String photo;

    private Integer degreeId;

    private String degreeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getJob() {
        return Job;
    }

    public void setJob(String job) {
        Job = job;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getBirthdayMin() {
        return birthdayMin;
    }

    public void setBirthdayMin(String birthdayMin) {
        this.birthdayMin = birthdayMin;
    }

    public String getBirthdayMax() { return birthdayMax;}

    public void setBirthdayMax(String birthdayMax) {
        this.birthdayMax = birthdayMax;
    }

    public String getPhoto() {return photo;}

    public void setPhoto(String photo) {this.photo = photo;}

    public Integer getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(Integer degreeId) {
        this.degreeId = degreeId;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public BigDecimal getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(BigDecimal salaryMin) {
        this.salaryMin = salaryMin;
    }

    public BigDecimal getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(BigDecimal salaryMax) {
        this.salaryMax = salaryMax;
    }

    public static Map<String,Object> CreateCondition(PeopleVo peoplevo){
        Map<String, Object> condition = Maps.newHashMap();

        if(StringUtils.isNoneBlank(peoplevo.getName())){
            condition.put("name", peoplevo.getName());
        }

        if(StringUtils.isNoneBlank(peoplevo.getJob())){
            condition.put("job", peoplevo.getJob());
        }

        if (peoplevo.getSex() != null){
            condition.put("sex", peoplevo.getSex());
        }

        if (StringUtils.isNoneBlank(peoplevo.getBirthdayMin())){
            condition.put("birthdayMin",peoplevo.getBirthdayMin());
        }

        if (StringUtils.isNoneBlank(peoplevo.getBirthdayMax())){
            condition.put("birthdayMax",peoplevo.getBirthdayMax());
        }

        if (peoplevo.getSalaryMin() != null){
            condition.put("salaryMin", peoplevo.getSalaryMin());
        }

        if (peoplevo.getSalaryMax() != null){
            condition.put("salaryMax", peoplevo.getSalaryMax());
        }

        if (peoplevo.getDegreeId() != null){
            condition.put("degreeId", peoplevo.getDegreeId());
        }

        return condition;
    }
}
