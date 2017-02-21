package com.wangzhixuan.mapper;

import com.wangzhixuan.model.SalaryChangeRecord;
import com.wangzhixuan.utils.PageInfo;


/**
 * Created by sterm on 2017/2/21.
 */
public interface SalaryChangeRecordMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SalaryChangeRecord record);

    int insertSelective(SalaryChangeRecord record);

    SalaryChangeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SalaryChangeRecord record);

    int updateByPrimaryKey(SalaryChangeRecord record);

    java.util.List findSalaryChangeRecordPageCondition(PageInfo pageInfo);

    int findSalaryChangeRecordPageCount(PageInfo pageInfo);
}
