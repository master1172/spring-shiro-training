package com.wangzhixuan.service;

import com.wangzhixuan.model.People;
import com.wangzhixuan.utils.PageInfo;

/**
 * Created by liushaoyang on 2016/9/8.
 */
public interface PeopleService {

    /**
     * 根据人员id查询人员
     *
     * @param id
     * @return
     */
    People findPeopleById(Long id);

    /**
     * 根据人员姓名查询人员
     *
     * @param name
     * @return
     */
    People findPeopleByName(String name);


    /**
     * 人员列表
     *
     * @param pageInfo
     */
    void findDataGrid(PageInfo pageInfo);

    /**
     * 添加人员
     *
     * @param people
     */
    void addPeople(People people);



    /**
     * 修改人员
     *
     * @param people
     */
    void updatePeople(People people);

    /**
     * 删除人员
     *
     * @param id
     */
    void deletePeopleById(Long id);
}
