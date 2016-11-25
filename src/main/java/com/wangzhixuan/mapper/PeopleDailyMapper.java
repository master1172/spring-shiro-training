package com.wangzhixuan.mapper;

import com.wangzhixuan.model.PeopleDaily;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleDailyVo;

import java.util.List;

/**
 * Created by sterm on 2016/11/25.
 */
public interface PeopleDailyMapper {

    PeopleDaily findPeopleDailyById(Long id);

    PeopleDaily findPeopleDailyByName(String name);

    List findPeoplePageDailyCondition(PageInfo pageInfo);

    int findPeopleDailyPageCount(PageInfo pageInfo);

    void insert(PeopleDaily peopleDaily);

    void updatePeopleDaily(PeopleDaily peopleDaily);

    void deleteById(Long id);

    void batchDeleteByIds(String[] ids);

    int insertByImport(List<PeopleDaily> list);

    PeopleDailyVo findPeopleDailyVoById(Long aLong);

    List selectPeopleDailyVoByIds(String[] idList);

    List<PeopleDailyVo> findPeopleDailyPageCondition(PageInfo pageInfo);
}
