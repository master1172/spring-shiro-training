package com.wangzhixuan.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by sterm on 2017/2/14.
 */
public class TrainingVo implements Serializable {

    private Integer id;

    private String code;

    private String name;

    private Integer sex;

    private Integer departmentId;

    private String departmentName;

    private Integer nationalId;

    private String nationalName;

    private String politicalName;

    private Integer jobId;

    private String jobName;

    private String trainingClass;

    private String description;

    public String getTrainingCategory() {
        return trainingCategory;
    }

    public void setTrainingCategory(String trainingCategory) {
        this.trainingCategory = trainingCategory;
    }

    private String trainingCategory;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String endDate;

    private String sumTime;

    private String offWork;

    private String classHour;

    private String trainingPlace;

    private String trainingSchool;

    private String comment;

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

    public Integer getNationalId() {
        return nationalId;
    }

    public void setNationalId(Integer nationalId) {
        this.nationalId = nationalId;
    }

    public String getNationalName() {
        return nationalName;
    }

    public void setNationalName(String nationalName) {
        this.nationalName = nationalName;
    }

    public String getPoliticalName() {
        return politicalName;
    }

    public void setPoliticalName(String politicalName) {
        this.politicalName = politicalName;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getTrainingClass() {
        return trainingClass;
    }

    public void setTrainingClass(String trainingClass) {
        this.trainingClass = trainingClass;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSumTime() {
        return sumTime;
    }

    public void setSumTime(String sumTime) {
        this.sumTime = sumTime;
    }

    public String getOffWork() {
        return offWork;
    }

    public void setOffWork(String offWork) {
        this.offWork = offWork;
    }

    public String getClassHour() {
        return classHour;
    }

    public void setClassHour(String classHour) {
        this.classHour = classHour;
    }

    public String getTrainingPlace() {
        return trainingPlace;
    }

    public void setTrainingPlace(String trainingPlace) {
        this.trainingPlace = trainingPlace;
    }

    public String getTrainingSchool() {
        return trainingSchool;
    }

    public void setTrainingSchool(String trainingSchool) {
        this.trainingSchool = trainingSchool;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public static Map<String,Object> CreateCondition(TrainingVo trainingVo){
        Map<String,Object> condition = Maps.newHashMap();

        if(StringUtils.isNoneBlank(trainingVo.getName())){
            condition.put("name",trainingVo.getName());
        }

        if(trainingVo.getSex() != null){
            condition.put("sex", trainingVo.getSex());
        }

        if(trainingVo.getDepartmentId() != null){
            condition.put("departmentId", trainingVo.getDepartmentId());
        }

        if(trainingVo.getNationalId() != null){
            condition.put("nationalId", trainingVo.getNationalId());
        }

        if(StringUtils.isNoneBlank(trainingVo.getPoliticalName())){
            condition.put("politicalName",trainingVo.getPoliticalName());
        }

        if(trainingVo.getJobId() != null){
            condition.put("jobId", trainingVo.getJobId());
        }

        if(StringUtils.isNoneBlank(trainingVo.getOffWork())){
            condition.put("offWork", trainingVo.getOffWork());
        }

        if(StringUtils.isNoneBlank(trainingVo.getTrainingCategory())){
            condition.put("trainingCategory", trainingVo.getTrainingCategory());
        }

        return condition;
    }
}
