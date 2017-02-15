package com.wangzhixuan.mapper;

import com.wangzhixuan.model.PeopleRetire;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleRetireVo;

import java.util.List;

/**
 * Created by administrator_cernet on 2016/11/27.
 */
public interface PeopleRetireMapper {

    int batchDeleteByIds(String[] ids);
    /**
     * 删除人员
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 添加人员
     *
     * @param peopleRetire
     * @return
     */
    int insert(PeopleRetire peopleRetire);

    /**
     * 修改人员
     *
     * @param peopleRetire
     * @return
     */
    int updatePeopleRetire(PeopleRetire peopleRetire);

    /**
     * 根据人员名查询人员
     *
     * @param name
     * @return
     */
    PeopleRetire findPeopleRetireByName(String name);

    /**
     * 根据人员id查询人员
     *
     * @param id
     * @return
     */
    PeopleRetire findPeopleRetireById(Long id);

    PeopleRetire findPeopleRetireByCode(String code);

    /**
     * 人员列表
     *
     * @param pageInfo
     * @return
     */
    List findPeopleRetirePageCondition(PageInfo pageInfo);

    /**
     * 统计人员
     *
     * @param pageInfo
     * @return
     */
    int findPeopleRetirePageCount(PageInfo pageInfo);
    /**
     * 根据ID查询人员列表
     * @param ids
     * @return
     */
    List selectPeopleRetireByIds(String[] ids);

    /**
     * 根据ID查询人员列表
     * @param ids
     * @return
     */
    List selectPeopleRetireVoByIds(String[] ids);
    /**
     * 批量添加人员
     * @param list
     * @return
     */
    int insertByImport(List<PeopleRetire> list);


    /**
     * 搜索用户信息根据用户ID
     * @param id
     * @return
     */
    PeopleRetireVo findPeopleRetireVoById(Long id);

    PeopleRetire findFirstPeopleByName(String name);

    
}

