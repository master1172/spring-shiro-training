package com.wangzhixuan.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by wangwk on 2017/1/15.
 */
public class PeopleRank implements Serializable {
    private static final long serialVersionUID = -5321613594382537423L;

    private Long id;

    private String rank_level;

    private BigDecimal salary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRank_level() {
        return rank_level;
    }

    public void setRank_level(String rank_level) {
        this.rank_level = rank_level;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }



    @Override
    public String toString(){
        return "People{" +
                "id=" + id +
                "}";
    }

}
