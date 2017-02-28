package com.wangzhixuan.service;

import com.wangzhixuan.model.PeopleDeath;
import com.wangzhixuan.utils.PageInfo;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by administrator_cernet on 2016/11/22.
 */
public interface PeopleDeathService {
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
     */
    void findDataGrid(PageInfo pageInfo);

    /**
     * 添加人员
     *
     * @param people
     */
    void addPeople(PeopleDeath people,CommonsMultipartFile file);



    /**
     * 修改人员
     *
     * @param people
     */
    void updatePeople(PeopleDeath people, CommonsMultipartFile file);

    /**
     * 删除人员
     *
     * @param id
     */
    void deletePeopleById(Integer id);

    void batchDeletePeopleByIds(String[] ids);
    /**
     * 数据导入
     * @param list
     */
    boolean insertByImport(CommonsMultipartFile[] files);
    /**
     * 导出Excel
     * @param response
     * @param id
     * @return
     */
    void exportExcel(HttpServletResponse response,String[] idList);
    /**
     * 导出Word
     * @param request
     * @param response
     * @param id
     * @return
     */
    void exportWord(HttpServletResponse response, String id);

    /**
     * 根据条件搜索ids
     * @param request
     * @param response
     * @param id
     * @return
     */
    String findPeopleIDsByCondition(PageInfo pageInfo);

    void batchRetirePeopleByIds(String[] idList);

    void batchNormalPeopleByIds(String[] idList);

    void deathFeeReview(HttpServletResponse response, String ids);

    void deathFeeReceive(HttpServletResponse response, String ids);
}
