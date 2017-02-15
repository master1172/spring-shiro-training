package com.wangzhixuan.mapper;

import com.wangzhixuan.model.PeopleTotal;

/**
 * Created by sterm on 2017/2/15.
 */
public interface PeopleTotalMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(PeopleTotal record);

    int insertSelective(PeopleTotal record);

    PeopleTotal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PeopleTotal record);

    int updateByPrimaryKey(PeopleTotal record);
}
