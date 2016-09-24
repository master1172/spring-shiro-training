package com.wangzhixuan.model;

import java.io.Serializable;

/**
 * Created by liushaoyang on 2016/9/18.
 */
public class Category implements Serializable {

    private static final long serialVersionUID = -5321613594382531238L;

    private Long id;

    private Long pid;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
