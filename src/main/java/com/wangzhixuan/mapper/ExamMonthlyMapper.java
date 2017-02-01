package com.wangzhixuan.mapper;

import com.wangzhixuan.model.ExamMonthly;
import com.wangzhixuan.utils.PageInfo;

import java.util.List;
import java.util.Map;

public interface ExamMonthlyMapper {

    ExamMonthly selectByPrimaryKey(Integer id);

    void deleteByPrimaryKey(Integer id);

    int insert(ExamMonthly record);

    int updateByPrimaryKey(ExamMonthly record);

    List findPageCondition(PageInfo pageInfo);

    int findPageCount(PageInfo pageInfo);

    int insertByImport(List<ExamMonthly > list);

    int batchDeleteByIds(String[] ids);

    List findExamMonthlyVoListByCode(String code);

    ExamMonthly findPeopleExamMonthlyResultByCodeAndDate(Map<String, Object> condition);
}