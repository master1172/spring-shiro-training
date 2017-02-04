package com.wangzhixuan.mapper;

import com.wangzhixuan.model.PeopleRehire;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleRehireVo;

import java.util.List;

/**
 * Created by administrator_cernet on 2016/11/27.
 */
public interface PeopleRehireMapper {

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
    int insert(PeopleRehire peopleRehire);

    /**
     * 修改人员
     *
     * @param people
     * @return
     */
    int updatePeopleRehire(PeopleRehire peopleRehire);

    /**
     * 根据人员名查询人员
     *
     * @param name
     * @return
     */
    PeopleRehire findPeopleRehireByName(String name);

    /**
     * 根据人员id查询人员
     *
     * @param id
     * @return
     */
    PeopleRehire findPeopleRehireById(Long id);

    /**
     * 人员列表
     *
     * @param pageInfo
     * @return
     */
    List findPeopleRehirePageCondition(PageInfo pageInfo);

    /**
     * 统计人员
     *
     * @param pageInfo
     * @return
     */
    int findPeopleRehirePageCount(PageInfo pageInfo);
    /**
     * 根据ID查询人员列表
     * @param ids
     * @return
     */
    List selectPeopleRehireByIds(String[] ids);

    /**
     * 根据ID查询人员列表
     * @param ids
     * @return
     */
    List selectPeopleRehireVoByIds(String[] ids);
    /**
     * 批量添加人员
     * @param list
     * @return
     */
    int insertByImport(List<PeopleRehire> list);


    /**
     * 搜索用户信息根据用户ID
     * @param id
     * @return
     */
    PeopleRehireVo findPeopleRehireVoById(Long id);

    void deleteByCode(String code);
}
