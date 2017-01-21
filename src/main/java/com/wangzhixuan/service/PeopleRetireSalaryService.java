package com.wangzhixuan.service;

import javax.servlet.http.HttpServletRequest;

import com.wangzhixuan.model.PeopleRetireSalary;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleRetireSalaryVo;


public interface PeopleRetireSalaryService {
    void findDataGrid(PageInfo pageInfo, HttpServletRequest request);

    void addSalary(PeopleRetireSalary peopleSalary);

    void updateSalary(PeopleRetireSalary peopleSalary);

    void deleteSalaryById(Long id);

    PeopleRetireSalaryVo findPeopleRetireSalaryVoById(Long id);
}

