package com.wangzhixuan.mapper;

import com.wangzhixuan.model.PeopleContractSalary;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleContractSalaryVo;

import java.util.List;

public interface TimesheetMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PeopleContractSalary record);

    int insertSelective(PeopleContractSalary record);

    PeopleContractSalary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PeopleContractSalary record);


    int updateByPrimaryKey(PeopleContractSalary record);

    int findPeopleContractSalaryPageCount(PageInfo pageInfo);

    List findPeopleContractSalaryPageCondition(PageInfo pageInfo);

    // TODO
    void batchDeleteByIds(String[] ids);

    // TODO
    PeopleContractSalaryVo findPeopleContractSalaryVoById(Long id);
    //add this part for timesheet
}