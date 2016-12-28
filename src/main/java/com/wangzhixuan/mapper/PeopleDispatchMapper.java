package com.wangzhixuan.mapper;

import com.wangzhixuan.model.PeopleDispatch;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleDispatchVo;

import java.util.List;

/**
 * Created by sterm on 2016/11/25.
 */
public interface PeopleDispatchMapper {

    PeopleDispatch findPeopleDispatchById(Long id);

    List<PeopleDispatchVo> findPeopleDispatchPageCondition(PageInfo pageInfo);

    int findPeopleDispatchPageCount(PageInfo pageInfo);

    void insert(PeopleDispatch peopleDispatch);

    void updatePeopleDispatch(PeopleDispatch peopleDispatch);

    void deleteById(Long id);

    void batchDeleteByIds(String[] ids);

    int insertByImport(List<PeopleDispatch> list);

    PeopleDispatchVo findPeopleDispatchVoById(Long aLong);

    List selectPeopleDispatchVoByIds(String[] idList);
}
