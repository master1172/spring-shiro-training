package com.wangzhixuan.mapper;

import com.wangzhixuan.model.PeopleParty;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeoplePartyVo;

import java.util.List;

/**
 * Created by administrator_cernet on 2016/11/27.
 */
public interface PeoplePartyMapper {

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
     * @param peopleParty
     * @return
     */
    int insert(PeopleParty peopleParty);

    /**
     * 修改人员
     *
     * @param peopleParty
     * @return
     */
    int updatePeopleParty(PeopleParty peopleParty);

    /**
     * 根据人员名查询人员
     *
     * @param name
     * @return
     */
    PeopleParty findPeoplePartyByName(String name);

    /**
     * 根据人员id查询人员
     *
     * @param id
     * @return
     */
    PeopleParty findPeoplePartyById(Long id);

    /**
     * 人员列表
     *
     * @param pageInfo
     * @return
     */
    List findPeoplePartyPageCondition(PageInfo pageInfo);

    /**
     * 统计人员
     *
     * @param pageInfo
     * @return
     */
    int findPeoplePartyPageCount(PageInfo pageInfo);
    /**
     * 根据ID查询人员列表
     * @param ids
     * @return
     */
    List selectPeoplePartyByIds(String[] ids);

    /**
     * 根据ID查询人员列表
     * @param ids
     * @return
     */
    List selectPeoplePartyVoByIds(String[] ids);
    /**
     * 批量添加人员
     * @param list
     * @return
     */
    int insertByImport(List<PeopleParty> list);


    /**
     * 搜索用户信息根据用户ID
     * @param id
     * @return
     */
    PeoplePartyVo findPeoplePartyVoById(Long id);

    List selectAllPeoplePartyVo();
}


