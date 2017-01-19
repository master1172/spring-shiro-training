package com.wangzhixuan.service;

import com.wangzhixuan.model.PeopleSalary;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleSalaryVo;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sterm on 2017/1/13.
 */
public interface PeopleSalaryService {

    void findDataGrid(PageInfo pageInfo, HttpServletRequest request);

    void addSalary(PeopleSalary peopleSalary);

    void updateSalary(PeopleSalary peopleSalary);

    void deleteSalaryById(Long id);

    void batchDeleteSalaryByIds(String[] ids);

    PeopleSalaryVo findPeopleSalaryVoById(Long id);
}
