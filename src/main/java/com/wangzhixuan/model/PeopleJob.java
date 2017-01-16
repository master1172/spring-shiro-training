package com.wangzhixuan.model;

/**
 * Created by wangwk on 2017/1/15.
 */
public class PeopleJob extends PeopleBase {
    private static final long serialVersionUID = -5321613594382537423L;

    private Long id;

    private String Job_category;

    private String Job_level;

    private String salary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJob_category() {
        return Job_category;
    }

    public void setJob_category(String job_category) {
        this.Job_category = job_category;
    }

    public String getJob_level() {
        return Job_level;
    }

    public void setJob_level(String job_level) {
        this.Job_level = job_level;
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
