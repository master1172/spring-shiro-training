package com.wangzhixuan.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by liushaoyang on 2016/9/8.
 */
public class People implements Serializable {

    private static final long serialVersionUID = -5321613594382537423L;

    private Long id;

    private String name;

    private Integer sex;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String birthday;

    private String job;

    private BigDecimal salary;

    private String photo;

    private Integer degreeId;

    private String address;

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
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) { this.salary = salary;}

    public Integer getDegreeId() { return degreeId;}

    public void setDegreeId(Integer degreeId) {this.degreeId = degreeId;}

    public String getPhoto(){return photo;}

    public void setPhoto(String photo){
        this.photo = photo;
    }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address;}

    @Override
    public String toString(){
        return "People{" +
                "id=" + id +
                "}";
    }
}
