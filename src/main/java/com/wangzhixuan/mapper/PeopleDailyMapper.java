package com.wangzhixuan.mapper;

import com.wangzhixuan.model.PeopleDaily;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleDailyVo;

import java.util.List;

/**
 * Created by sterm on 2016/11/25.
 */
public interface PeopleDailyMapper {

    PeopleDaily findPeopleDailyById(Integer id);

    List<PeopleDailyVo> findPeopleDailyPageCondition(PageInfo pageInfo);

    int findPeopleDailyPageCount(PageInfo pageInfo);

    void insert(PeopleDaily peopleDaily);

    void updatePeopleDaily(PeopleDaily peopleDaily);

    void deleteById(Integer id);

    void batchDeleteByIds(String[] ids);

    int insertByImport(List<PeopleDaily> list);

    PeopleDailyVo findPeopleDailyVoById(Long aLong);

    List selectPeopleDailyVoByIds(String[] idList);
}
