package com.wangzhixuan.vo;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by mengfw on 2017/1/20.
 */
public class ExamYearlyVo implements Serializable {
  private static final long serialVersionUID = 451132512692285175L;
  private Integer id;

  private String peopleCode;

  private String name;

  private String peopleType;

  private Integer year;

  private Integer yearMax;

  private Integer yearMin;

  private String examResult;

  private String examOperation;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getPeopleCode() {
    return peopleCode;
  }

  public void setPeopleCode(String peopleCode) {
    this.peopleCode = peopleCode;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPeopleType() {
    return peopleType;
  }

  public void setPeopleType(String peopleType) {
    this.peopleType = peopleType;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public String getExamResult() {
    return examResult;
  }

  public void setExamResult(String examResult) {
    this.examResult = examResult;
  }

  public String getExamOperation() {
    return examOperation;
  }

  public void setExamOperation(String examOperation) {
    this.examOperation = examOperation;
  }

  public Integer getYearMax() {
    return yearMax;
  }

  public void setYearMax(Integer yearMax) {
    this.yearMax = yearMax;
  }

  public Integer getYearMin() {
    return yearMin;
  }

  public void setYearMin(Integer yearMin) {
    this.yearMin = yearMin;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ExamYearlyVo{");
    sb.append("id=").append(id);
    sb.append(", peopleCode='").append(peopleCode).append('\'');
    sb.append(", name='").append(name).append('\'');
    sb.append(", peopleType='").append(peopleType).append('\'');
    sb.append(", year=").append(year);
    sb.append(", yearMax=").append(yearMax);
    sb.append(", yearMin=").append(yearMin);
    sb.append(", examResult='").append(examResult).append('\'');
    sb.append(", examOperation='").append(examOperation).append('\'');
    sb.append('}');
    return sb.toString();
  }

  public static Map<String,Object> CreateCondition(ExamYearlyVo vo) {
    Map< String, Object > condition = Maps.newHashMap();
    if(StringUtils.isNotBlank(vo.getName())){
      condition.put("name",vo.getName());
    }
    if(vo.getYearMax() != null){
      condition.put("yearMax",vo.getYearMax());
    }
    if(vo.getYearMin() != null){
      condition.put("yearMin",vo.getYearMin());
    }
    if(StringUtils.isNotBlank(vo.getExamResult())){
      condition.put("examResult",vo.getExamResult());
    }
    if(StringUtils.isNotBlank(vo.getExamOperation())){
      condition.put("examOperation",vo.getExamOperation());
    }
    return condition;
  }
}
