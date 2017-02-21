package com.wangzhixuan.vo;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class AbroadVo implements Serializable {
    private Integer id;

    private String code;

    private String name;

    private Integer departmentId;

    private String departmentName;

    private String jobName;

    private String jobId;

    private String abroadDate;

    private String country;

    private String passportStatus;

    private String reason;

    private String funding;

    private String issueDate;

    private String pickPassportDate;

    private String returnPassportDate;

    private String comment;

    private static final long serialVersionUID = 1L;

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
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getJobId() {return jobId;}

    public void setJobId(String jobId) {this.jobId = jobId;}

    public String getAbroadDate() {
        return abroadDate;
    }

    public void setAbroadDate(String abroadDate) {
        this.abroadDate = abroadDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getPassportStatus() {
        return passportStatus;
    }

    public void setPassportStatus(String passportStatus) {
        this.passportStatus = passportStatus == null ? null : passportStatus.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getFunding() {
        return funding;
    }

    public void setFunding(String funding) {
        this.funding = funding == null ? null : funding.trim();
    }

    public String getJobName() {return jobName;}

    public void setJobName(String jobName) {this.jobName = jobName;}

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getPickPassportDate() {
        return pickPassportDate;
    }

    public void setPickPassportDate(String pickPassportDate) {
        this.pickPassportDate = pickPassportDate;
    }

    public String getReturnPassportDate() {
        return returnPassportDate;
    }

    public void setReturnPassportDate(String returnPassportDate) {
        this.returnPassportDate = returnPassportDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getDepartmentName() {return departmentName;}

    public void setDepartmentName(String departmentName) {this.departmentName = departmentName;}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", departmentId=").append(departmentId);
        sb.append(", departmentName=").append(departmentName);
        sb.append(", abroadDate=").append(abroadDate);
        sb.append(", country=").append(country);
        sb.append(", passportStatus=").append(passportStatus);
        sb.append(", reason=").append(reason);
        sb.append(", funding=").append(funding);
        sb.append(", issueDate=").append(issueDate);
        sb.append(", pickPassportDate=").append(pickPassportDate);
        sb.append(", returnPassportDate=").append(returnPassportDate);
        sb.append(", comment=").append(comment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static Map<String,Object> CreateCondition(AbroadVo abroadVo){
        Map<String,Object> condition = Maps.newHashMap();

        if(StringUtils.isNoneBlank(abroadVo.getName())){
            condition.put("name",abroadVo.getName());
        }
        if(abroadVo.getDepartmentId() != null){
            condition.put("departmentId", abroadVo.getDepartmentId());
        }
        if(abroadVo.getJobId() != null){
            condition.put("jobId", abroadVo.getJobId());
        }
        if(abroadVo.getAbroadDate() != null){
            condition.put("abroadDate", abroadVo.getAbroadDate());
        }
        if(StringUtils.isNoneBlank(abroadVo.getCountry())){
            condition.put("conuntry", abroadVo.getCountry());
        }
        return condition;
    }
}