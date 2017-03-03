package com.wangzhixuan.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by sterm on 2017/2/15.
 */
public class PeopleTotal implements Serializable {

    private static final long serialVersionUID = -5321613594382537423L;
    //id
    private Integer id;

    //人员编码
    private String code;

    //姓名
    private String name;

    //性别
    private Integer sex;

    //民族
    private Integer nationalId;

    //生日
    private String birthday;

    //籍贯
    private Integer nativeId;

    //学历
    private String educationName;

    //学位
    private Integer degreeId;

    //政治面貌
    private String politicalName;

    //入党日期
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String partyDate;

    //工作日期
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String workDate;

    //来源工作日期
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String schoolDate;

    //职务
    private String jobName;

    //职务类别
    private String jobCategory;

    //职级
    private Integer jobId;

    //担任现在职务日期
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String jobDate;

    //担任现在职级日期
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String jobLevelDate;

    //年龄
    private Integer age;

    //虚岁
    private Integer virtualAge;

    //工龄
    private Integer workAge;

    //编制
    private String formation;

    //电话号码
    private String mobile;

    //婚姻状况
    private Integer marriageId;

    //身份证
    private String photoId;

    //住址
    private String address;

    //户籍
    private String hukou;

    //户籍地址
    private String hukouAddress;

    //最终学历
    private String finalEducationName;

    //专业
    private String major;

    //毕业院校
    private String graduateSchool;

    //紧急联系人
    private String contact;

    //与本人关系
    private String relationship;

    //联系人电话
    private String contactNumber;

    //家庭成员1
    private String familyInfo1;

    //家庭成员2
    private String familyInfo2;

    //家庭成员3
    private String familyInfo3;

    //家庭成员4
    private String familyInfo4;

    //照片
    private String photo;

    //身份
    private Integer identityId;

    //人员类型
    private Integer status;

    //来自省
    private String province;

    //来自城市
    private String city;

    //特长
    private String speciality;

    //身高
    private String height;

    //部门id
    private Integer departmentId;

    //其他人员所在部门名称
    private String extraDepartmentName;

    //工种
    private String extraJobName;

    //备注
    private String comment;

    //出生地
    private String birthPlace;

    //健康状况
    private String healthStatus;

    //退休日期
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String retireDate;

    //返聘前工作部门
    private Integer beforeDepartmentId;

    //返聘前岗位名称
    private String beforeJobName;

    //返聘前职级
    private Integer beforeJobId;

    //拟返聘部门
    private Integer afterDepartmentId;

    //返聘岗位名称
    private String afterJobName;

    //返聘职级
    private Integer afterJobId;

    //返聘人员类型
    private String rehireCategory;

    //退休时职务
    private String retireJobName;

    //退休时职级
    private Integer retireJobId;

    //退休状态，0：退休，1：返聘
    private Integer retireStatus;

    //死亡日期
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String deathDate;

    //死亡原因
    private String deathReason;

    //所在支部
    private Integer branchId;

    //党员状态
    private Integer partyStatusId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String partyInDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String partyOutDate;

    //岗位或者职级工资
    private BigDecimal jobSalary;

    //薪级
    private Integer rankId;

    //薪级工资
    private BigDecimal rankSalary;

    //工改保留工资
    private BigDecimal reserveSalary;

    //岗位津贴
    private BigDecimal jobAllowance;

    //绩效津贴
    private BigDecimal performanceAllowance;

    //提租补贴
    private BigDecimal rentAllowance;

    //购房补贴
    private BigDecimal houseAllowance;

    //考勤情况
    private BigDecimal timesheetResult;

    //职务补贴
    private BigDecimal dutyAllowance;

    //适当补贴
    private BigDecimal extraAllowance;

    //通讯补贴
    private BigDecimal telephoneAllowance;

    //交通补贴
    private BigDecimal trafficAllowance;

    //单日值班费用
    private BigDecimal onDutyFee;

    //物业补贴
    private BigDecimal propertyAllowance;

    //挂职补贴
    private BigDecimal extraJobAllowance;

    //降温补贴
    private BigDecimal temperatureAllowance;

    //补发
    private BigDecimal reissueFee;

    //药费报销
    private BigDecimal medicare;

    //年终奖
    private BigDecimal yearlyBonus;

    //养老保险
    private BigDecimal lifeInsurance;

    //失业保险
    private BigDecimal jobInsurance;

    //医疗保险
    private BigDecimal healthInsurance;

    //年金
    private BigDecimal annuity;

    //住房公积金
    private BigDecimal houseFund;

    //院龄工资
    private BigDecimal schoolSalary;

    //考核结果
    private String examResult;

    //岗位考核工资
    private BigDecimal jobExamSalary;

    //特殊补贴
    private BigDecimal specialAllowance;

    //领班补贴
    private BigDecimal headAllowance;

    //奖金
    private BigDecimal bonus;

    //基本工资
    private BigDecimal baseSalary;

    //退休补贴
    private BigDecimal retireAllowance;

    //退休费调增
    private BigDecimal retireFeeIncrease;

    //伙食补贴
    private BigDecimal foodAllowance;

    //卫生补贴
    private BigDecimal healthAllowance;

    //供暖费
    private BigDecimal heatingFee;

    //残疾补贴
    private BigDecimal handicapAllowance;

    //代扣房租
    private BigDecimal rentFee;

    //最后更新日期
    private String lastUpdateDate;

    //养老保险基数
    private BigDecimal lifeInsuranceBase;

    //失业保险基数
    private BigDecimal jobInsuranceBase;

    //工伤保险基数
    private BigDecimal woundInsuranceBase;

    //生育保险基数
    private BigDecimal birthInsuranceBase;

    //医疗保险基数
    private BigDecimal healthInsuranceBase;

    //职业年金基数
    private BigDecimal annuityBase;

    //丧葬费
    private BigDecimal funeralFee;

    //抚恤金
    private BigDecimal deathFee;

    private BigDecimal retireSalaryRatio;

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

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
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

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getExtraDepartmentName() {
        return extraDepartmentName;
    }

    public void setExtraDepartmentName(String extraDepartmentName) {
        this.extraDepartmentName = extraDepartmentName;
    }

    public String getExtraJobName() {
        return extraJobName;
    }

    public void setExtraJobName(String extraJobName) {
        this.extraJobName = extraJobName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
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

    public Integer getBeforeDepartmentId() {
        return beforeDepartmentId;
    }

    public void setBeforeDepartmentId(Integer beforeDepartmentId) {
        this.beforeDepartmentId = beforeDepartmentId;
    }

    public String getBeforeJobName() {
        return beforeJobName;
    }

    public void setBeforeJobName(String beforeJobName) {
        this.beforeJobName = beforeJobName;
    }

    public Integer getBeforeJobId() {
        return beforeJobId;
    }

    public void setBeforeJobId(Integer beforeJobId) {
        this.beforeJobId = beforeJobId;
    }

    public Integer getAfterDepartmentId() {
        return afterDepartmentId;
    }

    public void setAfterDepartmentId(Integer afterDepartmentId) {
        this.afterDepartmentId = afterDepartmentId;
    }

    public String getRehireCategory() {
        return rehireCategory;
    }

    public void setRehireCategory(String rehireCategory) {
        this.rehireCategory = rehireCategory;
    }

    public String getRetireJobName() {
        return retireJobName;
    }

    public void setRetireJobName(String retireJobName) {
        this.retireJobName = retireJobName;
    }

    public Integer getRetireJobId() {
        return retireJobId;
    }

    public void setRetireJobId(Integer retireJobId) {
        this.retireJobId = retireJobId;
    }

    public Integer getRetireStatus() {
        return retireStatus;
    }

    public void setRetireStatus(Integer retireStatus) {
        this.retireStatus = retireStatus;
    }

    public String getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(String deathDate) {
        this.deathDate = deathDate;
    }

    public String getDeathReason() {
        return deathReason;
    }

    public void setDeathReason(String deathReason) {
        this.deathReason = deathReason;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Integer getPartyStatusId() {
        return partyStatusId;
    }

    public void setPartyStatusId(Integer partyStatusId) {
        this.partyStatusId = partyStatusId;
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

    public BigDecimal getJobSalary() {
        return jobSalary;
    }

    public void setJobSalary(BigDecimal jobSalary) {
        this.jobSalary = jobSalary;
    }

    public Integer getRankId() {
        return rankId;
    }

    public void setRankId(Integer rankId) {
        this.rankId = rankId;
    }

    public BigDecimal getRankSalary() {
        return rankSalary;
    }

    public void setRankSalary(BigDecimal rankSalary) {
        this.rankSalary = rankSalary;
    }

    public BigDecimal getReserveSalary() {
        return reserveSalary;
    }

    public void setReserveSalary(BigDecimal reserveSalary) {
        this.reserveSalary = reserveSalary;
    }

    public BigDecimal getPerformanceAllowance() {
        return performanceAllowance;
    }

    public void setPerformanceAllowance(BigDecimal performanceAllowance) {
        this.performanceAllowance = performanceAllowance;
    }

    public BigDecimal getRentAllowance() {
        return rentAllowance;
    }

    public void setRentAllowance(BigDecimal rentAllowance) {
        this.rentAllowance = rentAllowance;
    }

    public BigDecimal getHouseAllowance() {
        return houseAllowance;
    }

    public void setHouseAllowance(BigDecimal houseAllowance) {
        this.houseAllowance = houseAllowance;
    }

    public BigDecimal getTimesheetResult() {
        return timesheetResult;
    }

    public void setTimesheetResult(BigDecimal timesheetResult) {
        this.timesheetResult = timesheetResult;
    }

    public BigDecimal getDutyAllowance() {
        return dutyAllowance;
    }

    public void setDutyAllowance(BigDecimal dutyAllowance) {
        this.dutyAllowance = dutyAllowance;
    }

    public BigDecimal getExtraAllowance() {
        return extraAllowance;
    }

    public void setExtraAllowance(BigDecimal extraAllowance) {
        this.extraAllowance = extraAllowance;
    }

    public BigDecimal getTelephoneAllowance() {
        return telephoneAllowance;
    }

    public void setTelephoneAllowance(BigDecimal telephoneAllowance) {
        this.telephoneAllowance = telephoneAllowance;
    }

    public BigDecimal getTrafficAllowance() {
        return trafficAllowance;
    }

    public void setTrafficAllowance(BigDecimal trafficAllowance) {
        this.trafficAllowance = trafficAllowance;
    }

    public BigDecimal getOnDutyFee() {
        return onDutyFee;
    }

    public void setOnDutyFee(BigDecimal onDutyFee) {
        this.onDutyFee = onDutyFee;
    }

    public BigDecimal getPropertyAllowance() {
        return propertyAllowance;
    }

    public void setPropertyAllowance(BigDecimal propertyAllowance) {
        this.propertyAllowance = propertyAllowance;
    }

    public BigDecimal getExtraJobAllowance() {
        return extraJobAllowance;
    }

    public void setExtraJobAllowance(BigDecimal extrajobAllowance) {
        this.extraJobAllowance = extrajobAllowance;
    }

    public BigDecimal getTemperatureAllowance() {
        return temperatureAllowance;
    }

    public void setTemperatureAllowance(BigDecimal temperatureAllowance) {
        this.temperatureAllowance = temperatureAllowance;
    }

    public BigDecimal getReissueFee() {
        return reissueFee;
    }

    public void setReissueFee(BigDecimal reissueFee) {
        this.reissueFee = reissueFee;
    }

    public BigDecimal getMedicare() {
        return medicare;
    }

    public void setMedicare(BigDecimal medicare) {
        this.medicare = medicare;
    }

    public BigDecimal getYearlyBonus() {
        return yearlyBonus;
    }

    public void setYearlyBonus(BigDecimal yearlyBonus) {
        this.yearlyBonus = yearlyBonus;
    }

    public BigDecimal getLifeInsurance() {
        return lifeInsurance;
    }

    public void setLifeInsurance(BigDecimal lifeInsurance) {
        this.lifeInsurance = lifeInsurance;
    }

    public BigDecimal getJobInsurance() {
        return jobInsurance;
    }

    public void setJobInsurance(BigDecimal jobInsurance) {
        this.jobInsurance = jobInsurance;
    }

    public BigDecimal getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(BigDecimal healthInsurance) {
        this.healthInsurance = healthInsurance;
    }

    public BigDecimal getAnnuity() {
        return annuity;
    }

    public void setAnnuity(BigDecimal annuity) {
        this.annuity = annuity;
    }

    public BigDecimal getHouseFund() {
        return houseFund;
    }

    public void setHouseFund(BigDecimal houseFund) {
        this.houseFund = houseFund;
    }

    public BigDecimal getSchoolSalary() {
        return schoolSalary;
    }

    public void setSchoolSalary(BigDecimal schoolSalary) {
        this.schoolSalary = schoolSalary;
    }

    public String getExamResult() {
        return examResult;
    }

    public void setExamResult(String examResult) {
        this.examResult = examResult;
    }

    public BigDecimal getJobExamSalary() {
        return jobExamSalary;
    }

    public void setJobExamSalary(BigDecimal jobExamSalary) {
        this.jobExamSalary = jobExamSalary;
    }

    public BigDecimal getSpecialAllowance() {
        return specialAllowance;
    }

    public void setSpecialAllowance(BigDecimal specialAllowance) {
        this.specialAllowance = specialAllowance;
    }

    public BigDecimal getHeadAllowance() {
        return headAllowance;
    }

    public void setHeadAllowance(BigDecimal headAllowance) {
        this.headAllowance = headAllowance;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(BigDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }

    public BigDecimal getRetireAllowance() {
        return retireAllowance;
    }

    public void setRetireAllowance(BigDecimal retireAllowance) {
        this.retireAllowance = retireAllowance;
    }

    public BigDecimal getRetireFeeIncrease() {
        return retireFeeIncrease;
    }

    public void setRetireFeeIncrease(BigDecimal retireFeeIncrease) {
        this.retireFeeIncrease = retireFeeIncrease;
    }

    public BigDecimal getHealthAllowance() {
        return healthAllowance;
    }

    public void setHealthAllowance(BigDecimal healthAllowance) {
        this.healthAllowance = healthAllowance;
    }

    public BigDecimal getHeatingFee() {
        return heatingFee;
    }

    public void setHeatingFee(BigDecimal heatingFee) {
        this.heatingFee = heatingFee;
    }

    public BigDecimal getHandicapAllowance() {
        return handicapAllowance;
    }

    public void setHandicapAllowance(BigDecimal handicapAllowance) {
        this.handicapAllowance = handicapAllowance;
    }

    public BigDecimal getRentFee() {
        return rentFee;
    }

    public void setRentFee(BigDecimal rentFee) {
        this.rentFee = rentFee;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getAfterJobName() {
        return afterJobName;
    }

    public void setAfterJobName(String afterJobName) {
        this.afterJobName = afterJobName;
    }

    public Integer getAfterJobId() {
        return afterJobId;
    }

    public void setAfterJobId(Integer afterJobId) {
        this.afterJobId = afterJobId;
    }

    public BigDecimal getJobAllowance() {
        return jobAllowance;
    }

    public void setJobAllowance(BigDecimal jobAllowance) {
        this.jobAllowance = jobAllowance;
    }

    public BigDecimal getFoodAllowance() {
        return foodAllowance;
    }

    public void setFoodAllowance(BigDecimal foodAllowance) {
        this.foodAllowance = foodAllowance;
    }

    public BigDecimal getLifeInsuranceBase() {
        return lifeInsuranceBase;
    }

    public void setLifeInsuranceBase(BigDecimal lifeInsuranceBase) {
        this.lifeInsuranceBase = lifeInsuranceBase;
    }

    public BigDecimal getJobInsuranceBase() {
        return jobInsuranceBase;
    }

    public void setJobInsuranceBase(BigDecimal jobInsuranceBase) {
        this.jobInsuranceBase = jobInsuranceBase;
    }

    public BigDecimal getWoundInsuranceBase() {
        return woundInsuranceBase;
    }

    public void setWoundInsuranceBase(BigDecimal woundInsuranceBase) {
        this.woundInsuranceBase = woundInsuranceBase;
    }

    public BigDecimal getBirthInsuranceBase() {
        return birthInsuranceBase;
    }

    public void setBirthInsuranceBase(BigDecimal birthInsuranceBase) {
        this.birthInsuranceBase = birthInsuranceBase;
    }

    public BigDecimal getHealthInsuranceBase() {
        return healthInsuranceBase;
    }

    public void setHealthInsuranceBase(BigDecimal healthInsuranceBase) {
        this.healthInsuranceBase = healthInsuranceBase;
    }

    public BigDecimal getAnnuityBase() {
        return annuityBase;
    }

    public void setAnnuityBase(BigDecimal annuityBase) {
        this.annuityBase = annuityBase;
    }

    public BigDecimal getFuneralFee() {
        return funeralFee;
    }

    public void setFuneralFee(BigDecimal funeralFee) {
        this.funeralFee = funeralFee;
    }

    public BigDecimal getDeathFee() {
        return deathFee;
    }

    public void setDeathFee(BigDecimal deathFee) {
        this.deathFee = deathFee;
    }

    public BigDecimal getRetireSalaryRatio() {
        return retireSalaryRatio;
    }

    public void setRetireSalaryRatio(BigDecimal retireSalaryRatio) {
        this.retireSalaryRatio = retireSalaryRatio;
    }

    public String toString(){
        return "";
    }
}
