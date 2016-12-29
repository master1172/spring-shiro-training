package com.wangzhixuan.service;

import com.wangzhixuan.model.PeopleDispatch;
import com.wangzhixuan.utils.PageInfo;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by liushaoyang on 2016/9/8.
 */
public interface PeopleDispatchService {

    /**
     * 根据人员id查询人员
     *
     * @param id
     * @return
     */
    PeopleDispatch findPeopleDispatchById(Long id);

    /**
     * 根据人员姓名查询人员
     *
     * @param name
     * @return
     */
    PeopleDispatch findPeopleDispatchByName(String name);


    /**
     * 人员列表
     *
     * @param pageInfo
     */
    void findDataGrid(PageInfo pageInfo);

    /**
     * 添加人员
     *
     * @param peopleDispatch
     */
    void addPeopleDispatch(PeopleDispatch peopleDispatch, CommonsMultipartFile file);



    /**
     * 修改人员
     *
     * @param peopleDispatch
     */
    void updatePeopleDispatch(PeopleDispatch peopleDispatch, CommonsMultipartFile file);

    /**
     * 删除人员
     *
     * @param id
     */
    void deletePeopleDispatchById(Long id);

    void batchDeletePeopleDispatchByIds(String[] ids);
    /**
     * 数据导入
     * @param files
     */
    boolean insertByImport(CommonsMultipartFile[] files);
    /**
     * 导出Excel
     * @param response
     * @param idList
     * @return
     */
    void exportExcel(HttpServletResponse response,String[] idList);
    /**
     * 导出Word
     * @param response
     * @param id
     * @return
     */
    void exportWord(HttpServletResponse response,String id);

    /**
     * 根据条件搜索ids
     * @param pageInfo
     * @return
     */
    String findPeopleDispatchIDsByCondition(PageInfo pageInfo);
}


