package com.wangzhixuan.mapper;

import com.wangzhixuan.model.PeopleDispatch;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleDispatchVo;

import java.util.List;

/**
 * Created by administrator_cernet on 2016/11/27.
 */
public interface PeopleDispatchMapper {

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
     * @param
     * @return
     */
    int insert(PeopleDispatch peopleDispatch);

    /**
     * 修改人员
     *
     * @param
     * @return
     */
    int updatePeopleDispatch(PeopleDispatch peopleDispatch);

    /**
     * 根据人员名查询人员
     *
     * @param name
     * @return
     */
    PeopleDispatch findPeopleDispatchByName(String name);

    /**
     * 根据人员id查询人员
     *
     * @param id
     * @return
     */
    PeopleDispatch findPeopleDispatchById(Integer id);

    /**
     * 人员列表
     *
     * @param pageInfo
     * @return
     */
    List findPeopleDispatchPageCondition(PageInfo pageInfo);

    /**
     * 统计人员
     *
     * @param pageInfo
     * @return
     */
    int findPeopleDispatchPageCount(PageInfo pageInfo);
    /**
     * 根据ID查询人员列表
     * @param ids
     * @return
     */
    List selectPeopleDispatchByIds(String[] ids);

    /**
     * 根据ID查询人员列表
     * @param ids
     * @return
     */
    List selectPeopleDispatchVoByIds(String[] ids);
    /**
     * 批量添加人员
     * @param list
     * @return
     */
    int insertByImport(List<PeopleDispatch> list);


    /**
     * 搜索用户信息根据用户ID
     * @param id
     * @return
     */
    PeopleDispatchVo findPeopleDispatchVoById(Integer id);
}

