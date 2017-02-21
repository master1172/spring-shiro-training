package com.wangzhixuan.mapper;

import com.wangzhixuan.model.SalaryChangeRecord;

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
}
