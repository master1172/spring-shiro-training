package com.wangzhixuan.mapper;

import com.wangzhixuan.model.ExamYearly;
import com.wangzhixuan.utils.PageInfo;

import java.util.List;

public interface ExamYearlyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExamYearly record);

    int insertSelective(ExamYearly record);

    ExamYearly selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExamYearly record);

    int updateByPrimaryKey(ExamYearly record);

    List findPageCondition(PageInfo pageInfo);

    List selectByIds(String[] ids);

    int insertByImport(List<ExamYearly> list);

    int batchDeleteByIds(String[] ids);
}