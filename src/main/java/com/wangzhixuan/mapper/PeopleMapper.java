package com.wangzhixuan.mapper;

import com.wangzhixuan.model.People;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleVo;

import java.util.List;
import java.util.Map;

/**
 * Created by liushaoyang on 2016/9/8.
 */
public interface PeopleMapper {

    int batchDeleteByIds(String[] ids);
    /**
     * 删除人员
     *
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 添加人员
     *
     * @param people
     * @return
     */
    int insert(People people);

    /**
     * 修改人员
     *
     * @param people
     * @return
     */
    int updatePeople(People people);

    /**
     * 根据人员名查询人员
     *
     * @param name
     * @return
     */
    People findPeopleByName(String name);

    /**
     * 根据人员id查询人员
     *
     * @param id
     * @return
     */
    People findPeopleById(Integer id);

    /**
     * 人员列表
     *
     * @param pageInfo
     * @return
     */
    List findPeoplePageCondition(PageInfo pageInfo);

    /**
     * 统计人员
     *
     * @param pageInfo
     * @return
     */
    int findPeoplePageCount(PageInfo pageInfo);

    List findPeopleNearRetirePageCondition(PageInfo pageInfo);

    int findPeopleNearRetirePageCount(PageInfo pageInfo);


    /**
     * 根据ID查询人员列表
     * @param ids
     * @return
     */
    List selectPeopleByIds(String[] ids);

    /**
     * 根据ID查询人员列表
     * @param ids
     * @return
     */
    List selectPeopleVoByIds(String[] ids);
    /**
     * 批量添加人员
     * @param list
     * @return
     */
    int insertByImport(List<People> list);


    /**
     * 搜索用户信息根据用户ID
     * @param id
     * @return
     */
    PeopleVo findPeopleVoById(Integer id);

    People findPeopleByCode(String code);

    People findFirstPeopleByName(String name);

    List selectPeopleByCodes(List code);

    List findPeoplePageCondition2(PageInfo pageInfo);

    int findPeoplePageCount2(PageInfo pageInfo);

    List<People> findAllPeople();
}
