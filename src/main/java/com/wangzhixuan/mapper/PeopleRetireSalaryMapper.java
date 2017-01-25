package com.wangzhixuan.mapper;

import java.util.List;

import com.wangzhixuan.model.PeopleContractSalary;
import com.wangzhixuan.model.PeopleRetireSalary;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleRetireSalaryVo;

public interface PeopleRetireSalaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PeopleRetireSalary record);

    int insertSelective(PeopleRetireSalary record);

    PeopleRetireSalary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PeopleRetireSalary record);

    int updateByPrimaryKey(PeopleRetireSalary record);
    
    int findPeopleRetireSalaryPageCount(PageInfo pageInfo);

    List findPeopleRetireSalaryPageCondition(PageInfo pageInfo);

    PeopleRetireSalaryVo findPeopleRetireSalaryVoById(Long id);
    
    List selectPeopleRetireSalaryVoByIds(String[] idList);

   	int insertByImport(List<PeopleRetireSalary> list);
    
}