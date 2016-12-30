package com.wangzhixuan.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Created by liushaoyang on 2016/9/18.
 */
public class PeopleVo implements Serializable{

    private Long id;

    private String code;

    private String name;

    private Integer sex;

    private Integer nationalId;

    private String nationalName;

    private String nationalIdList;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String birthday;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String birthdayMin;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String birthdayMax;

    private Integer nativeId;

    private String nativeName;

    private String educationName;

    private Integer degreeId;

    private String degreeName;

    private String politicalName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String partyDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String partyDateMin;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String partyDateMax;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String workDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String workDateMin;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String workDateMax;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String schoolDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String schoolDateMin;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String schoolDateMax;

    private String jobName;

    private String jobCategory;

    private Integer jobLevelId;

    private String jobLevelName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String jobDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String jobDateMin;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String jobDateMax;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String jobLevelDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String jobLevelDateMin;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String jobLevelDateMax;

    private Integer age;

    private Integer ageMin;

    private Integer ageMax;

    private Integer virtualAge;

    private Integer virtualAgeMin;

    private Integer virtualAgeMax;

    private Integer workAge;

    private Integer workAgeMin;

    private Integer workAgeMax;

    private String formation;

    private String mobile;

    private Integer marriageId;

    private String marriageName;

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

    private String familyInfo1Title;

    private String familyInfo1Name;

    private String familyInfo1WorkAddress;

    private String familyInfo1Job;

    private String familyInfo1Contact;

    private String familyInfo2;

    private String familyInfo2Title;

    private String familyInfo2Name;

    private String familyInfo2WorkAddress;

    private String familyInfo2Job;

    private String familyInfo2Contact;

    private String familyInfo3;

    private String familyInfo3Title;

    private String familyInfo3Name;

    private String familyInfo3WorkAddress;

    private String familyInfo3Job;

    private String familyInfo3Contact;

    private String familyInfo4;

    private String familyInfo4Title;

    private String familyInfo4Name;

    private String familyInfo4WorkAddress;

    private String familyInfo4Job;

    private String familyInfo4Contact;

    private Integer identityId;

    private String identityName;

    private Integer status;

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

    public String getNationalIdList() {return nationalIdList;}

    public void setNationalIdList(String nationalIdList) {this.nationalIdList = nationalIdList;}

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

    public Integer getNativeId() {
        return nativeId;
    }

    public void setNativeId(Integer nativeId) {
        this.nativeId = nativeId;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
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

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
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

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getWorkDateMin() { return workDateMin;}

    public void setWorkDateMin(String workDateMin) {
        this.workDateMin = workDateMin;
    }

    public String getWorkDateMax() {
        return workDateMax;
    }

    public void setWorkDateMax(String workDateMax) {
        this.workDateMax = workDateMax;
    }

    public String getSchoolDate() {
        return schoolDate;
    }

    public void setSchoolDate(String schoolDate) {
        this.schoolDate = schoolDate;
    }

    public String getSchoolDateMin() {
        return schoolDateMin;
    }

    public void setSchoolDateMin(String schoolDateMin) {
        this.schoolDateMin = schoolDateMin;
    }

    public String getSchoolDateMax() {
        return schoolDateMax;
    }

    public void setSchoolDateMax(String schoolDateMax) {
        this.schoolDateMax = schoolDateMax;
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

    public String getJobLevelDate() {
        return jobLevelDate;
    }

    public void setJobLevelDate(String jobLevelDate) {
        this.jobLevelDate = jobLevelDate;
    }

    public String getJobLevelDateMin() {
        return jobLevelDateMin;
    }

    public void setJobLevelDateMin(String jobLevelDateMin) {
        this.jobLevelDateMin = jobLevelDateMin;
    }

    public String getJobLevelDateMax() {
        return jobLevelDateMax;
    }

    public void setJobLevelDateMax(String jobLevelDateMax) {
        this.jobLevelDateMax = jobLevelDateMax;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(Integer ageMin) {
        this.ageMin = ageMin;
    }

    public Integer getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(Integer ageMax) {
        this.ageMax = ageMax;
    }

    public Integer getVirtualAge() {
        return virtualAge;
    }

    public void setVirtualAge(Integer virtualAge) {
        this.virtualAge = virtualAge;
    }

    public Integer getVirtualAgeMin() {
        return virtualAgeMin;
    }

    public void setVirtualAgeMin(Integer virtualAgeMin) {
        this.virtualAgeMin = virtualAgeMin;
    }

    public Integer getVirtualAgeMax() {
        return virtualAgeMax;
    }

    public void setVirtualAgeMax(Integer virtualAgeMax) {
        this.virtualAgeMax = virtualAgeMax;
    }

    public Integer getWorkAge() {
        return workAge;
    }

    public void setWorkAge(Integer workAge) {
        this.workAge = workAge;
    }

    public Integer getWorkAgeMin() {
        return workAgeMin;
    }

    public void setWorkAgeMin(Integer workAgeMin) {
        this.workAgeMin = workAgeMin;
    }

    public Integer getWorkAgeMax() {
        return workAgeMax;
    }

    public void setWorkAgeMax(Integer workAgeMax) {
        this.workAgeMax = workAgeMax;
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

    public String getMarriageName() {
        return marriageName;
    }

    public void setMarriageName(String marriageName) {
        this.marriageName = marriageName;
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

    public String getFamilyInfo1Title() {
        return familyInfo1Title;
    }

    public void setFamilyInfo1Title(String familyInfo1Title) {
        this.familyInfo1Title = familyInfo1Title;
    }

    public String getFamilyInfo1Name() {
        return familyInfo1Name;
    }

    public void setFamilyInfo1Name(String familyInfo1Name) {
        this.familyInfo1Name = familyInfo1Name;
    }

    public String getFamilyInfo1WorkAddress() {
        return familyInfo1WorkAddress;
    }

    public void setFamilyInfo1WorkAddress(String familyInfo1WorkAddress) {
        this.familyInfo1WorkAddress = familyInfo1WorkAddress;
    }

    public String getFamilyInfo1Job() {
        return familyInfo1Job;
    }

    public void setFamilyInfo1Job(String familyInfo1Job) {
        this.familyInfo1Job = familyInfo1Job;
    }

    public String getFamilyInfo1Contact() {
        return familyInfo1Contact;
    }

    public void setFamilyInfo1Contact(String familyInfo1Contact) {
        this.familyInfo1Contact = familyInfo1Contact;
    }

    public String getFamilyInfo2() {
        return familyInfo2;
    }

    public void setFamilyInfo2(String familyInfo2) {
        this.familyInfo2 = familyInfo2;
    }

    public String getFamilyInfo2Title() {
        return familyInfo2Title;
    }

    public void setFamilyInfo2Title(String familyInfo2Title) {
        this.familyInfo2Title = familyInfo2Title;
    }

    public String getFamilyInfo2Name() {
        return familyInfo2Name;
    }

    public void setFamilyInfo2Name(String familyInfo2Name) {
        this.familyInfo2Name = familyInfo2Name;
    }

    public String getFamilyInfo2WorkAddress() {
        return familyInfo2WorkAddress;
    }

    public void setFamilyInfo2WorkAddress(String familyInfo2WorkAddress) {
        this.familyInfo2WorkAddress = familyInfo2WorkAddress;
    }

    public String getFamilyInfo2Job() {
        return familyInfo2Job;
    }

    public void setFamilyInfo2Job(String familyInfo2Job) {
        this.familyInfo2Job = familyInfo2Job;
    }

    public String getFamilyInfo2Contact() {
        return familyInfo2Contact;
    }

    public void setFamilyInfo2Contact(String familyInfo2Contact) {
        this.familyInfo2Contact = familyInfo2Contact;
    }

    public String getFamilyInfo3() {
        return familyInfo3;
    }

    public void setFamilyInfo3(String familyInfo3) {
        this.familyInfo3 = familyInfo3;
    }

    public String getFamilyInfo3Title() {
        return familyInfo3Title;
    }

    public void setFamilyInfo3Title(String familyInfo3Title) {
        this.familyInfo3Title = familyInfo3Title;
    }

    public String getFamilyInfo3Name() {
        return familyInfo3Name;
    }

    public void setFamilyInfo3Name(String familyInfo3Name) {
        this.familyInfo3Name = familyInfo3Name;
    }

    public String getFamilyInfo3WorkAddress() {
        return familyInfo3WorkAddress;
    }

    public void setFamilyInfo3WorkAddress(String familyInfo3WorkAddress) {
        this.familyInfo3WorkAddress = familyInfo3WorkAddress;
    }

    public String getFamilyInfo3Job() {
        return familyInfo3Job;
    }

    public void setFamilyInfo3Job(String familyInfo3Job) {
        this.familyInfo3Job = familyInfo3Job;
    }

    public String getFamilyInfo3Contact() {
        return familyInfo3Contact;
    }

    public void setFamilyInfo3Contact(String familyInfo3Contact) {
        this.familyInfo3Contact = familyInfo3Contact;
    }

    public String getFamilyInfo4() {
        return familyInfo4;
    }

    public void setFamilyInfo4(String familyInfo4) {
        this.familyInfo4 = familyInfo4;
    }

    public String getFamilyInfo4Title() {
        return familyInfo4Title;
    }

    public void setFamilyInfo4Title(String familyInfo4Title) {
        this.familyInfo4Title = familyInfo4Title;
    }

    public String getFamilyInfo4Name() {
        return familyInfo4Name;
    }

    public void setFamilyInfo4Name(String familyInfo4Name) {
        this.familyInfo4Name = familyInfo4Name;
    }

    public String getFamilyInfo4WorkAddress() {
        return familyInfo4WorkAddress;
    }

    public void setFamilyInfo4WorkAddress(String familyInfo4WorkAddress) {
        this.familyInfo4WorkAddress = familyInfo4WorkAddress;
    }

    public String getFamilyInfo4Job() {
        return familyInfo4Job;
    }

    public void setFamilyInfo4Job(String familyInfo4Job) {
        this.familyInfo4Job = familyInfo4Job;
    }

    public String getFamilyInfo4Contact() {
        return familyInfo4Contact;
    }

    public void setFamilyInfo4Contact(String familyInfo4Contact) {
        this.familyInfo4Contact = familyInfo4Contact;
    }

    public Integer getIdentityId() {
        return identityId;
    }

    public void setIdentityId(Integer identityId) {
        this.identityId = identityId;
    }

    public String getIdentityName() {
        return identityName;
    }

    public void setIdentityName(String identityName) {
        this.identityName = identityName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public static Map<String,Object> CreateCondition(PeopleVo peoplevo){
        Map<String, Object> condition = Maps.newHashMap();

        if(StringUtils.isNoneBlank(peoplevo.getName())){
            condition.put("name", peoplevo.getName());
        }

        if (peoplevo.getSex() != null){
            condition.put("sex", peoplevo.getSex());
        }

        if (StringUtils.isNoneBlank(peoplevo.getNationalIdList())){
            condition.put("nationalIdList",peoplevo.getNationalIdList());
        }

        if (StringUtils.isNoneBlank(peoplevo.getBirthdayMin())){
            condition.put("birthdayMin",peoplevo.getBirthdayMin());
        }

        if (StringUtils.isNoneBlank(peoplevo.getBirthdayMax())){
            condition.put("birthdayMax",peoplevo.getBirthdayMax());
        }

        if (StringUtils.isNoneBlank(peoplevo.getPoliticalName())){
            condition.put("politicalName", peoplevo.getPoliticalName());
        }

        if (StringUtils.isNoneBlank(peoplevo.getEducationName())){
            condition.put("educationName", peoplevo.getEducationName());
        }

        if (peoplevo.getDegreeId() != null){
            condition.put("degreeId", peoplevo.getDegreeId());
        }

        if (StringUtils.isNoneBlank(peoplevo.getPartyDateMin())){
            condition.put("partyDateMin", peoplevo.getPartyDateMin());
        }

        if (StringUtils.isNoneBlank(peoplevo.getPartyDateMax())){
            condition.put("partyDateMax", peoplevo.getPartyDateMax());
        }

        if (StringUtils.isNoneBlank(peoplevo.getWorkDateMin())){
            condition.put("workDateMin", peoplevo.getWorkDateMin());
        }

        if (StringUtils.isNoneBlank(peoplevo.getWorkDateMax())){
            condition.put("workDateMax", peoplevo.getWorkDateMax());
        }

        if (StringUtils.isNoneBlank(peoplevo.getSchoolDateMin())){
            condition.put("schoolDateMin", peoplevo.getSchoolDateMin());
        }

        if (StringUtils.isNoneBlank(peoplevo.getSchoolDateMax())){
            condition.put("schoolDateMax", peoplevo.getSchoolDateMax());
        }

        if (StringUtils.isNoneBlank(peoplevo.getJobName())){
            condition.put("jobName", peoplevo.getJobName());
        }

        if (StringUtils.isNoneBlank(peoplevo.getJobCategory())){
            condition.put("jobCategory",peoplevo.getJobCategory());
        }

        if (peoplevo.getJobLevelId() != null){
            condition.put("jobLevelId", peoplevo.getJobLevelId());
        }

        if (StringUtils.isNoneBlank(peoplevo.getJobDateMin())){
            condition.put("jobDateMin", peoplevo.getJobDateMin());
        }

        if(StringUtils.isNoneBlank(peoplevo.getJobDateMax())){
            condition.put("jobDateMax", peoplevo.getJobDateMax());
        }

        if(StringUtils.isNoneBlank(peoplevo.getJobLevelDateMin())){
            condition.put("jobLevelDateMin",peoplevo.getJobLevelDateMin());
        }

        if(StringUtils.isNoneBlank(peoplevo.getJobLevelDateMax())){
            condition.put("jobLevelDateMax", peoplevo.getJobLevelDateMax());
        }

        return condition;
    }


}
