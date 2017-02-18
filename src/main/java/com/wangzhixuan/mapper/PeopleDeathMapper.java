package com.wangzhixuan.mapper;

import com.wangzhixuan.model.PeopleDeath;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleDeathVo;

import java.util.List;

/**
 * Created by administrator_cernet on 2016/11/22.
 */
public interface PeopleDeathMapper  {

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
    int insert(PeopleDeath people);

    /**
     * 修改人员
     *
     * @param people
     * @return
     */
    int updatePeople(PeopleDeath people);

    /**
     * 根据人员id查询人员
     *
     * @param id
     * @return
     */
    PeopleDeath findPeopleById(Integer id);

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
    int insertByImport(List<PeopleDeath> list);


    /**
     * 搜索用户信息根据用户ID
     * @param id
     * @return
     */
    PeopleDeathVo findPeopleVoById(Integer id);

    PeopleDeath findPeopleByCode(String code);
}
