package com.wangzhixuan.mapper;

import com.wangzhixuan.model.Training;
import com.wangzhixuan.utils.PageInfo;

import java.util.List;

/**
 * Created by sterm on 2017/2/14.
 */
public interface TrainingMapper {

    List findPeoplePageCondition(PageInfo pageInfo);

    int findPeoplePageCount(PageInfo pageInfo);

    Training findTraningById(Integer id);

    void insert(Training training);

    void update(Training training);

    void delete(Integer id);

    void batchDeleteByIds(String[] idList);

    int insertByImport(List<Training> list);

    List selectTrainingVoByIds(String[] idList);
}
