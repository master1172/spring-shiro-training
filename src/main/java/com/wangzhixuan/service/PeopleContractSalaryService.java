package com.wangzhixuan.service;

import com.wangzhixuan.model.PeopleContractSalary;
import com.wangzhixuan.model.PeopleContractSalaryBase;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleContractSalaryVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Created by fengjunfeng on 2017/1/22.
 */
public interface PeopleContractSalaryService {

    void findDataGrid(PageInfo pageInfo, HttpServletRequest request);

    void addSalary(PeopleContractSalary peopleSalary);

    void updateSalary(PeopleContractSalary peopleSalary);

    void deleteSalaryById(Long id);

    PeopleContractSalaryVo findPeopleContractSalaryVoById(Long id);
    
    
    public boolean insertByImport(CommonsMultipartFile[] files);
    
    void exportExcel(HttpServletResponse response, String[] idList);

    PeopleContractSalaryBase findPeopleContractSalaryBaseByCode(String peopleCode);

    void updateSalaryBase(PeopleContractSalaryBase peopleContractSalaryBase);

    PeopleContractSalaryBase findPeopleContractSalaryBaseById(Integer id);

    void findSalaryDataGrid(PageInfo pageInfo, HttpServletRequest request);
}
