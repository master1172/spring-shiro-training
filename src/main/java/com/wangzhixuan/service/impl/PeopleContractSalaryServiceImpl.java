package com.wangzhixuan.service.impl;

import com.wangzhixuan.mapper.PeopleContractSalaryMapper;
import com.wangzhixuan.mapper.PeopleMapper;
import com.wangzhixuan.model.PeopleContractSalary;
import com.wangzhixuan.service.PeopleContractSalaryService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleContractSalaryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sterm on 2017/1/13.
 */
@Service
public class PeopleContractSalaryServiceImpl implements PeopleContractSalaryService {

    @Autowired
    private PeopleMapper peopleMapper;

    @Autowired
    private PeopleContractSalaryMapper peopleContractSalaryMapper;


    @Override
    public void findDataGrid(PageInfo pageInfo, HttpServletRequest request) {
        pageInfo.setRows(peopleContractSalaryMapper.findPeopleContractSalaryPageCondition(pageInfo));
        pageInfo.setTotal(peopleContractSalaryMapper.findPeopleContractSalaryPageCount(pageInfo));
    }

    @Override
    public void addSalary(PeopleContractSalary peopleSalary){
        peopleContractSalaryMapper.insert(peopleSalary);
    }

    @Override
    public void updateSalary(PeopleContractSalary peopleSalary) {
        peopleContractSalaryMapper.updateByPrimaryKeySelective(peopleSalary);
    }

    @Override
    public void deleteSalaryById(Long id) {
        peopleContractSalaryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void batchDeleteSalaryByIds(String[] ids) {
        peopleContractSalaryMapper.batchDeleteByIds(ids);
    }

    @Override
    public PeopleContractSalaryVo findPeopleContractSalaryVoById(Long id) {
        return peopleContractSalaryMapper.findPeopleContractSalaryVoById(id);
    }
}
