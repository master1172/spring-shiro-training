package com.wangzhixuan.vo;

import com.google.common.collect.Maps;
import com.wangzhixuan.model.ExamMonthly;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Created by mengfw on 2017/1/20.
 */
public class ExamMonthlyVo implements Serializable{
  private static final long serialVersionUID = 4123185494140818886L;
  private Integer id;

  private String peopleCode;

  private String name;

  private String peopleType;

  private String yearMonth;

  private String yearMonthMax;

  private String yearMonthMin;

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

  public String getYearMonth() {
    return yearMonth;
  }

  public void setYearMonth(String yearMonth) {
    this.yearMonth = yearMonth;
  }

  public String getYearMonthMax() {
    return yearMonthMax;
  }

  public void setYearMonthMax(String yearMonthMax) {
    this.yearMonthMax = yearMonthMax;
  }

  public String getYearMonthMin() {
    return yearMonthMin;
  }

  public void setYearMonthMin(String yearMonthMin) {
    this.yearMonthMin = yearMonthMin;
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

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ExamMonthlyVo{");
    sb.append("id=").append(id);
    sb.append(", peopleCode='").append(peopleCode).append('\'');
    sb.append(", name='").append(name).append('\'');
    sb.append(", peopleType='").append(peopleType).append('\'');
    sb.append(", yearMonth='").append(yearMonth).append('\'');
    sb.append(", yearMonthMax='").append(yearMonthMax).append('\'');
    sb.append(", yearMonthMin='").append(yearMonthMin).append('\'');
    sb.append(", examResult='").append(examResult).append('\'');
    sb.append(", examOperation='").append(examOperation).append('\'');
    sb.append('}');
    return sb.toString();
  }
  public static Map<String,Object> CreateCondition(ExamMonthlyVo vo) {
    Map< String, Object > condition = Maps.newHashMap();
    if(StringUtils.isNotBlank(vo.getName())){
      condition.put("name",vo.getName());
    }
    if(StringUtils.isNotBlank(vo.getYearMonthMax())){
      String[]strArr = vo.getYearMonthMax().split("-");
      if(strArr.length < 2){
        throw new RuntimeException("时间格式不对");
      }
      condition.put("yearMax",Integer.parseInt(strArr[0]));
      condition.put("monthMax",Integer.parseInt(strArr[1]));
    }
    if(StringUtils.isNotBlank(vo.getYearMonthMin())){
      String[]strArr = vo.getYearMonthMin().split("-");
      if(strArr.length < 2){
        throw new RuntimeException("时间格式不对");
      }
      condition.put("yearMin",Integer.parseInt(strArr[0]));
      condition.put("monthMin",Integer.parseInt(strArr[1]));
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
