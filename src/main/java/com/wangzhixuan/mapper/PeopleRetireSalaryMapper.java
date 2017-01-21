package com.wangzhixuan.mapper;

import com.wangzhixuan.model.PeopleRetireSalary;

public interface PeopleRetireSalaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PeopleRetireSalary record);

    int insertSelective(PeopleRetireSalary record);

    PeopleRetireSalary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PeopleRetireSalary record);

    int updateByPrimaryKey(PeopleRetireSalary record);
}