package com.wangzhixuan.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by liushaoyang on 2016/9/8.
 */
public class People extends PeopleBase implements Serializable {

    private static final long serialVersionUID = -5321613594382537423L;

    private Long id;

    private String code;

    private String name;

    private Integer sex;

    private Integer nationalId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String birthday;

    private Integer nativeId;

    private String educationName;

    private Integer degreeId;

    private String politicalName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String partyDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String workDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String schoolDate;

    private String jobName;

    private String jobCategory;

    private Integer jobLevelId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String jobDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String jobLevelDate;

    private Integer age;

    private Integer virtualAge;

    private Integer workAge;

    private String formation;

    private String mobile;

    private Integer marriageId;

    private String photoId;

    private String address;

    private String hukou;

    private String hukouAddress;

    private String finalEducationName;

    private String major;

    private String graduateSchool;

    private String contact;

    private String relationship;

    private String contactNumber;

    private String familyInfo1;

    private String familyInfo2;

    private String familyInfo3;

    private String familyInfo4;

    private String photo;

    private Integer identityId;

    private Integer status;

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

    public Integer getNationalId() {
        return nationalId;
    }

    public void setNationalId(Integer nationalId) {
        this.nationalId = nationalId;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getNativeId() {
        return nativeId;
    }

    public void setNativeId(Integer nativeId) {
        this.nativeId = nativeId;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public Integer getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(Integer degreeId) {
        this.degreeId = degreeId;
    }

    public String getPoliticalName() {
        return politicalName;
    }

    public void setPoliticalName(String politicalName) {
        this.politicalName = politicalName;
    }

    public String getPartyDate() {
        return partyDate;
    }

    public void setPartyDate(String partyDate) {
        this.partyDate = partyDate;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getSchoolDate() {
        return schoolDate;
    }

    public void setSchoolDate(String schoolDate) {
        this.schoolDate = schoolDate;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    public Integer getJobLevelId() {
        return jobLevelId;
    }

    public void setJobLevelId(Integer jobLevelId) {
        this.jobLevelId = jobLevelId;
    }

    public String getJobDate() {
        return jobDate;
    }

    public void setJobDate(String jobDate) {
        this.jobDate = jobDate;
    }

    public String getJobLevelDate() {
        return jobLevelDate;
    }

    public void setJobLevelDate(String jobLevelDate) {
        this.jobLevelDate = jobLevelDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getVirtualAge() {
        return virtualAge;
    }

    public void setVirtualAge(Integer virtualAge) {
        this.virtualAge = virtualAge;
    }

    public Integer getWorkAge() {
        return workAge;
    }

    public void setWorkAge(Integer workAge) {
        this.workAge = workAge;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getMarriageId() {
        return marriageId;
    }

    public void setMarriageId(Integer marriageId) {
        this.marriageId = marriageId;
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

    public String getHukou() {
        return hukou;
    }

    public void setHukou(String hukou) {
        this.hukou = hukou;
    }

    public String getHukouAddress() {
        return hukouAddress;
    }

    public void setHukouAddress(String hukouAddress) {
        this.hukouAddress = hukouAddress;
    }

    public String getFinalEducationName() {
        return finalEducationName;
    }

    public void setFinalEducationName(String finalEducationName) {
        this.finalEducationName = finalEducationName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getFamilyInfo1() {
        return familyInfo1;
    }

    public void setFamilyInfo1(String familyInfo1) {
        this.familyInfo1 = familyInfo1;
    }

    public String getFamilyInfo2() {
        return familyInfo2;
    }

    public void setFamilyInfo2(String familyInfo2) {
        this.familyInfo2 = familyInfo2;
    }

    public String getFamilyInfo3() {
        return familyInfo3;
    }

    public void setFamilyInfo3(String familyInfo3) {
        this.familyInfo3 = familyInfo3;
    }

    public String getFamilyInfo4() {
        return familyInfo4;
    }

    public void setFamilyInfo4(String familyInfo4) {
        this.familyInfo4 = familyInfo4;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getIdentityId() {
        return identityId;
    }

    public void setIdentityId(Integer identityId) {
        this.identityId = identityId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return "People{" +
                "id=" + id +
                "}";
    }
}
