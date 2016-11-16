package com.wangzhixuan.model;

import java.io.Serializable;

/**
 * Created by sterm on 2016/11/12.
 */
public class Dict implements Serializable {
    private static final long serialVersionUID = -5321613594382232118L;

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dict(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public Dict(){

    }
}
