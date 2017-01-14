package com.wangzhixuan.service.impl;

import com.wangzhixuan.mapper.PeopleMapper;
import com.wangzhixuan.mapper.PeopleSalaryMapper;
import com.wangzhixuan.model.PeopleSalary;
import com.wangzhixuan.service.PeopleSalaryService;
import com.wangzhixuan.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sterm on 2017/1/13.
 */
@Service
public class PeopleSalaryServiceImpl implements PeopleSalaryService {

    @Autowired
    private PeopleMapper peopleMapper;

    @Autowired
    private PeopleSalaryMapper peopleSalaryMapper;


    @Override
    public void findDataGrid(PageInfo pageInfo, HttpServletRequest request) {
        pageInfo.setRows(peopleSalaryMapper.findPeopleSalaryPageCondition(pageInfo));
        pageInfo.setTotal(peopleSalaryMapper.findPeopleSalaryPageCount(pageInfo));
    }

    @Override
    public void addSalary(PeopleSalary peopleSalary){
        peopleSalaryMapper.insert(peopleSalary);
    }

    @Override
    public void updateSalary(PeopleSalary peopleSalary) {
        peopleSalaryMapper.update(peopleSalary);
    }

    @Override
    public void deleteSalarySalaryById(Long id) {
        peopleSalaryMapper.deleteById(id);
    }

    @Override
    public void batchDeletePeopleSalaryByIds(String[] ids) {
        peopleSalaryMapper.batchDeleteByIds(ids);
    }
}
