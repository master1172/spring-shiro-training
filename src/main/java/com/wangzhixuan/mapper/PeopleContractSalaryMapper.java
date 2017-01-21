<<<<<<< HEAD
package com.wangzhixuan.mapper;

import com.wangzhixuan.model.PeopleContractSalary;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleContractSalaryVo;

import java.util.List;

public interface PeopleContractSalaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PeopleContractSalary record);

    int insertSelective(PeopleContractSalary record);

    PeopleContractSalary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PeopleContractSalary record);

    int updateByPrimaryKey(PeopleContractSalary record);

    int findPeopleContractSalaryPageCount(PageInfo pageInfo);

    List findPeopleContractSalaryPageCondition(PageInfo pageInfo);

    void batchDeleteByIds(String[] ids);

    PeopleContractSalaryVo findPeopleContractSalaryVoById(Long id);

=======
package com.wangzhixuan.mapper;

import com.wangzhixuan.model.PeopleContractSalary;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleContractSalaryVo;

import java.util.List;

public interface PeopleContractSalaryMapper {
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

>>>>>>> cf86893c839aa89d18f71a929d42248c8674306e
}