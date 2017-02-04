package com.wangzhixuan.mapper;

import com.wangzhixuan.model.PeopleContract;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleContractVo;

import java.util.List;

/**
 * Created by administrator_cernet on 2016/11/27.
 */
public interface PeopleContract2Mapper {

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
    int insert(PeopleContract peopleContract);

    /**
     * 修改人员
     *
     * @param people
     * @return
     */
    int updatePeopleContract(PeopleContract peopleContract);

    /**
     * 根据人员名查询人员
     *
     * @param name
     * @return
     */
    PeopleContract findPeopleContractByName(String name);

    /**
     * 根据人员id查询人员
     *
     * @param id
     * @return
     */
    PeopleContract findPeopleContractById(Long id);

    /**
     * 人员列表
     *
     * @param pageInfo
     * @return
     */
    List findPeopleContractPageCondition(PageInfo pageInfo);

    /**
     * 统计人员
     *
     * @param pageInfo
     * @return
     */
    int findPeopleContractPageCount(PageInfo pageInfo);
    /**
     * 根据ID查询人员列表
     * @param ids
     * @return
     */
    List selectPeopleContractByIds(String[] ids);

    /**
     * 根据ID查询人员列表
     * @param ids
     * @return
     */
    List selectPeopleContractVoByIds(String[] ids);
    /**
     * 批量添加人员
     * @param list
     * @return
     */
    int insertByImport(List<PeopleContract> list);


    /**
     * 搜索用户信息根据用户ID
     * @param id
     * @return
     */
    PeopleContractVo findPeopleContractVoById(Long id);
    PeopleContract findFirstPeopleByName(String name);
    PeopleContract findPeopleContractByCode(String code);
}
