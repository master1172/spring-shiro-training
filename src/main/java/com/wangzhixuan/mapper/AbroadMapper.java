package com.wangzhixuan.mapper;

import com.wangzhixuan.model.Abroad;
import com.wangzhixuan.utils.PageInfo;

import java.util.List;

public interface AbroadMapper {
    List findPeoplePageCondition(PageInfo pageInfo);

    int findPeoplePageCount(PageInfo pageInfo);

    Abroad findAbroadById(Integer id);
    int delete(Integer id);

    int insert(Abroad abroad);

    int update(Abroad abroad);

    void batchDeleteByIds(String[] idList);

    int insertByImport(List<Abroad> list);

    List selectAbroadVoByIds(String[] idList);
}