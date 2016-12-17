package com.wangzhixuan.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Maps;
import com.wangzhixuan.model.PeopleParty;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by administrator_cernet on 2016/12/17.
 */
public class PeoplePartyVo implements Serializable {

    private Long id;

    private String peopleCode;

    private String peopleType;

    private String peopleName;

    private Integer branchId;

    private String branchName;

    private Integer departmentId;

    private String departmentName;

    private Integer sex;

    private Integer nationalId;

    private String nationalName;

    @JsonFormat(pattern = "yy-mm-dd")
    private String birthday;

    @JsonFormat(pattern = "yy-mm-dd")
    private String birthdayMin;

    @JsonFormat(pattern = "yy-mm-dd")
    private String birthdayMax;

    private String nativeName;

    private Integer partyStatusId;

    private String partyStatusName;

    @JsonFormat(pattern = "yy-mm-dd")
    private String partyDate;

    @JsonFormat(pattern = "yy-mm-dd")
    private String partyDateMin;

    @JsonFormat(pattern = "yy-mm-dd")
    private String partyDateMax;

    private Integer degreeId;

    private String degreeName;

    @JsonFormat(pattern = "yy-mm-dd")
    private String workDate;

    @JsonFormat(pattern = "yy-mm-dd")
    private String workDateMin;

    @JsonFormat(pattern = "yy-mm-dd")
    private String workDateMax;

    private String jobName;

    private Integer jobLevelId;

    private String jobLevelName;

    @JsonFormat(pattern = "yy-mm-dd")
    private String jobDate;

    @JsonFormat(pattern = "yy-mm-dd")
    private String jobDateMin;

    @JsonFormat(pattern = "yy-mm-dd")
    private String jobDateMax;

    private String formation;

    @JsonFormat(pattern = "yy-mm-dd")
    private String partyInDate;

    @JsonFormat(pattern = "yy-mm-dd")
    private String partyInDateMin;

    @JsonFormat(pattern = "yy-mm-dd")
    private String partyInDateMax;

    @JsonFormat(pattern = "yy-mm-dd")
    private String partyOutDate;

    @JsonFormat(pattern = "yy-mm-dd")
    private String partyOutDateMin;

    @JsonFormat(pattern = "yy-mm-dd")
    private String partyOutDateMax;

    private String photo;

    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPeopleCode() {
        return peopleCode;
    }

    public void setPeopleCode(String peopleCode) {
        this.peopleCode = peopleCode;
    }

    public String getPeopleType() {
        return peopleType;
    }

    public void setPeopleType(String peopleType) {
        this.peopleType = peopleType;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
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

    public String getNationalName() {
        return nationalName;
    }

    public void setNationalName(String nationalName) {
        this.nationalName = nationalName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public Integer getPartyStatusId() {
        return partyStatusId;
    }

    public void setPartyStatusId(Integer partyStatusId) {
        this.partyStatusId = partyStatusId;
    }

    public String getPartyStatusName() {
        return partyStatusName;
    }

    public void setPartyStatusName(String partyStatusName) {
        this.partyStatusName = partyStatusName;
    }

    public String getPartyDate() {
        return partyDate;
    }

    public void setPartyDate(String partyDate) {
        this.partyDate = partyDate;
    }

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

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getJobLevelId() {
        return jobLevelId;
    }

    public void setJobLevelId(Integer jobLevelId) {
        this.jobLevelId = jobLevelId;
    }

    public String getJobLevelName() {
        return jobLevelName;
    }

    public void setJobLevelName(String jobLevelName) {
        this.jobLevelName = jobLevelName;
    }

    public String getJobDate() {
        return jobDate;
    }

    public void setJobDate(String jobDate) {
        this.jobDate = jobDate;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getPartyInDate() {
        return partyInDate;
    }

    public void setPartyInDate(String partyInDate) {
        this.partyInDate = partyInDate;
    }

    public String getPartyOutDate() {
        return partyOutDate;
    }

    public void setPartyOutDate(String partyOutDate) {
        this.partyOutDate = partyOutDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public String getPartyDateMin() {
        return partyDateMin;
    }

    public void setPartyDateMin(String partyDateMin) {
        this.partyDateMin = partyDateMin;
    }

    public String getPartyDateMax() {
        return partyDateMax;
    }

    public void setPartyDateMax(String partyDateMax) {
        this.partyDateMax = partyDateMax;
    }

    public String getWorkDateMin() {
        return workDateMin;
    }

    public void setWorkDateMin(String workDateMin) {
        this.workDateMin = workDateMin;
    }

    public String getWorkDateMax() {
        return workDateMax;
    }

    public void setWorkDateMax(String workDateMax) {
        this.workDateMax = workDateMax;
    }

    public String getJobDateMin() {
        return jobDateMin;
    }

    public void setJobDateMin(String jobDateMin) {
        this.jobDateMin = jobDateMin;
    }

    public String getJobDateMax() {
        return jobDateMax;
    }

    public void setJobDateMax(String jobDateMax) {
        this.jobDateMax = jobDateMax;
    }

    public String getPartyInDateMin() {
        return partyInDateMin;
    }

    public void setPartyInDateMin(String partyInDateMin) {
        this.partyInDateMin = partyInDateMin;
    }

    public String getPartyInDateMax() {
        return partyInDateMax;
    }

    public void setPartyInDateMax(String partyInDateMax) {
        this.partyInDateMax = partyInDateMax;
    }

    public String getPartyOutDateMin() {
        return partyOutDateMin;
    }

    public void setPartyOutDateMin(String partyOutDateMin) {
        this.partyOutDateMin = partyOutDateMin;
    }

    public String getPartyOutDateMax() {
        return partyOutDateMax;
    }

    public void setPartyOutDateMax(String partyOutDateMax) {
        this.partyOutDateMax = partyOutDateMax;
    }

    public static Map<String,Object> CreateCondition(PeoplePartyVo peoplePartyvo){
        Map<String, Object> condition = Maps.newHashMap();

        if(StringUtils.isNoneBlank(peoplePartyvo.getPeopleCode())){
            condition.put("peopleCode", peoplePartyvo.getPeopleCode());
        }

        if(StringUtils.isNoneBlank(peoplePartyvo.getPeopleType())){
            condition.put("peopleType", peoplePartyvo.getPeopleType());
        }

        if(StringUtils.isNoneBlank(peoplePartyvo.getPeopleName())){
            condition.put("peopleName", peoplePartyvo.getPeopleName());
        }

        if(peoplePartyvo.getBranchId() != null){
            condition.put("branchId", peoplePartyvo.getBranchId());
        }

        if(peoplePartyvo.getDepartmentId() != null){
            condition.put("departmentId",peoplePartyvo.getDepartmentId());
        }

        if(peoplePartyvo.getSex() != null){
            condition.put("sex",peoplePartyvo.getSex());
        }

        if(peoplePartyvo.getNationalId() != null){
            condition.put("nationalId",peoplePartyvo.getNationalId());
        }

        if(StringUtils.isNoneBlank(peoplePartyvo.getBirthdayMin())){
            condition.put("birthdayMin", peoplePartyvo.getBirthdayMin());
        }

        if(StringUtils.isNoneBlank(peoplePartyvo.getBirthdayMax())){
            condition.put("birthdayMax", peoplePartyvo.getBirthdayMax());
        }

        if(StringUtils.isNoneBlank(peoplePartyvo.getNativeName())){
            condition.put("nativeName", peoplePartyvo.getNativeName());
        }

        if(peoplePartyvo.getPartyStatusId() != null){
            condition.put("partyStatusId",peoplePartyvo.getPartyStatusId());
        }

        if(StringUtils.isNoneBlank(peoplePartyvo.getPartyDateMin())){
            condition.put("partyDateMin", peoplePartyvo.getPartyDateMin());
        }

        if(StringUtils.isNoneBlank(peoplePartyvo.getPartyDateMax())){
            condition.put("partyDateMax", peoplePartyvo.getPartyDateMax());
        }

        if(peoplePartyvo.getDegreeId() != null){
            condition.put("degreeId",peoplePartyvo.getDegreeId());
        }

        if(StringUtils.isNoneBlank(peoplePartyvo.getWorkDateMin())){
            condition.put("workDateMin", peoplePartyvo.getWorkDateMin());
        }

        if(StringUtils.isNoneBlank(peoplePartyvo.getWorkDateMax())){
            condition.put("workDateMax", peoplePartyvo.getWorkDateMax());
        }

        if(StringUtils.isNoneBlank(peoplePartyvo.getJobName())){
            condition.put("jobName", peoplePartyvo.getJobName());
        }

        if(peoplePartyvo.getJobLevelId() != null){
            condition.put("jobLevelId",peoplePartyvo.getJobLevelId());
        }

        if(StringUtils.isNoneBlank(peoplePartyvo.getJobDateMin())){
            condition.put("jobDateMin", peoplePartyvo.getJobDateMin());
        }

        if(StringUtils.isNoneBlank(peoplePartyvo.getJobDateMax())){
            condition.put("jobDateMax", peoplePartyvo.getJobDateMax());
        }

        if(StringUtils.isNoneBlank(peoplePartyvo.getFormation())){
            condition.put("formation", peoplePartyvo.getFormation());
        }

        if(StringUtils.isNoneBlank(peoplePartyvo.getPartyInDateMin())){
            condition.put("partyInDateMin", peoplePartyvo.getPartyInDateMin());
        }

        if(StringUtils.isNoneBlank(peoplePartyvo.getPartyInDateMax())){
            condition.put("partyInDateMax", peoplePartyvo.getPartyInDateMax());
        }

        if(StringUtils.isNoneBlank(peoplePartyvo.getPartyOutDateMin())){
            condition.put("partyOutDateMin", peoplePartyvo.getPartyOutDateMin());
        }

        if(StringUtils.isNoneBlank(peoplePartyvo.getPartyOutDateMax())){
            condition.put("partyOutDateMax", peoplePartyvo.getPartyOutDateMax());
        }

        if(StringUtils.isNoneBlank(peoplePartyvo.getComment())){
            condition.put("comment", peoplePartyvo.getComment());
        }

        return condition;
    }
}
