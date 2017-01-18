package com.wangzhixuan.mapper;

import com.wangzhixuan.model.PeopleTemp;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleTempVo;

import java.util.List;

/**
 * Created by administrator_cernet on 2016/11/27.
 */
public interface PeopleTempMapper {

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
     * @param people
     * @return
     */
    int insert(PeopleTemp peopleTemp);

    /**
     * 修改人员
     *
     * @param people
     * @return
     */
    int updatePeopleTemp(PeopleTemp peopleTemp);

    /**
     * 根据人员名查询人员
     *
     * @param name
     * @return
     */
    PeopleTemp findPeopleTempByName(String name);

    /**
     * 根据人员id查询人员
     *
     * @param id
     * @return
     */
    PeopleTemp findPeopleTempById(Long id);

    /**
     * 人员列表
     *
     * @param pageInfo
     * @return
     */
    List findPeopleTempPageCondition(PageInfo pageInfo);

    /**
     * 统计人员
     *
     * @param pageInfo
     * @return
     */
    int findPeopleTempPageCount(PageInfo pageInfo);
    /**
     * 根据ID查询人员列表
     * @param ids
     * @return
     */
    List selectPeopleTempByIds(String[] ids);

    /**
     * 根据ID查询人员列表
     * @param ids
     * @return
     */
    List selectPeopleTempVoByIds(String[] ids);
    /**
     * 批量添加人员
     * @param list
     * @return
     */
    int insertByImport(List<PeopleTemp> list);


    /**
     * 搜索用户信息根据用户ID
     * @param id
     * @return
     */
    PeopleTempVo findPeopleTempVoById(Long id);


}

