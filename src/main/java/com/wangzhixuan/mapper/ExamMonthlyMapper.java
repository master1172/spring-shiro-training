package com.wangzhixuan.mapper;

import com.wangzhixuan.model.ExamMonthly;
import com.wangzhixuan.model.ExamYearly;
import com.wangzhixuan.utils.PageInfo;

import java.util.List;

public interface ExamMonthlyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExamMonthly record);

    int insertSelective(ExamMonthly record);

    ExamMonthly selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExamMonthly record);

    int updateByPrimaryKey(ExamMonthly record);

    List findPageCondition(PageInfo pageInfo);

    List selectByIds(String[] ids);

    int insertByImport(List<ExamMonthly > list);

    int batchDeleteByIds(String[] ids);
}