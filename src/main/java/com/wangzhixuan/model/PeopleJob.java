package com.wangzhixuan.model;

import java.io.Serializable;

/**
 * Created by wangwk on 2017/1/15.
 */
public class PeopleJob implements Serializable {
    private static final long serialVersionUID = -5321613594382537423L;

    private Long id;

    private String job_category;

    private String job_level;

    private String salary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJob_category() {
        return job_category;
    }

    public void setJob_category(String job_category) {
        this.job_category = job_category;
    }

    public String getJob_level() {
        return job_level;
    }

    public void setJob_level(String job_level) {
        this.job_level = job_level;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }


    @Override
    public String toString(){
        return "People{" +
                "id=" + id +
                "}";
    }

}
