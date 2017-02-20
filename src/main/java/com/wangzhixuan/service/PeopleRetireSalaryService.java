package com.wangzhixuan.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangzhixuan.model.PeopleRetireSalaryBase;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wangzhixuan.model.PeopleRetireSalary;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleRetireSalaryVo;


public interface PeopleRetireSalaryService {
    void findDataGrid(PageInfo pageInfo, HttpServletRequest request);

    void addSalary(PeopleRetireSalary peopleSalary);

    void updateSalary(PeopleRetireSalary peopleSalary);

    void deleteSalaryById(Long id);

    PeopleRetireSalaryVo findPeopleRetireSalaryVoById(Long id);
    
    public boolean insertByImport(CommonsMultipartFile[] files);
    
    void exportExcel(HttpServletResponse response, String[] idList);

    PeopleRetireSalaryBase findPeopleRetireSalaryBaseByCode(String peopleCode);

    void updateSalaryBase(PeopleRetireSalaryBase peopleRetireSalaryBase);

    PeopleRetireSalaryBase findPeopleRetireSalaryBaseById(Integer id);

    void findSalaryDataGrid(PageInfo pageInfo, HttpServletRequest request);
}

