package com.wangzhixuan.mapper;

import com.wangzhixuan.model.PeopleContract;
import com.wangzhixuan.model.PeopleContractSalary;
import com.wangzhixuan.model.PeopleContractSalaryBase;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleContractSalaryVo;

import java.util.List;

public interface PeopleContractSalaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PeopleContractSalary record);

    int insertSelective(PeopleContractSalary record);

    PeopleContractSalary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PeopleContractSalary record);

    int updateByPrimaryKey(PeopleContractSalary record);

    int findPeopleContractSalaryPageCount(PageInfo pageInfo);

    List findPeopleContractSalaryPageCondition(PageInfo pageInfo);

    void batchDeleteByIds(String[] ids);

    PeopleContractSalaryVo findPeopleContractSalaryVoById(Long id);
    
    List selectPeopleContractSalaryVoByIds(String[] idList);

	int insertByImport(List<PeopleContractSalary> list);

    List findPeopleContractSalaryVoListByCode(String peopleCode);

    PeopleContractSalaryBase findPeopleContractSalaryBaseByCode(String peopleCode);

    void addSalaryBase(PeopleContractSalaryBase peopleContractSalaryBase);

    void updateSalaryBase(PeopleContractSalaryBase peopleContractSalaryBase);

    List findPeopleContractSalaryBasePageCondition(PageInfo pageInfo);

    int findPeopleContractSalaryBasePageCount(PageInfo pageInfo);

    PeopleContractSalaryBase findPeopleContractSalaryBaseById(Integer id);

    PeopleContractSalary findPeopleContractSalaryById(Integer id);

    PeopleContractSalary findLatestPeopleSalaryByCode(String code);
}