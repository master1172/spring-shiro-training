package com.wangzhixuan.mapper;

import com.wangzhixuan.model.PeopleTransfer;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleTransferVo;

import java.util.List;

/**
 * Created by administrator_cernet on 2016/11/27.
 */
public interface PeopleTransferMapper {

    /**
     * 添加人员
     *
     * @param peopleTransfer
     * @return
     */
    int insert(PeopleTransfer peopleTransfer);

    /**
     * 修改人员
     *
     * @param peopleTransfer
     * @return
     */
    int updatePeopleTransfer(PeopleTransfer peopleTransfer);

    /**
     * 根据人员名查询人员
     *
     * @param name
     * @return
     */
    PeopleTransfer findPeopleTransferByName(String name);

    /**
     * 根据人员id查询人员
     *
     * @param id
     * @return
     */
    PeopleTransfer findPeopleTransferById(Long id);

    List findPeopleTransferCodeListByConditions(PageInfo pageInfo);
    /**
     * 人员列表
     *
     * @param pageInfo
     * @return
     */
    List findPeopleTransferPageCondition(PageInfo pageInfo);

    List findPeopleTransferListPageCondition(PageInfo pageInfo);

    int findPeopleTransferListPageCount(PageInfo pageInfo);

    /**
     * 统计人员
     *
     * @param pageInfo
     * @return
     */
    int findPeopleTransferPageCount(PageInfo pageInfo);
    /**
     * 根据ID查询人员列表
     * @param ids
     * @return
     */
    List selectPeopleTransferByIds(String[] ids);

    /**
     * 根据ID查询人员列表
     * @param ids
     * @return
     */
    List selectPeopleTransferVoByIds(String[] ids);


    List findPeopleTransferListByCode(String code);

    List selectPeopleTransferByCodeList(List<String> codeList);

    /**
     * 批量添加人员
     * @param list
     * @return
     */
    int insertByImport(List<PeopleTransfer> list);


    /**
     * 搜索用户信息根据用户ID
     * @param id
     * @return
     */
    PeopleTransferVo findPeopleTransferVoById(Long id);

    void deleteById(Long id);
}


