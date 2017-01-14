package com.wangzhixuan.mapper;

import com.wangzhixuan.model.PeopleSalary;
import com.wangzhixuan.utils.PageInfo;

import java.util.List;

/**
 * Created by sterm on 2017/1/13.
 */
public interface PeopleSalaryMapper {

    int batchDeleteByIds(String[] ids);

    int deleteById(Long id);

    int insert(PeopleSalary peopleSalary);

    int update(PeopleSalary peopleSalary);

    int findPeopleSalaryPageCount(PageInfo pageInfo);

    List findPeopleSalaryPageCondition(PageInfo pageInfo);
}
