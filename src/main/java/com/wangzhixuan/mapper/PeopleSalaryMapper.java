package com.wangzhixuan.mapper;

import com.wangzhixuan.model.PeopleSalary;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleSalaryBaseVo;
import com.wangzhixuan.vo.PeopleSalaryVo;

import java.util.List;

/**
 * Created by sterm on 2017/1/13.
 */
public interface PeopleSalaryMapper {

    int batchDeleteByIds(String[] ids);

    int deleteSalaryById(Long id);

    int insert(PeopleSalary peopleSalary);

    int update(PeopleSalary peopleSalary);

    int findPeopleSalaryPageCount(PageInfo pageInfo);

    List findPeopleSalaryPageCondition(PageInfo pageInfo);

    PeopleSalaryVo findPeopleSalaryVoById(Long id);

    PeopleSalaryBaseVo findPeopleSalaryBaseVoByCode(String code);

    List findPeopleSalaryVoListByCode(String code);
}
