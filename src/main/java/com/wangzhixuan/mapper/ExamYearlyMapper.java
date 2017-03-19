package com.wangzhixuan.mapper;

import com.wangzhixuan.model.ExamYearly;
import com.wangzhixuan.utils.PageInfo;

import java.util.List;
import java.util.Map;

public interface ExamYearlyMapper {
    void deleteByPrimaryKey(Integer id);

    int insert(ExamYearly record);

    ExamYearly selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(ExamYearly record);

    List findPageCondition(PageInfo pageInfo);

    int findPageCount(PageInfo pageInfo);

    int insertByImport(List<ExamYearly> list);

    int batchDeleteByIds(String[] ids);

    List findExamYearlyVoListByCode(String code);

    List findExamYearlyByCodeAndYear(Map<String, Object> condition);
}